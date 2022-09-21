package pt.thenerdguylulu.controller;

import pt.thenerdguylulu.interfaces.ClientInterface;
import pt.thenerdguylulu.model.Dealer;
import pt.thenerdguylulu.model.Player;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GameController {
    private final PlayerController playerController = new PlayerController();
    private final DeckController deckController = new DeckController();
    private final Dealer dealer = new Dealer();
    private final ScheduledExecutorService gameScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService playTimeout = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> playTimeoutScheduledFuture;
    private ScheduledFuture<?> gameScheduledFuture;

    public void startGame() {
        if (gameScheduledFuture != null)
            gameScheduledFuture.cancel(true);
        if (playTimeoutScheduledFuture != null)
            playTimeoutScheduledFuture.cancel(true);

        gameScheduledFuture = gameScheduledExecutorService.schedule(() -> {
            // Reset dealer
            this.dealer.resetDealer();
            // Remove broke players before game begins
            this.playerController.removeBrokePlayers();
            this.dealer.getHand().addCard(this.deckController.deal());
            this.dealer.getHand().addCard(this.deckController.deal());

            // Reset players when new game starts
            this.playerController.resetPlayers();

            this.playerController.notifyLog("JOGO VAI COMEÇAR!");
            this.deckController.shuffle();
            this.playerController.setPlayersPlaying();
            this.playerController.bet();
            this.playerController.notifyPlayersListHasChanged();
            this.playerController.distributeInitialCards(this.deckController);
            this.verifyIfBlackJack();
            this.playerController.notifyCardsChanged(this.dealer);
            this.resetAndStartPlayTimeout();
        }, 5L, TimeUnit.SECONDS);
    }

    private void verifyIfBlackJack() {
        final Player firstPlayer = this.playerController.playersPlaying.element();
        if (firstPlayer.getHand().getHandValue(false) == 21) {
            this.nextPlayerTurn();
        }
    }

    private void nextPlayerTurn() {
        if (!this.playerController.validateAndSetNextPlayerTurn()) {
            this.dealer.setFirstCardHidden(false);
            final List<Player> playersWithHandValueBellow22 = this.playerController.getPlayersWithHandValueBellow22();
            if (playersWithHandValueBellow22.size() > 0)
                while (this.dealer.getHand().getHandValue(false) <= 17)
                    this.dealer.getHand().addCard(this.deckController.deal());

            this.findWinners(playersWithHandValueBellow22);

            this.playerController.notifyCardsChanged(this.dealer);

            // GAME ENDED, START NEW GAME
            startGame();
        }
    }

    /**
     * Verify winners when dealer shows his cards
     */
    private void findWinners(final List<Player> aPlayersWithHandValueBellow22) {
        final int dealerHand = this.dealer.getHand().getHandValue(false);
        aPlayersWithHandValueBellow22.forEach(player -> {
            if (dealerHand > 21) {
                this.playerController.setPlayerWon(player);
            } else {
                final int playerHand = player.getHand().getHandValue(false);
                if (playerHand > dealerHand)
                    this.playerController.setPlayerWon(player);
                else if (playerHand == dealerHand)
                    this.playerController.setPlayerDraw(player);
                else
                    this.playerController.setPlayerLost(player);
            }
        });
    }

    public synchronized void hit(final ClientInterface aClient) {
        if (aClient != null) {
            final Player player = this.playerController.getPlayingPlayer();
            if (player.getClientInterface().equals(aClient) && player.getHand().getHandValue(false) < 21) {
                this.playTimeoutScheduledFuture.cancel(true);
                this.resetAndStartPlayTimeout();
                player.getHand().addCard(this.deckController.deal());

                this.playerController.notifyLog(String.format("%s - HIT", player.getName()));

                final int handValue = player.getHand().getHandValue(false);

                // IF WON OR LOST, SET NEXT PLAYER
                if (handValue >= 21)
                    this.nextPlayerTurn();

                this.playerController.notifyCardsChanged(this.dealer);
            }
        }
    }

    public synchronized void stand(final ClientInterface aClientInterface) {
        final Player player = this.playerController.getPlayingPlayer();
        if (player != null && player.getHand().getHandValue(false) < 21 && player.getClientInterface().equals(aClientInterface)) {
            this.playTimeoutScheduledFuture.cancel(true);
            this.resetAndStartPlayTimeout();
            this.playerController.notifyLog(String.format("%s - STAND", player.getName()));
            this.nextPlayerTurn();
            this.playerController.notifyCardsChanged(this.dealer);
        }
    }

    public Player joinGame(final Player aPlayer) {
        final boolean added = this.playerController.addPlayer(aPlayer);

        if (added) {
            this.playerController.notifyPlayersListHasChanged();
            this.playerController.notifyLog(String.format("%s entrou na sala", aPlayer.getName()));
            // Only one game can be started!
            synchronized (this.playerController.players) {
                if (this.playerController.players.size() == 1) {
                    // Reset dealer, so it won't show the cards from the previous game
                    this.dealer.resetDealer();
                    startGame();
                }
            }
            return aPlayer;
        }


        return null;
    }

    public void leaveGame(final ClientInterface aClientInterface) {
        this.stand(aClientInterface);
        this.playerController.removePlayer(aClientInterface);
        this.playerController.notifyPlayersListHasChanged();
    }

    public void withdrawGame(ClientInterface aClientInterface) {
        this.stand(aClientInterface);
        this.playerController.removePlayingPlayer(aClientInterface);
        this.playerController.notifyPlayersListHasChanged();
    }

    public void updateUser(final ClientInterface aClientInterface) {
        this.playerController.notifyCardsChanged(aClientInterface, this.dealer);
    }

    public void resetAndStartPlayTimeout() {
        playTimeoutScheduledFuture = this.playTimeout.schedule(() -> this.stand(this.playerController.getPlayingPlayer().getClientInterface()), 5L, TimeUnit.SECONDS);
    }
}

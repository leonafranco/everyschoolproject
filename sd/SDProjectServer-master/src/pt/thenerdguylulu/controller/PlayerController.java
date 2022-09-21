package pt.thenerdguylulu.controller;

import pt.thenerdguylulu.interfaces.ClientInterface;
import pt.thenerdguylulu.model.Dealer;
import pt.thenerdguylulu.model.Player;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PlayerController {
    public final LinkedList<Player> players = new LinkedList<>();
    public final LinkedList<Player> playersPlaying = new LinkedList<>();

    public boolean addPlayer(final Player aPlayer) {
        synchronized (this.players) {
            if (aPlayer != null && aPlayer.getName() != null)
                if (this.players.stream().noneMatch(player -> player.getName().equalsIgnoreCase(aPlayer.getName())))
                    return this.players.add(aPlayer);
        }
        return false;
    }

    public void setPlayersPlaying() {
        synchronized (this.players) {
            synchronized (this.playersPlaying) {
                this.playersPlaying.clear();
                for (final Player player : this.players) {
                    if (this.playersPlaying.size() < 3) {
                        player.setIsPlaying(true);
                        this.playersPlaying.add(player);
                        player.setTablePosition(this.playersPlaying.size());
                    } else return;

                    // Set linked list head element as the player whose playing at the moment
                    if (this.playersPlaying.size() == 1)
                        try {
                            this.playersPlaying.element().setPlayerTurn(true);
                        } catch (NoSuchElementException ignored) {
                            this.notifyPlayersListHasChanged();
                        }
                }
            }
        }
    }

    /**
     * @return true if there is a player that will take his turn,
     * false if all players have played
     */
    public boolean validateAndSetNextPlayerTurn() {
        synchronized (this.playersPlaying) {
            final Player currentPlayer = this.playersPlaying.element();
            final int handValue = currentPlayer.getHand().getHandValue(false);

            if (handValue > 21)
                this.setPlayerLost(currentPlayer);

            currentPlayer.setPlayerTurn(false);
            currentPlayer.setHasPlayed(true);

            Player nextPlayer = this.getNextPlayingPlayer();

            if (!nextPlayer.isHasPlayed())
                if (nextPlayer.getHand().getHandValue(false) < 21) {
                    nextPlayer.setPlayerTurn(true);
                } else
                    return this.validateAndSetNextPlayerTurn();
            else return false;
            return true;
        }
    }

    public void notifyPlayersListHasChanged() {
        synchronized (this.players) {
            Iterator<Player> it = this.players.iterator();
            while (it.hasNext()) {
                final Player player = it.next();
                try {
                    player.getClientInterface().updatePlayersList(this.players);
                } catch (RemoteException e) {
                    this.notifyLogWithoutValidation(String.format("%s saiu da sala", player.getName()));
                    it.remove();
                    this.notifyPlayersListHasChanged();
                    return;
                }
            }
        }
    }

    public void notifyLogWithoutValidation(String aLogMsg) {
        synchronized (this.players) {
            for (final Player player : this.players)
                try {
                    player.getClientInterface().serverLog(aLogMsg);
                } catch (RemoteException ignored) {
                }
        }
    }

    public void notifyLog(String aLogMsg) {
        aLogMsg = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + aLogMsg;

        boolean userLeft = false;
        synchronized (this.players) {
            for (final Player player : this.players)
                try {
                    player.getClientInterface().serverLog(aLogMsg);
                } catch (RemoteException e) {
                    userLeft = true;
                }
        }
        if (userLeft)
            notifyPlayersListHasChanged();
    }

    public Player getNextPlayingPlayer() {
        synchronized (this.playersPlaying) {
            // Retrieves and removes the head of this queue
            final Player head = this.playersPlaying.remove();
            // Moves to the end of the queue
            this.playersPlaying.add(head);
            // Return new head
            return this.playersPlaying.element();
        }
    }

    public void removePlayer(final ClientInterface aClientInterface) {
        synchronized (this.players) {
            final Player player = this.players.stream().filter(p -> p.getClientInterface().equals(aClientInterface)).findFirst().orElse(null);
            if (player != null) {
                if (this.players.remove(player)) {
                    // If player exists in middle of match without having played already, it will skip it
                    player.setHasPlayed(true);
                    this.notifyLog(String.format("%s saiu da sala", player.getName()));
                }
            }
        }
    }


    public void removePlayingPlayer(ClientInterface aClientInterface) {
        synchronized (this.playersPlaying) {
            final Player player = this.playersPlaying.stream().filter(p -> p.getClientInterface().equals(aClientInterface)).findFirst().orElse(null);
            if (player != null) {
                // Remove from whatever position it is in the linked list
                synchronized (this.players) {
                    if (this.players.remove(player)) {
                        // Add to tail
                        this.players.add(player);
                        player.setIsPlaying(false);
                        player.setHasPlayed(true);
                        this.notifyLog(String.format("%s abandonou a partida", player.getName()));
                    }
                }
            }
        }
    }

    public Player getPlayingPlayer() {
        synchronized (this.playersPlaying) {
            return this.playersPlaying.stream().filter(Player::isPlayerTurn).findFirst().orElse(null);
        }
    }

    public void distributeInitialCards(final DeckController aDeckController) {
        synchronized (this.playersPlaying) {
            for (final Player player : this.playersPlaying) {
                player.getHand().addCard(aDeckController.deal());
                player.getHand().addCard(aDeckController.deal());
            }
        }
    }

    public void notifyCardsChanged(final Dealer aDealer) {
        boolean userLeft = false;

        synchronized (this.players) {
            for (final Player player : this.players)
                try {
                    player.getClientInterface().cardsChanged(this.playersPlaying.toArray(new Player[0]), aDealer);
                } catch (RemoteException e) {
                    userLeft = true;
                }
        }
        if (userLeft)
            this.notifyPlayersListHasChanged();
    }

    public void notifyCardsChanged(final ClientInterface aClientInterface, final Dealer aDealer) {
        try {
            synchronized (this.playersPlaying) {
                aClientInterface.cardsChanged(this.playersPlaying.toArray(new Player[0]), aDealer);
            }
        } catch (RemoteException e) {
            this.notifyPlayersListHasChanged();
        }
    }

    public void bet() {
        synchronized (this.playersPlaying) {
            for (final Player player : this.playersPlaying) {
                player.changeChips(-2);
            }
        }
    }

    public List<Player> getPlayersWithHandValueBellow22() {
        synchronized (this.playersPlaying) {
            return this.playersPlaying.stream().filter(player -> player.getHand().getHandValue(false) < 22)
                    .collect(Collectors.toList());
        }
    }

    public void setPlayerLost(final Player aPlayer) {
        aPlayer.setVictoryStatus(Player.LOSER);
        this.notifyLog(String.format("%s - perdeu", aPlayer.getName()));
    }

    public void setPlayerWon(final Player aPlayer) {
        aPlayer.setVictoryStatus(Player.WINNER);
        aPlayer.changeChips(4);
        this.notifyLog(String.format("%s - venceu", aPlayer.getName()));
    }

    public void setPlayerDraw(final Player aPlayer) {
        aPlayer.setVictoryStatus(Player.DRAW);
        aPlayer.changeChips(2);
        this.notifyLog(String.format("%s - empate", aPlayer.getName()));
    }

    public void resetPlayers() {
        synchronized (this.playersPlaying) {
            this.playersPlaying.forEach(this::resetPlayersProps);
        }
    }

    private void resetPlayersProps(final Player aPlayer) {
        aPlayer.setVictoryStatus(Player.UNDEFINED);
        aPlayer.setPlaying(false);
        aPlayer.setPlayerTurn(false);
        aPlayer.setHasPlayed(false);
        aPlayer.getHand().clearHand();
    }

    public void removeBrokePlayers() {
        synchronized (this.playersPlaying) {
            this.playersPlaying.stream().filter(player -> player.getChips() == 0).forEach(player -> {
                if (this.players.remove(player)) {
                    this.players.add(player);
                    player.setChips(10);
                }
            });
        }
    }
}

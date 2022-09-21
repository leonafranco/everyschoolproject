package pt.thenerdguylulu.model;

import pt.thenerdguylulu.interfaces.ClientInterface;

import java.io.Serializable;

public class Player extends Dealer implements Serializable {
    public static int UNDEFINED = 0;
    public static int WINNER = 1;
    public static int LOSER = 2;
    public static int DRAW = 3;

    private final ClientInterface clientInterface;
    private final String name;
    private boolean isPlaying;
    private boolean playerTurn;
    private int chips;
    private boolean hasPlayed;
    private int victoryStatus;
    private int tablePosition;

    public Player(final ClientInterface aClientInterface, final String aName) {
        super();
        this.clientInterface = aClientInterface;
        this.name = aName;
        this.isPlaying = false;
        this.playerTurn = false;
        this.hasPlayed = false;
        this.victoryStatus = UNDEFINED;
        this.chips = 10;
    }

    public String getName() {
        return this.name;
    }

    public int getChips() {
        return this.chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void changeChips(final int aValue) {
        this.chips += aValue;
    }

    public ClientInterface getClientInterface() {
        return this.clientInterface;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(final boolean aIsPlaying) {
        this.isPlaying = aIsPlaying;
    }

    public boolean isPlayerTurn() {
        return this.playerTurn;
    }

    public void setPlayerTurn(final boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    public boolean isHasPlayed() {
        return this.hasPlayed;
    }

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public int getVictoryStatus() {
        return this.victoryStatus;
    }

    public void setVictoryStatus(final int aStatus) {
        this.victoryStatus = aStatus;
    }

    public int getTablePosition() {
        return this.tablePosition;
    }

    public void setTablePosition(final int aTablePosition) {
        this.tablePosition = aTablePosition;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", isPlaying=" + isPlaying +
                ", chips=" + chips +
                ", hand=" + hand +
                '}';
    }
}

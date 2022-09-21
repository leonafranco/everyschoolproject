package pt.thenerdguylulu;

import pt.thenerdguylulu.model.Dealer;
import pt.thenerdguylulu.interfaces.ClientInterface;
import pt.thenerdguylulu.model.Player;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Queue;

public class ClientRMI extends UnicastRemoteObject implements ClientInterface {
    private static ClientRMI instance;

    public static ClientRMI getInstance() {
        if (instance == null) {
            try {
                instance = new ClientRMI();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public ClientRMI() throws RemoteException {
        super();
    }

    private Player me;
    private JTextArea playersTA;
    private JTextArea logTA;
    private JButton withdrawBtn;
    private TableUI tableUI;

    public Player getMe() {
        return this.me;
    }

    public void setMe(final Player me) {
        this.me = me;
    }

    public void setPlayersTextArea(final JTextArea players) {
        this.playersTA = players;
    }

    public void setLogTA(final JTextArea aLogTA) {
        this.logTA = aLogTA;
    }

    public void setTableUI(final TableUI tableUI) {
        this.tableUI = tableUI;
    }

    public void setWithdrawBtn(JButton withdrawBtn) {
        this.withdrawBtn = withdrawBtn;
    }

    public void updatePlayersList(final Queue<Player> players) throws RemoteException {
        if (playersTA != null && players != null) {
            this.playersTA.setText(null);
            players.forEach(player -> this.playersTA.append(player.toString()));
        }
    }

    public void serverLog(final String aLog) throws RemoteException {
        if (this.logTA != null && aLog != null) {
            this.logTA.append(aLog + "\n");
        }
    }

    public void cardsChanged(final Player[] aPlayers, final Dealer aDealer) throws RemoteException {
        if (aPlayers != null && aDealer != null && this.tableUI != null) {
            this.tableUI.buildUI(aPlayers, aDealer);
            // Enable withdraw button if player is playing
            Arrays.stream(aPlayers).filter(player -> player.getName().equals(me.getName())).findAny().ifPresent(p -> this.withdrawBtn.setEnabled(p.isPlaying()));
        }
    }
}

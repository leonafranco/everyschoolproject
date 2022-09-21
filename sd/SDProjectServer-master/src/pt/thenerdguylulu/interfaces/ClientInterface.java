package pt.thenerdguylulu.interfaces;

import pt.thenerdguylulu.model.Dealer;
import pt.thenerdguylulu.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Queue;

public interface ClientInterface extends Remote {

    void updatePlayersList(Queue<Player> players) throws RemoteException;

    void serverLog(String aLog) throws RemoteException;

    void cardsChanged(Player[] player, Dealer dealer) throws RemoteException;
}

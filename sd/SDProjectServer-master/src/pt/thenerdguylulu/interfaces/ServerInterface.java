package pt.thenerdguylulu.interfaces;

import pt.thenerdguylulu.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    Player login(Player aPlayer) throws RemoteException;

    void hit(ClientInterface clientInterface) throws RemoteException;

    void stand(ClientInterface clientInterface) throws RemoteException;

    void requestUpdate(ClientInterface clientInterface) throws RemoteException;

    void leaveGame(ClientInterface clientInterface) throws RemoteException;

    void withdrawGame(ClientInterface clientInterface) throws RemoteException;
}

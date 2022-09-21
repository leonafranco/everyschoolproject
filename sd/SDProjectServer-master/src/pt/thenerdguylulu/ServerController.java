package pt.thenerdguylulu;

import pt.thenerdguylulu.controller.DeckController;
import pt.thenerdguylulu.controller.GameController;
import pt.thenerdguylulu.interfaces.ClientInterface;
import pt.thenerdguylulu.interfaces.ServerInterface;
import pt.thenerdguylulu.model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerController extends UnicastRemoteObject implements ServerInterface {
    private final DeckController deck = new DeckController();
    private final GameController gameController = new GameController();

    public ServerController() throws RemoteException {
        super();
    }

    @Override
    public Player login(final Player aPlayer) throws RemoteException {
        return this.gameController.joinGame(aPlayer);
    }

    @Override
    public void hit(final ClientInterface aClientInterface) throws RemoteException {
        this.gameController.hit(aClientInterface);
    }

    @Override
    public void stand(final ClientInterface aClientInterface) throws RemoteException {
        this.gameController.stand(aClientInterface);
    }

    @Override
    public void requestUpdate(final ClientInterface aClientInterface) throws RemoteException {
        this.gameController.updateUser(aClientInterface);
    }

    @Override
    public void leaveGame(final ClientInterface aClientInterface) throws RemoteException {
        this.gameController.leaveGame(aClientInterface);
    }

    @Override
    public void withdrawGame(final ClientInterface aClientInterface) throws RemoteException {
        this.gameController.withdrawGame(aClientInterface);
    }
}

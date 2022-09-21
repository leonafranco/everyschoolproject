package pt.thenerdguylulu;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int port = Main.readInt("Indique o porto RMI:");

        try {
            final Registry reg = LocateRegistry.createRegistry(port);
            reg.rebind("blackjack", new ServerController());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }


    public static int readInt(final String aMsg) {
        final Scanner scanner = new Scanner(System.in);
        do {
            if (aMsg != null)
                System.out.print(aMsg);
            try {
                final int i = scanner.nextInt();
                scanner.nextLine(); // Clear Buffer
                return i;
            } catch (InputMismatchException ignored) {
                scanner.nextLine();
                System.out.println("O valor introduzido não está correto. Tente novamente.");
            }
        } while (true);
    }
}
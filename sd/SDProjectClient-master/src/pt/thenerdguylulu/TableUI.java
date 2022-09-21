package pt.thenerdguylulu;

import pt.thenerdguylulu.model.Dealer;
import pt.thenerdguylulu.model.Player;

import javax.swing.*;
import java.awt.*;

public class TableUI extends JPanel {
    final ClientRMI clientRMI = ClientRMI.getInstance();

    public TableUI() {
        clientRMI.setTableUI(this);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        try {
            Main.serverInterface.requestUpdate(clientRMI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildUI(final Player[] aPlayers, Dealer dealer) {
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.removeAll();
        for (final Player player : aPlayers) {
            switch (player.getTablePosition()) {
                case 1:
                    this.add(new PlayerUI(player), BorderLayout.WEST);
                    break;
                case 2:
                    this.add(new PlayerUI(player), BorderLayout.SOUTH);
                    break;
                case 3:
                    this.add(new PlayerUI(player), BorderLayout.EAST);
                    break;
            }
        }
        this.add(new PlayerUI(dealer), BorderLayout.NORTH);
        this.revalidate();
        this.repaint();
    }
}

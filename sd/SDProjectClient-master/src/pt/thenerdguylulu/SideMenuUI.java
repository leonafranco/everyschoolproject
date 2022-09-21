package pt.thenerdguylulu;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class SideMenuUI extends JPanel {

    private final ClientRMI clientRMI = ClientRMI.getInstance();

    public SideMenuUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, getHeight()));
        this.north();
        this.logPanel();
    }


    private void north() {
        final JPanel northJPanel = new JPanel(new BorderLayout());
        northJPanel.add(this.waitingList(), BorderLayout.NORTH);
        northJPanel.add(this.buttons(), BorderLayout.SOUTH);
        this.add(northJPanel, BorderLayout.NORTH);
    }

    private JScrollPane waitingList() {
        final JTextArea players = new JTextArea();
        players.setEditable(false);
        players.setLineWrap(true);
        players.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(players);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        clientRMI.setPlayersTextArea(players);

        return scrollPane;
    }

    private JPanel buttons() {
        final JPanel buttonsJPanel = new JPanel();
        final JButton withdrawBtn = new JButton("Desistir");
        final JButton exitGameBtn = new JButton("Sair do jogo");
        withdrawBtn.setEnabled(false);

        withdrawBtn.addActionListener(e -> {
            try {
                Main.serverInterface.withdrawGame(this.clientRMI);
                withdrawBtn.setEnabled(false);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });

        exitGameBtn.addActionListener(e -> {
            try {
                Main.serverInterface.leaveGame(this.clientRMI);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        });

        clientRMI.setWithdrawBtn(withdrawBtn);

        buttonsJPanel.add(withdrawBtn);
        buttonsJPanel.add(exitGameBtn);
        return buttonsJPanel;
    }

    private void logPanel() {
        final JTextArea log = new JTextArea();
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        this.add(new JScrollPane(log), BorderLayout.CENTER);
        clientRMI.setLogTA(log);
    }
}

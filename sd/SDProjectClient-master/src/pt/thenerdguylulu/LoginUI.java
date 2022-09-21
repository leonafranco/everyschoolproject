package pt.thenerdguylulu;

import pt.thenerdguylulu.interfaces.ServerInterface;
import pt.thenerdguylulu.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoginUI extends JPanel implements ActionListener {
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JTextField nameJTF = new JTextField(10);
    private final JTextField ipJTF = new JTextField(10);
    private final JTextField portJTF = new JTextField(10);
    private final JLabel errorJL = new JLabel();

    public LoginUI() {
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        final JLabel title = new JLabel("Blackjack Stars");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        title.setHorizontalAlignment(JLabel.CENTER);

        this.add(title, gbc);
        this.buildFormUI();
    }

    private void buildFormUI() {
        final JPanel form = new JPanel();

        form.setLayout(new GridLayout(7, 2));
        form.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(form, gbc);

        final JLabel nameJL = new JLabel("Nome:");
        final JLabel ipJL = new JLabel("IP:");
        final JLabel portJL = new JLabel("Porto:");
        final JButton btn = new JButton("ENTRAR");
        btn.addActionListener(this);

        this.setTextWhite(nameJL, ipJL, portJL, errorJL);
        form.add(nameJL);
        form.add(nameJTF);
        form.add(ipJL);
        form.add(ipJTF);
        form.add(portJL);
        form.add(portJTF);

        form.add(new JLabel());
        form.add(btn);
        form.add(errorJL);
    }

    private void setTextWhite(final JLabel... jLabel) {
        for (JLabel jl : jLabel) {
            jl.setForeground(Color.WHITE);
            jl.setFont(jl.getFont().deriveFont(Font.BOLD, jl.getFont().getSize()));
            jl.setHorizontalAlignment(JLabel.LEADING);
            jl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int port;
            try {
                port = Integer.parseInt(this.portJTF.getText().trim());
            } catch (NumberFormatException ignored) {
                errorJL.setText("Porto inválido");
                return;
            }
            final Registry reg = LocateRegistry.getRegistry(this.ipJTF.getText(), port);
            Main.serverInterface = (ServerInterface) reg.lookup("blackjack");

            // IT MUST BE INITIALIZED BEFORE LOGIN, SO IT CAN LOG WHAT THE USER ENTERED THE ROOM
            final SideMenuUI sideMenuUI = new SideMenuUI();

            Player me = new Player(ClientRMI.getInstance(), this.nameJTF.getText());
            if ((me = Main.serverInterface.login(me)) != null) {
                ClientRMI.getInstance().setMe(me);
                ((JComponent) this.getParent()).add(new TableUI(), BorderLayout.CENTER);
                ((JComponent) this.getParent()).add(sideMenuUI, BorderLayout.EAST);
                ((JComponent) this.getParent()).revalidate();
                ((JComponent) this.getParent()).repaint();
                ((JComponent) this.getParent()).remove(this);
            } else errorJL.setText("Nome já registado");
        } catch (RemoteException ex) {
            errorJL.setText("Servidor RMI não encontrado");
        } catch (NotBoundException ex) {
            errorJL.setText("RMI não encontrado");
        }
    }
}

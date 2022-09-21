package UI.Main;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

public class PedidoRemocaoContaUI extends JFrame {


    private final Container container;
    private final JLabel mainJL;
    private final JButton aceitarBTN, recusarBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     *
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public PedidoRemocaoContaUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Deseja Remover a sua conta?");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(250, 20);
        mainJL.setLocation(120, 20);

        aceitarBTN = new JButton("Aceitar");
        aceitarBTN.setActionCommand("removerContaBTN");
        aceitarBTN.addActionListener(event);
        aceitarBTN.setSize(250, 20);
        aceitarBTN.setLocation(80, 60);
        aceitarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        recusarBTN = new JButton("Recusar");
        recusarBTN.setActionCommand("naoRemoverContaBTN");
        recusarBTN.addActionListener(event);
        recusarBTN.setSize(250, 20);
        recusarBTN.setLocation(80, 90);
        recusarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        container.add(mainJL);
        container.add(aceitarBTN);
        container.add(recusarBTN);


        this.setSize(420, 150);
        this.setLocationRelativeTo(null);
        this.setTitle("Remover Conta");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);
    }
}

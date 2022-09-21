package UI.Main;

import com.company.Main;


import javax.swing.*;
import java.awt.*;

/**
 * Primeira classe de Menu a ser chamada, contida os botoes para aceder o Login, Registo ou Password
 */

public class MainUI extends JFrame{

    private final Container container;
    private final JPanel painelJP, mainJP;
    private final JButton loginBTN, registerBTN, sairBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public MainUI(Main event) {


        container = getContentPane();
        container.setLayout(new BorderLayout());

        painelJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelJP.add(new JLabel("Gestão de Compras"));


        mainJP = new JPanel(new GridLayout(3,2));


        JPanel loginBTNJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginBTN = new JButton(" Login ");
        loginBTN.setActionCommand("login");
        loginBTN.addActionListener(event);
        loginBTN.setPreferredSize(new Dimension(90,28));
        loginBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBTNJP.add(loginBTN);

        JPanel registerBTNJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerBTN = new JButton("registo");
        registerBTN.setActionCommand("registo");
        registerBTN.addActionListener(event);
        registerBTN.setPreferredSize(new Dimension(90,28));
        registerBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerBTNJP.add(registerBTN);

        JPanel sairBTNJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sairBTN = new JButton("sair");
        sairBTN.setActionCommand("sair");
        sairBTN.addActionListener(event);
        sairBTN.setPreferredSize(new Dimension(90,28));
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sairBTNJP.add(sairBTN);

        mainJP.add(loginBTNJP);
        mainJP.add(registerBTNJP);
        mainJP.add(sairBTNJP);


        container.add(painelJP, BorderLayout.NORTH);
        container.add(mainJP, BorderLayout.CENTER);
        //container.add(new JPanel(), BorderLayout.SOUTH);


        this.setSize(350,200);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Menu");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

}

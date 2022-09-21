package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe MenuPesquisaListagemGestorUI, classe de menu para ver todas as diferentes listagens possiveis pelo gestor
 */
public class MenuPesquisaListagemGestorUI extends JFrame{

    private final Container container;
    private final JLabel mainJL;
    private final JButton listarUtilizadoresBTN, pesquisarUtilizadoresBTN, listarEncomendasBTN, listarEncomendasNEntreguesBTN, pesquisarEncomendasCLienteBTN, pesquisarEncomendasTempoBTN, voltarBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public MenuPesquisaListagemGestorUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Pesquisas e Listagens");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(250,20);
        mainJL.setLocation(120,20);

        listarUtilizadoresBTN = new JButton("Listar Utilizadores");
        listarUtilizadoresBTN.setActionCommand("listarUtilizadores");
        listarUtilizadoresBTN.addActionListener(event);
        listarUtilizadoresBTN.setSize(250, 20);
        listarUtilizadoresBTN.setLocation(80,60);
        listarUtilizadoresBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisarUtilizadoresBTN = new JButton("Pesquisar Utilizadores");
        pesquisarUtilizadoresBTN.setActionCommand("pesquisarUtilizadores");
        pesquisarUtilizadoresBTN.addActionListener(event);
        pesquisarUtilizadoresBTN.setSize(250, 20);
        pesquisarUtilizadoresBTN.setLocation(80,90);
        pesquisarUtilizadoresBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        listarEncomendasBTN = new JButton("Listar Encomendas Utilizador");
        listarEncomendasBTN.setActionCommand("listarEncomendasUtilizador");
        listarEncomendasBTN.addActionListener(event);
        listarEncomendasBTN.setSize(250, 20);
        listarEncomendasBTN.setLocation(80,120);
        listarEncomendasBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        listarEncomendasNEntreguesBTN = new JButton("Listar Encomendas não Entregues");
        listarEncomendasNEntreguesBTN.setActionCommand("listarEncomendasNEntregues");
        listarEncomendasNEntreguesBTN.addActionListener(event);
        listarEncomendasNEntreguesBTN.setSize(250, 20);
        listarEncomendasNEntreguesBTN.setLocation(80,150);
        listarEncomendasNEntreguesBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        pesquisarEncomendasCLienteBTN = new JButton("Pesquisas Encomendas Cliente");
        pesquisarEncomendasCLienteBTN.setActionCommand("pesquisarEncomendasCLiente");
        pesquisarEncomendasCLienteBTN.addActionListener(event);
        pesquisarEncomendasCLienteBTN.setSize(250, 20);
        pesquisarEncomendasCLienteBTN.setLocation(80,180);
        pesquisarEncomendasCLienteBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisarEncomendasTempoBTN = new JButton("Pesquisar Encomendar Por Tempo");
        pesquisarEncomendasTempoBTN.setActionCommand("pesquisarEncomendasTempoBTN");
        pesquisarEncomendasTempoBTN.addActionListener(event);
        pesquisarEncomendasTempoBTN.setSize(250, 20);
        pesquisarEncomendasTempoBTN.setLocation(80,210);
        pesquisarEncomendasTempoBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        voltarBTN = new JButton("Voltar");
        voltarBTN.setActionCommand("VoltarMenuGestor");
        voltarBTN.addActionListener(event);
        voltarBTN.setSize(250, 20);
        voltarBTN.setLocation(80,240);
        voltarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));



        container.add(mainJL);
        container.add(listarUtilizadoresBTN);
        container.add(pesquisarUtilizadoresBTN);
        container.add(listarEncomendasBTN);
        container.add(listarEncomendasNEntreguesBTN);
        container.add(pesquisarEncomendasCLienteBTN);
        container.add(pesquisarEncomendasTempoBTN);
        container.add(voltarBTN);



        this.setSize(420,380);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu Pesquisas");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);
    }
}

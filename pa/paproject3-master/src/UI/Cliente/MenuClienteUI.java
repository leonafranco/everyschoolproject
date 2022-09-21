package UI.Cliente;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe MenuClienteUI, classe principal que é chamado após um cliente se autenticar no software
 */

public class MenuClienteUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JButton novaCompraBTN, encomendaEntregueBTN, alterarDadosBTN, pesquisarListagensBTN, notificacoesBTN, desativarContaBTN, sairBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public MenuClienteUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Menu Cliente");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(185,20);
        mainJL.setLocation(130,20);

        novaCompraBTN = new JButton("Nova Compra");
        novaCompraBTN.setActionCommand("novaCompra");
        novaCompraBTN.addActionListener(event);
        novaCompraBTN.setSize(185, 20);
        novaCompraBTN.setLocation(100,60);
        novaCompraBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        encomendaEntregueBTN = new JButton("Encomenda Entregue");
        encomendaEntregueBTN.setActionCommand("encomendaEntregue");
        encomendaEntregueBTN.addActionListener(event);
        encomendaEntregueBTN.setSize(185, 20);
        encomendaEntregueBTN.setLocation(100,90);
        encomendaEntregueBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        alterarDadosBTN = new JButton("Alterar Dados");
        alterarDadosBTN.setActionCommand("alterarDadosCliente");
        alterarDadosBTN.addActionListener(event);
        alterarDadosBTN.setSize(185, 20);
        alterarDadosBTN.setLocation(100,120);
        alterarDadosBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisarListagensBTN = new JButton("Pesquisas e Listagens");
        pesquisarListagensBTN.setActionCommand("pesquisarListagensCliente");
        pesquisarListagensBTN.addActionListener(event);
        pesquisarListagensBTN.setSize(185, 20);
        pesquisarListagensBTN.setLocation(100,150);
        pesquisarListagensBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        notificacoesBTN = new JButton("Notificacoes");
        notificacoesBTN.setActionCommand("notificacoes");
        notificacoesBTN.addActionListener(event);
        notificacoesBTN.setSize(185, 20);
        notificacoesBTN.setLocation(100,180);
        notificacoesBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        desativarContaBTN = new JButton("Desativar Conta");
        desativarContaBTN.setActionCommand("desativarConta");
        desativarContaBTN.addActionListener(event);
        desativarContaBTN.setSize(185, 20);
        desativarContaBTN.setLocation(100,210);
        desativarContaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        sairBTN = new JButton("Sair");
        sairBTN.setActionCommand("Sair");
        sairBTN.addActionListener(event);
        sairBTN.setSize(185, 20);
        sairBTN.setLocation(100,240);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        container.add(mainJL);
        container.add(novaCompraBTN);
        container.add(encomendaEntregueBTN);
        container.add(alterarDadosBTN);
        container.add(pesquisarListagensBTN);
        container.add(notificacoesBTN);
        container.add(desativarContaBTN);
        container.add(sairBTN);




        this.setSize(400,380);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu Cliente");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);

    }
}

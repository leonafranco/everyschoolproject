package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe MenuGestorUI, classe que é chamada após a autenticação de um Gestor
 */
public class MenuGestorUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JButton menuAlterarDadosBTN, aceitarEncomendaBTN, criarNovoGestorBTN, ativarContasBTN, pesquisarListagensBTN, notificacoesBTN, logBTN, sairBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public MenuGestorUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Menu Gestor");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(185,20);
        mainJL.setLocation(130,20);

        menuAlterarDadosBTN = new JButton("Menu Alterar Dados");
        menuAlterarDadosBTN.setActionCommand("menuAlterarDados");
        menuAlterarDadosBTN.addActionListener(event);
        menuAlterarDadosBTN.setSize(185, 20);
        menuAlterarDadosBTN.setLocation(100,60);
        menuAlterarDadosBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        aceitarEncomendaBTN = new JButton("Aceitar Encomenda");
        aceitarEncomendaBTN.setActionCommand("aceitarEncomenda");
        aceitarEncomendaBTN.addActionListener(event);
        aceitarEncomendaBTN.setSize(185, 20);
        aceitarEncomendaBTN.setLocation(100,90);
        aceitarEncomendaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        criarNovoGestorBTN = new JButton("Criar Novo Gestor");
        criarNovoGestorBTN.setActionCommand("novoGestorUI");
        criarNovoGestorBTN.addActionListener(event);
        criarNovoGestorBTN.setSize(185, 20);
        criarNovoGestorBTN.setLocation(100,120);
        criarNovoGestorBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ativarContasBTN = new JButton("Ativar Contas");
        ativarContasBTN.setActionCommand("menuAtivarContas");
        ativarContasBTN.addActionListener(event);
        ativarContasBTN.setSize(185, 20);
        ativarContasBTN.setLocation(100,150);
        ativarContasBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        pesquisarListagensBTN = new JButton("Pesquisas e Listagens");
        pesquisarListagensBTN.setActionCommand("pesquisarListagens");
        pesquisarListagensBTN.addActionListener(event);
        pesquisarListagensBTN.setSize(185, 20);
        pesquisarListagensBTN.setLocation(100,180);
        pesquisarListagensBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        notificacoesBTN = new JButton("Notificacoes");
        notificacoesBTN.setActionCommand("notificacoes");
        notificacoesBTN.addActionListener(event);
        notificacoesBTN.setSize(185, 20);
        notificacoesBTN.setLocation(100,210);
        notificacoesBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logBTN = new JButton("Logs");
        logBTN.setActionCommand("log");
        logBTN.addActionListener(event);
        logBTN.setSize(185, 20);
        logBTN.setLocation(100,240);
        logBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        sairBTN = new JButton("Sair");
        sairBTN.setActionCommand("Sair");
        sairBTN.addActionListener(event);
        sairBTN.setSize(185, 20);
        sairBTN.setLocation(100,270);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        container.add(mainJL);
        container.add(menuAlterarDadosBTN);
        container.add(aceitarEncomendaBTN);
        container.add(criarNovoGestorBTN);
        container.add(ativarContasBTN);
        container.add(pesquisarListagensBTN);
        container.add(notificacoesBTN);
        container.add(logBTN);
        container.add(sairBTN);



        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu Gestor");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);
    }
}

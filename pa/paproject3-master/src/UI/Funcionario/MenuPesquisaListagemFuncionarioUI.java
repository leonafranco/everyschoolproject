package UI.Funcionario;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe MenuPesquisaListagemFuncionarioUI, classe onde se encontra todas as pesquisas necessarias para o Funcionario
 */

public class MenuPesquisaListagemFuncionarioUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JButton funcionarioListarEncomendaBTN, funcionarioPesquisarEncomendaBTN, listarCategoriaBTN, pesquisarBaixoStockBTN, pesquisarProdutoBTN, voltarBTN;

    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public MenuPesquisaListagemFuncionarioUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Pesquisas e Listagens");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(185,20);
        mainJL.setLocation(115,20);

        funcionarioListarEncomendaBTN = new JButton("Listar Encomenda");
        funcionarioListarEncomendaBTN.setActionCommand("ListarEncomendaFuncionario");
        funcionarioListarEncomendaBTN.addActionListener(event);
        funcionarioListarEncomendaBTN.setSize(185, 20);
        funcionarioListarEncomendaBTN.setLocation(100,60);
        funcionarioListarEncomendaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        funcionarioPesquisarEncomendaBTN = new JButton("Pesquisar Encomenda");
        funcionarioPesquisarEncomendaBTN.setActionCommand("pesquisarEncomendaFuncionario");
        funcionarioPesquisarEncomendaBTN.addActionListener(event);
        funcionarioPesquisarEncomendaBTN.setSize(185, 20);
        funcionarioPesquisarEncomendaBTN.setLocation(100,90);
        funcionarioPesquisarEncomendaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        listarCategoriaBTN = new JButton("Listar Categoria");
        listarCategoriaBTN.setActionCommand("listarCategoria");
        listarCategoriaBTN.addActionListener(event);
        listarCategoriaBTN.setSize(185, 20);
        listarCategoriaBTN.setLocation(100,120);
        listarCategoriaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisarBaixoStockBTN = new JButton("Pesquisar Baixo Stock");
        pesquisarBaixoStockBTN.setActionCommand("pesquisarBaixoStock");
        pesquisarBaixoStockBTN.addActionListener(event);
        pesquisarBaixoStockBTN.setSize(185, 20);
        pesquisarBaixoStockBTN.setLocation(100,150);
        pesquisarBaixoStockBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        pesquisarProdutoBTN = new JButton("Pesquisas Produto");
        pesquisarProdutoBTN.setActionCommand("pesquisarProduto");
        pesquisarProdutoBTN.addActionListener(event);
        pesquisarProdutoBTN.setSize(185, 20);
        pesquisarProdutoBTN.setLocation(100,180);
        pesquisarProdutoBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        voltarBTN = new JButton("Voltar");
        voltarBTN.setActionCommand("VoltarMenuFuncionario");
        voltarBTN.addActionListener(event);
        voltarBTN.setSize(185, 20);
        voltarBTN.setLocation(100,210);
        voltarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));



        container.add(mainJL);
        container.add(funcionarioListarEncomendaBTN);
        container.add(funcionarioPesquisarEncomendaBTN);
        container.add(listarCategoriaBTN);
        container.add(pesquisarBaixoStockBTN);
        container.add(pesquisarProdutoBTN);
        container.add(voltarBTN);



        this.setSize(400,350);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu Pesquisas");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);
    }
}

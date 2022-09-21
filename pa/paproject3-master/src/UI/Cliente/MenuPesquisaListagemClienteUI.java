package UI.Cliente;

import com.company.Main;

import javax.swing.*;
import java.awt.*;


/**
 * Menu PesquisaListagemCliente, menu de todas as pesquisas de listagens que um cliente pode fazer
 */
public class MenuPesquisaListagemClienteUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JButton clienteListarEncomendaBTN, clientePesquisarEncomendaBTN, listarCategoriaBTN, pesquisarProdutoBTN, voltarBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event
     */
    public MenuPesquisaListagemClienteUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Pesquisas e Listagens");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(185,20);
        mainJL.setLocation(115,20);

        clienteListarEncomendaBTN = new JButton("Listar Encomenda");
        clienteListarEncomendaBTN.setActionCommand("listarEncomendaCliente");
        clienteListarEncomendaBTN.addActionListener(event);
        clienteListarEncomendaBTN.setSize(185, 20);
        clienteListarEncomendaBTN.setLocation(100,60);
        clienteListarEncomendaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clientePesquisarEncomendaBTN = new JButton("Pesquisar Encomenda");
        clientePesquisarEncomendaBTN.setActionCommand("pesquisarEncomendaCliente");
        clientePesquisarEncomendaBTN.addActionListener(event);
        clientePesquisarEncomendaBTN.setSize(185, 20);
        clientePesquisarEncomendaBTN.setLocation(100,90);
        clientePesquisarEncomendaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        listarCategoriaBTN = new JButton("Listar Categorias");
        listarCategoriaBTN.setActionCommand("listarCategorias");
        listarCategoriaBTN.addActionListener(event);
        listarCategoriaBTN.setSize(185, 20);
        listarCategoriaBTN.setLocation(100,120);
        listarCategoriaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisarProdutoBTN = new JButton("Pesquisar Produto");
        pesquisarProdutoBTN.setActionCommand("pesquisarProduto");
        pesquisarProdutoBTN.addActionListener(event);
        pesquisarProdutoBTN.setSize(185, 20);
        pesquisarProdutoBTN.setLocation(100,150);
        pesquisarProdutoBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        voltarBTN = new JButton("Voltar");
        voltarBTN.setActionCommand("VoltarMenuCliente");
        voltarBTN.addActionListener(event);
        voltarBTN.setSize(185, 20);
        voltarBTN.setLocation(100, 180);
        voltarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));



        container.add(mainJL);
        container.add(clienteListarEncomendaBTN);
        container.add(clientePesquisarEncomendaBTN);
        container.add(listarCategoriaBTN);
        container.add(pesquisarProdutoBTN);
        container.add(voltarBTN);



        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setTitle("Pesquisas e Listagens");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);

    }
}

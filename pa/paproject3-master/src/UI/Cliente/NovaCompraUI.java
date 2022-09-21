package UI.Cliente;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * metodo que possibilita um cliente fazer uma nova compra
 */
public class NovaCompraUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JTextField skuJTF, quantidadeJTF;
    private final JPanel bottomJP, topJP;
    private final JButton pesquisarButton, voltarBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param lista Lista de produtos disponiveis
     */
    public NovaCompraUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,70,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("SKU DESIGNACAO PRECO STOCK");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);

        voltarBTN = new JButton("voltar");
        voltarBTN.setActionCommand("VoltarMenuCliente");
        voltarBTN.addActionListener(event);
        voltarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topJP.add(mainJL);
        topJP.add(voltarBTN);




        jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.setText(lista);

        scrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setPreferredSize(new Dimension(100, 100));

        bottomJP = new JPanel(new FlowLayout());
        bottomJP.setPreferredSize(new Dimension(50,50));


        skuJTF = new JTextField();
        skuJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        skuJTF.setPreferredSize(new Dimension(120, 30));

        quantidadeJTF = new JTextField();
        quantidadeJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        quantidadeJTF.setPreferredSize(new Dimension(120, 30));

        pesquisarButton = new JButton("Adicionar");
        pesquisarButton.setActionCommand("AdicionarNovaCompra");
        pesquisarButton.addActionListener(event);
        pesquisarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomJP.add(skuJTF);
        bottomJP.add(quantidadeJTF);
        bottomJP.add(pesquisarButton);








        container.add(topJP, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(bottomJP, BorderLayout.SOUTH);




        this.setSize(500, 500);
        this.setTitle("Nova compra");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

    /**
     * Metodo que retorna para a Main qual o artigo comprado (SKU) e a quantidade do mesmo
     * @return Array composto por SKU e Quantidade que deseja comprar
     */
    public String[] getCompra() {

        String[] form = new String[2];
        form[0] = skuJTF.getText();
        form[1] = quantidadeJTF.getText();

        return form;
    }
}

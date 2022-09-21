package UI.Funcionario;

import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classe que possibilita o armazenista criar um novo Produto
 */
public class NovoProdutoUI extends JFrame implements ItemListener {

    private final Container container;
    private final JLabel designacaoJL, mainJL, fabricanteJL, pesoJL, precoJL, classificacaoJL, loteJL, stockJL, categoriaJL, dataProdJL, dataValidadeJL;
    private final JTextField designacaoJTF, pesoJTF, precoJTF, loteJTF, stockJTF, categoriaJTF, dataProdJTF, fabricanteJTF, dataValidadeJTF, classificacaoJTF;
    private final JButton entrarBTN, sairBTN;
    private final JComboBox comboBox;

    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public NovoProdutoUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Novo Produto");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(150, 20);
        mainJL.setLocation(120, 20);


        designacaoJL = new JLabel("designação");
        designacaoJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        designacaoJL.setSize(80, 20);
        designacaoJL.setLocation(30, 60);

        designacaoJTF = new JTextField();
        designacaoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        designacaoJTF.setSize(150, 20);
        designacaoJTF.setLocation(100, 60);


        fabricanteJL = new JLabel("fabricante");
        fabricanteJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fabricanteJL.setSize(100, 20);
        fabricanteJL.setLocation(35, 90);

        fabricanteJTF = new JTextField();
        fabricanteJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fabricanteJTF.setSize(150, 20);
        fabricanteJTF.setLocation(100, 90);

        pesoJL = new JLabel("Peso");
        pesoJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pesoJL.setSize(50, 20);
        pesoJL.setLocation(50, 120);

        pesoJTF = new JTextField();
        pesoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pesoJTF.setSize(150, 20);
        pesoJTF.setLocation(100, 120);

        precoJL = new JLabel("Preço");
        precoJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        precoJL.setSize(50, 20);
        precoJL.setLocation(50, 150);

        precoJTF = new JTextField();
        precoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        precoJTF.setSize(150, 20);
        precoJTF.setLocation(100, 150);


        loteJL = new JLabel("Lote");
        loteJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loteJL.setSize(100, 20);
        loteJL.setLocation(50, 210);

        loteJTF = new JTextField();
        loteJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loteJTF.setSize(150, 20);
        loteJTF.setLocation(100, 210);

        dataProdJL = new JLabel("<html>Data de Inicio <br> de Produção dd/MM/YYYY:</html>");
        dataProdJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataProdJL.setSize(150, 50);
        dataProdJL.setLocation(10, 330);

        dataProdJTF = new JTextField();
        dataProdJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataProdJTF.setSize(150, 20);
        dataProdJTF.setLocation(110, 363);

        String[] validade = {"Sim", "Não"};

        comboBox = new JComboBox(validade);

        comboBox.addItemListener(this);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        comboBox.setSize(100, 20);
        comboBox.setLocation(50, 180);


        dataValidadeJL = new JLabel("<html>Data de Inicio <br> de Válidade dd/MM/YYYY:</html>");
        dataValidadeJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataValidadeJL.setSize(150, 50);
        dataValidadeJL.setLocation(10, 330);

        dataValidadeJTF = new JTextField();
        dataValidadeJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataValidadeJTF.setSize(150, 20);
        dataValidadeJTF.setLocation(110, 363);

        stockJL = new JLabel("Stock");
        stockJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        stockJL.setSize(100, 20);
        stockJL.setLocation(40, 240);

        stockJTF = new JTextField();
        stockJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        stockJTF.setSize(150, 20);
        stockJTF.setLocation(100, 240);

        categoriaJL = new JLabel("categoria");
        categoriaJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        categoriaJL.setSize(100, 20);
        categoriaJL.setLocation(30, 270);

        categoriaJTF = new JTextField();
        categoriaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        categoriaJTF.setSize(150, 20);
        categoriaJTF.setLocation(100, 270);

        classificacaoJL = new JLabel("classificacao");
        classificacaoJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        classificacaoJL.setSize(100, 20);
        classificacaoJL.setLocation(30, 300);

        classificacaoJTF = new JTextField();
        classificacaoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        classificacaoJTF.setSize(150, 20);
        classificacaoJTF.setLocation(80, 300);


        container.add(mainJL);

        container.add(designacaoJL);
        container.add(designacaoJTF);

        container.add(fabricanteJL);
        container.add(fabricanteJTF);

        container.add(pesoJL);
        container.add(pesoJTF);

        container.add(precoJL);
        container.add(precoJTF);

        container.add(comboBox);

        container.add(loteJL);
        container.add(loteJTF);

        container.add(stockJL);
        container.add(stockJTF);

        container.add(dataValidadeJL);
        container.add(dataValidadeJTF);

        container.add(dataProdJL);
        container.add(dataProdJTF);


        container.add(categoriaJL);
        container.add(categoriaJTF);

        container.add(classificacaoJL);
        container.add(classificacaoJTF);


        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("VoltarMenuFuncionario");
        sairBTN.addActionListener(event);
        sairBTN.setSize(100, 20);
        sairBTN.setLocation(50, 400);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        entrarBTN = new JButton("Novo Produto");
        entrarBTN.setActionCommand("adicionarNovoProduto");
        entrarBTN.addActionListener(event);
        entrarBTN.setSize(150, 20);
        entrarBTN.setLocation(180, 400);
        entrarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        container.add(sairBTN);
        container.add(entrarBTN);


        this.setSize(350, 480);
        this.setLocationRelativeTo(null);
        this.setTitle("Novo Produto");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

    /**
     * Retorna para a Main o formulario para a criação de um novo produto
     * @return form contendo todas as variaveis para a criação de um novo Produto
     */
    public String[] getForm() {

        String[] form = new String[10];
        form[0] = designacaoJTF.getText();
        form[1] = fabricanteJTF.getText();
        form[2] = pesoJTF.getText();
        form[3] = precoJTF.getText();
        form[4] = loteJTF.getText();
        form[5] = stockJTF.getText();
        form[6] = categoriaJTF.getText();
        form[7] = classificacaoJTF.getText();
        form[8] = dataProdJTF.getText();
        if(comboBox.getSelectedIndex() == 1) {
            form[9] = dataValidadeJTF.getText();
        }else {
            form[9] = null;
        }
        return form;

    }

    /**
     * Caso o Produto tenha data de validade ou não o interface deve ser alterada disponibilizando mais um campo a ser preenchido
     * @param e ItemEvent que fica a escuta se o item foi alterado ou não
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (comboBox.getSelectedIndex() == 2) {
                container.add(dataValidadeJL);
                container.add(dataValidadeJTF);

                this.setSize(350, 550);

            } else {
                container.remove(dataValidadeJL);
                container.remove(dataValidadeJTF);

            }
            this.repaint();
        }
    }
}

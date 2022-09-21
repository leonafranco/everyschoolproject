package UI.Pesquisas;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

public class PesquisarEncomendasTempoUI extends JFrame {

    private final Container container;
    private final JLabel mainJL, dataInicialJP, dataFinalJP;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JTextField dataFinalJTF, dataInicioJTF;
    private final JPanel bottomJP, topJP;
    private final JButton voltarBTN, pesquisaBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public PesquisarEncomendasTempoUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("Identificador   Custo   Estado");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);

        JLabel spaceLabel = new JLabel("                                  ");
        spaceLabel.setSize(100, 20);

        voltarBTN = new JButton("voltar");
        voltarBTN.setActionCommand("voltarPesquisarEncomendasTempoUI");
        voltarBTN.addActionListener(event);
        voltarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topJP.add(mainJL);
        topJP.add(spaceLabel);
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

        dataInicialJP = new JLabel("Data inicial:");
        dataInicialJP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataInicialJP.setSize(50, 20);

        dataInicioJTF = new JTextField();
        dataInicioJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataInicioJTF.setPreferredSize(new Dimension(100, 30));

        dataFinalJP = new JLabel("Data final:");
        dataFinalJP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataFinalJP.setSize(100, 20);

        dataFinalJTF = new JTextField();
        dataFinalJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataFinalJTF.setPreferredSize(new Dimension(100, 30));

        pesquisaBTN = new JButton("Pesquisar");
        pesquisaBTN.setActionCommand("pesquisarEncomendasTempoUIPesquisa");
        pesquisaBTN.addActionListener(event);
        pesquisaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        bottomJP.add(dataFinalJP);
        bottomJP.add(dataFinalJTF);
        bottomJP.add(dataInicialJP);
        bottomJP.add(dataInicioJTF);
        bottomJP.add(pesquisaBTN);




        container.add(topJP, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(bottomJP, BorderLayout.SOUTH);




        this.setSize(500, 500);
        this.setTitle("Pesquisar");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

    /**
     * Metodo que coloca o valor das JTextField tipo e JTF pesquisa dentro de um array
     * @return retorna o array
     */
    public String[] getPesquisa() {
        String[] form = new String[2];
        form[0] = dataInicioJTF.getText();
        form[1] = dataFinalJTF.getText();

        return form;
    }

    /**
     * Metodo que altera o valor da JtexArea principal que mostra a lista
     * @param lista Lista que será colocada dentro da JtextArea
     */
    public void setLista(String lista) {
        jTextArea.setText(lista);
    }

}

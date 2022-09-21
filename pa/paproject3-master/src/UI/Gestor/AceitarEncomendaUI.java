package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que permite a Aceitação de uma nova encomenda por parte do Gestor
 */
public class AceitarEncomendaUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JTextField skuJTF;
    private final JPanel bottomJP, topJP;
    private final JButton aceitarButton, voltarBTN, recusarBTN;

    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param lista Lista de todas as encomendas a espera de ser aceites
     */
    public AceitarEncomendaUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,70,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("IDENTIFICADOR CUSTO ESTADO");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);

        voltarBTN = new JButton("voltar");
        voltarBTN.setActionCommand("voltarLog");
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


        aceitarButton = new JButton("Aceitar");
        aceitarButton.setActionCommand("AceitarNovaEncomenda");
        aceitarButton.addActionListener(event);
        aceitarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        recusarBTN = new JButton("Recusar");
        recusarBTN.setActionCommand("RecusarNovaEncomenda");
        recusarBTN.addActionListener(event);
        recusarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomJP.add(aceitarButton);
        bottomJP.add(recusarBTN);
        bottomJP.add(skuJTF);



        container.add(topJP, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(bottomJP, BorderLayout.SOUTH);




        this.setSize(500, 500);
        this.setTitle("Aceitar Encomenda");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

    /**
     * metodo que pega o identificador escrito na identificadorJTF
     * @return String contendo o identificador
     */
    public String getEncomenda() {
         return skuJTF.getText();
    }
}

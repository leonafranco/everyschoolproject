package UI.Pesquisas;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

public class PesquisarUtilizadoresUI extends JFrame{

    private final Container container;
    private final JLabel mainJL, tipoJP, pesquisaJP;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JTextField pesquisaJTF, tipoJTF;
    private final JPanel bottomJP, topJP;
    private final JButton pesquisarButton, voltarBTN;


    public PesquisarUtilizadoresUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,70,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("Nome   Login   Tipo");
//        mainJL.setLayout(new BorderLayout(BorderLayout.NORTH));
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);

        voltarBTN = new JButton("voltar");
        voltarBTN.setActionCommand("voltarPesquisarUtilizadoresUI");
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

        tipoJP = new JLabel("Tipo:");
        tipoJP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tipoJP.setSize(50, 20);

        tipoJTF = new JTextField();
        tipoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tipoJTF.setPreferredSize(new Dimension(100, 30));

        pesquisaJP = new JLabel("Pesquisa:");
        pesquisaJP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pesquisaJP.setSize(100, 20);

        pesquisaJTF = new JTextField();
        pesquisaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pesquisaJTF.setPreferredSize(new Dimension(100, 30));

        pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.setActionCommand("pesquisarUtilizadoresUI");
        pesquisarButton.addActionListener(event);
        pesquisarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomJP.add(pesquisaJP);
        bottomJP.add(pesquisaJTF);
        bottomJP.add(tipoJP);
        bottomJP.add(tipoJTF);
        bottomJP.add(pesquisarButton);



        container.add(topJP, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(bottomJP, BorderLayout.SOUTH);




        this.setSize(500, 500);
        this.setTitle("Logs");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

    public String[] getPesquisa() {
        String[] form = new String[2];
        form[0] = tipoJTF.getText();
        form[1] = pesquisaJTF.getText();

        return form;
    }

    public void setLista(String lista) {
        jTextArea.setText(lista);
    }

}


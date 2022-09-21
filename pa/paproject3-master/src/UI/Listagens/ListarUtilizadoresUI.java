package UI.Listagens;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de listagem de todos os utilizadores
 */
public class ListarUtilizadoresUI extends JFrame{
    private final Container container;
    private final JLabel mainJL;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JPanel bottomJP, topJP;
    private final JButton descBTN, ascBTN, voltarBTN;

    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public ListarUtilizadoresUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,70,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("Nome    Login    Tipo");
//        mainJL.setLayout(new BorderLayout(BorderLayout.NORTH));
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);

        voltarBTN = new JButton("voltar");
        voltarBTN.setActionCommand("voltarListarUtilizadores");
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



        ascBTN = new JButton("Ascendente");
        ascBTN.setActionCommand("ascListaUtilizadores");
        ascBTN.addActionListener(event);
        ascBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        descBTN = new JButton("Descendente");
        descBTN.setActionCommand("descListaUtilizadores");
        descBTN.addActionListener(event);
        descBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomJP.add(ascBTN);
        bottomJP.add(descBTN);


        container.add(topJP, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(bottomJP, BorderLayout.SOUTH);




        this.setSize(500, 500);
        this.setTitle("Listar Utilizadores");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }

    /**
     * Metodo de modificação da JTextArea para colocar por ordem decrescente ou Crescente a lista
     * @param lista Parametro de lista modificado por ordem crescente ou decrescente
     */
    public void setLista(String lista) {
        jTextArea.setText(lista);
    }
}

package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe para visualizar todos os Logs da Aplicação
 */
public class LogUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JTextField pesquisaJTF;
    private final JPanel bottomJP, topJP;
    private final JButton pesquisarButton, voltarBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param lista lista de todos os logs da aplicação
     */
    public LogUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,70,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("Dia    Hora    Utilizador    Acção");
//        mainJL.setLayout(new BorderLayout(BorderLayout.NORTH));
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
//        jTextArea.setSize(400, 300);
//        jTextArea.setLocation(40, 10);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.setText(lista);

        scrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setPreferredSize(new Dimension(100, 100));
//        scrollPane.setLocation(350,100);

        bottomJP = new JPanel(new FlowLayout());
        bottomJP.setPreferredSize(new Dimension(50,50));


        pesquisaJTF = new JTextField();
        pesquisaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pesquisaJTF.setPreferredSize(new Dimension(360, 30));

        pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.setActionCommand("pesquisarLogUtilizador");
        pesquisarButton.addActionListener(event);
//        pesquisarButton.setSize(100, 20);
//        pesquisarButton.setLocation(50,210);
        pesquisarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomJP.add(pesquisarButton);
        bottomJP.add(pesquisaJTF);








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

    /**
     * Devolve um metodo de procurar um pessoa para ver os seus logs
     * @return a String contida na JTextField PesquisaJTF
     */
    public String getPesquisa() {
        return pesquisaJTF.getText();
    }

}

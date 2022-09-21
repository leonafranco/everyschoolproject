package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe para ativação de novas contas
 */
public class AtivarContaUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JTextArea jTextArea;
    private final JScrollPane scrollPane;
    private final JTextField pesquisaJTF;
    private final JPanel bottomJP, topJP;
    private final JButton ativarButton, voltarBTN, recusarButton;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param lista Recebe da main a lista de utilizadores que estão pendestes de ativação de conta
     * */
    public AtivarContaUI(Main event, String lista) {

        container = getContentPane();
        container.setLayout(new BorderLayout());

        topJP = new JPanel(new FlowLayout(FlowLayout.LEFT,100,0));
        topJP.setPreferredSize(new Dimension(50,30));


        mainJL = new JLabel("Nome    Login    Tipo");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);

        voltarBTN = new JButton("voltar");
        voltarBTN.setActionCommand("voltarMenuGestor");
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


        pesquisaJTF = new JTextField();
        pesquisaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pesquisaJTF.setPreferredSize(new Dimension(300, 30));

        ativarButton = new JButton("Ativar");
        ativarButton.setActionCommand("ativarContaButton");
        ativarButton.addActionListener(event);
        ativarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        recusarButton = new JButton("Recusar");
        recusarButton.setActionCommand("recusarContaButton");
        recusarButton.addActionListener(event);
        recusarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomJP.add(recusarButton);
        bottomJP.add(ativarButton);
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
     * Informa qual o utilizador que deve ser ativado
     * @return o valor da pesquisaJTF
     */
    public String getPesquisa() {
        return pesquisaJTF.getText();
    }
}

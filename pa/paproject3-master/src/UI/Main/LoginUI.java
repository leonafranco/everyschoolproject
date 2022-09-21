package UI.Main;

import com.company.Main;

import javax.swing.*;
import java.awt.*;


/**
 * Classe de loginUI, é chamada assim que o Utilizador decide fazer login
 */
public class LoginUI extends JFrame {

    private final Container container;
    private final JPanel mainJP, topJP, botJP, passwordJP, loginTextJP, passwordTextJP;
    private JTextField loginTextJTF;
    private final JButton entrarBTN, sairBTN;
    private final JPasswordField passwordJPF;



    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public LoginUI(Main event) {


        container = getContentPane();
        container.setLayout(new BorderLayout());

        GridLayout gridLayout = new GridLayout(3,2);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        mainJP = new JPanel(gridLayout);


        topJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topJP.add(new JLabel("Autenticação"));

        loginTextJP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginTextJP.add(new JLabel("Login: "));
        loginTextJTF = new JTextField(1);
        loginTextJP.add(loginTextJTF);

        passwordJP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        passwordJP.add(new JLabel("Password: "));

        passwordTextJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordJPF = new JPasswordField(1);
        passwordTextJP.add(passwordJPF);


        botJP = new JPanel();

        JPanel sairBTNJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("VoltarMenuLogin");
        sairBTN.addActionListener(event);
        sairBTN.setPreferredSize(new Dimension(90,28));
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sairBTNJP.add(sairBTN);

        JPanel entrarBTNJP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        entrarBTN = new JButton("Entrar");
        entrarBTN.setActionCommand("Entrar");
        entrarBTN.addActionListener(event);
        entrarBTN.setPreferredSize(new Dimension(90,28));
        entrarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        entrarBTNJP.add(entrarBTN);

        mainJP.add(loginTextJP);
        mainJP.add(loginTextJTF);

        mainJP.add(passwordJP);
        mainJP.add(passwordJPF);



        botJP.add(sairBTNJP);
        botJP.add(entrarBTNJP);



        container.add(topJP, BorderLayout.NORTH);
        container.add(mainJP, BorderLayout.CENTER);
        container.add(botJP, BorderLayout.SOUTH);


        this.setSize(350,200);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);

    }

    /**
     * Retira o valor da LoginTextJTF
     * @return para a main o valor do loginTextJTF
     */
    public String getLogin() {
        return loginTextJTF.getText();
    }
    /**
     * Retira o valor da passwordJPF
     * @return para a main o valor do passwordJPF
     */
    public String getPassword() {
        return String.valueOf(passwordJPF.getPassword());
    }
}

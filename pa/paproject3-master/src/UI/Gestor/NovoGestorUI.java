package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe para a criação de um novo Gestor
 */
public class NovoGestorUI extends JFrame {

    private final Container container;
    private final JLabel loginJL, mainJL, passwordJL, nameJL, emailJL;
    private final JTextField loginJTF, nameJTF, emailJTF;
    private final JPasswordField passwordJPF;
    private final JButton entrarBTN, sairBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public NovoGestorUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Criar Novo Gestor");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100, 20);
        mainJL.setLocation(140, 20);


        loginJL = new JLabel("Login");
        loginJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJL.setSize(50, 20);
        loginJL.setLocation(50, 60);

        loginJTF = new JTextField();
        loginJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJTF.setSize(150, 20);
        loginJTF.setLocation(100, 60);

        passwordJL = new JLabel("Password");
        passwordJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordJL.setSize(100, 20);
        passwordJL.setLocation(35, 90);

        passwordJPF = new JPasswordField();
        //   passwordJPF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordJPF.setSize(150, 20);
        passwordJPF.setLocation(100, 90);

        nameJL = new JLabel("Nome");
        nameJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameJL.setSize(50, 20);
        nameJL.setLocation(50, 120);

        nameJTF = new JTextField();
        nameJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameJTF.setSize(150, 20);
        nameJTF.setLocation(100, 120);

        emailJL = new JLabel("Email");
        emailJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJL.setSize(50, 20);
        emailJL.setLocation(50, 150);

        emailJTF = new JTextField();
        emailJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJTF.setSize(150, 20);
        emailJTF.setLocation(100, 150);

        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("voltarMenuGestor");
        sairBTN.addActionListener(event);
        sairBTN.setSize(100, 20);
        sairBTN.setLocation(50,180);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        entrarBTN = new JButton("Criar Conta");
        entrarBTN.setActionCommand("criarNovoGestor");
        entrarBTN.addActionListener(event);
        entrarBTN.setSize(100, 20);
        entrarBTN.setLocation(180,180);
        entrarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));



        container.add(mainJL);

        container.add(loginJL);
        container.add(loginJTF);

        container.add(passwordJL);
        container.add(passwordJPF);

        container.add(nameJL);
        container.add(nameJTF);

        container.add(emailJL);
        container.add(emailJTF);

        container.add(sairBTN);
        container.add(entrarBTN);

        this.setSize(350, 300);
        this.setLocationRelativeTo(null);
        this.setTitle("Novo Gestor");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);

    }

    /**
     * Coloca todos as variaveis escritas nas JTextField dentro de um array Form
     * @return para a main o array form contendo todos os valores das JTextField
     */
    public String[] getForm() {

        String [] form = new String[4];
        form[0] = loginJTF.getText();
        form[1] = String.valueOf(passwordJPF.getPassword());
        form[2] = nameJTF.getText();
        form[3] = emailJTF.getText();

        return form;

    }

}
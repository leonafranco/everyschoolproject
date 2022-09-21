package UI.Gestor;

import com.company.Main;
import entities.Utilizador;

import javax.swing.*;
import java.awt.*;

/**
 * Classe para alteração de dados de um gestor
 */
public class AlterarDadosGestorUI extends JFrame {

    private final Container container;
    private final JLabel loginJL, mainJL, passwordJL, nameJL, emailJL;
    private final JTextField loginJTF, nameJTF, emailJTF;
    private final JButton entrarBTN, sairBTN, pesquisarGestorBTN;
    private final JPasswordField passwordJPF;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param utilizador Recebe da main o utilizador logado neste preciso momento
     * */
    public AlterarDadosGestorUI(Main event, Utilizador utilizador) {
        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Alterar Dados Gestor");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(200,20);
        mainJL.setLocation(100,20);


        loginJL = new JLabel("Login");
        loginJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJL.setSize(50,20);
        loginJL.setLocation(50,60);

        pesquisarGestorBTN = new JButton("Pesquisar");
        pesquisarGestorBTN.setActionCommand("pesquisarGestorAlterarDados");
        pesquisarGestorBTN.addActionListener(event);
        pesquisarGestorBTN.setSize(100, 20);
        pesquisarGestorBTN.setLocation(250,60);
        pesquisarGestorBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginJTF = new JTextField();
        loginJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJTF.setSize(150,20);
        loginJTF.setLocation(100,60);
        if(utilizador != null) {
            loginJTF.setText(utilizador.getLogin());
        }

        passwordJL = new JLabel("Password");
        passwordJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordJL.setSize(100,20);
        passwordJL.setLocation(35,90);

        passwordJPF = new JPasswordField();
        //   passwordJPF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordJPF.setSize(150,20);
        passwordJPF.setLocation(100,90);

        nameJL = new JLabel("Nome");
        nameJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameJL.setSize(50,20);
        nameJL.setLocation(50,120);

        nameJTF = new JTextField();
        nameJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameJTF.setSize(150,20);
        nameJTF.setLocation(100,120);
        if(utilizador != null)
            nameJTF.setText(utilizador.getNome());


        emailJL = new JLabel("Email");
        emailJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJL.setSize(50,20);
        emailJL.setLocation(50,150);

        emailJTF = new JTextField();
        emailJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJTF.setSize(150,20);
        emailJTF.setLocation(100,150);
        if(utilizador != null)
            emailJTF.setText(utilizador.getEmail());


        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("VoltarmenuAlterarDados");
        sairBTN.addActionListener(event);
        sairBTN.setSize(120, 20);
        sairBTN.setLocation(50,210);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        entrarBTN = new JButton("Alterar Dados");
        entrarBTN.setActionCommand("updateGestor");
        entrarBTN.addActionListener(event);
        entrarBTN.setSize(120, 20);
        entrarBTN.setLocation(180,210);
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

        container.add(pesquisarGestorBTN);



        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setTitle("Alterar Dados");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);
    }

    /**
     * Metodo que retira todos os valores das JtextField
     * @return Form de todos os dados alterados e não alterado do utilizador
     */
    public String[] getForm() {
        String [] form = new String[4];
        form[0] = loginJTF.getText();
        form[1] = String.valueOf(passwordJPF.getPassword());
        form[2] = nameJTF.getText();
        form[3] = emailJTF.getText();

        return form;
    }

    /**
     * Classe permite a pesquisa por um novo utilizador aqui possibilitando a procura por login
     * @return o valor da LoginJTF
     */
    public String getLogin() {
        return loginJTF.getText();
    }


}

package UI.Main;

import com.company.Main;
import entities.Cliente;
import entities.Utilizador;

import javax.swing.*;
import java.awt.*;

/**
 * Classe comum para cliente e gestor de alteração de Dados
 */
public class AlterarDadosClienteUI extends JFrame {

    private final Container container;
    private final JLabel loginJL, mainJL, passwordJL, nameJL, emailJL, contribuinteJL, contactJL, moradaJL;
    private final JTextField loginJTF, nameJTF, emailJTF, contribuinteJTF, contactoJTF, moradaJTF;
    private final JButton entrarBTN, sairBTN, pesquisarClienteBTN;
    private final JPasswordField passwordJPF;

    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param cliente Recebe do main o cliente que está logado, caso seja um gestor, recebe null
     */
    public AlterarDadosClienteUI(Main event, Cliente cliente) {
        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Alterar Dados Cliente");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(200,20);
        mainJL.setLocation(100,20);


        loginJL = new JLabel("Login");
        loginJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJL.setSize(50,20);
        loginJL.setLocation(50,60);

        pesquisarClienteBTN = new JButton("Pesquisar");
        pesquisarClienteBTN.setActionCommand("pesquisarClienteAlterarDados");
        pesquisarClienteBTN.addActionListener(event);
        pesquisarClienteBTN.setSize(100, 20);
        pesquisarClienteBTN.setLocation(250,60);
        pesquisarClienteBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginJTF = new JTextField();
        loginJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJTF.setSize(150,20);
        loginJTF.setLocation(100,60);
        if(cliente != null) {
            loginJTF.setText(cliente.getLogin());
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
        if(cliente != null) {
            nameJTF.setText(cliente.getNome());
        }


        emailJL = new JLabel("Email");
        emailJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJL.setSize(50,20);
        emailJL.setLocation(50,150);

        emailJTF = new JTextField();
        emailJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJTF.setSize(150,20);
        emailJTF.setLocation(100,150);
        if(cliente != null) {
            emailJTF.setText(cliente.getEmail());
        }

        contribuinteJL = new JLabel("Contribuinte");
        contribuinteJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contribuinteJL.setSize(100,20);
        contribuinteJL.setLocation(27,180);

        contribuinteJTF = new JTextField();
        contribuinteJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contribuinteJTF.setSize(150,20);
        contribuinteJTF.setLocation(100,180);
        if(cliente != null) {
            contribuinteJTF.setText(String.valueOf(cliente.getNumeroContribuinte()));
        }

        contactJL = new JLabel("Contacto");
        contactJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contactJL.setSize(100,20);
        contactJL.setLocation(40,210);

        contactoJTF = new JTextField();
        contactoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contactoJTF.setSize(150,20);
        contactoJTF.setLocation(100,210);
        if(cliente != null) {
            contactoJTF.setText(String.valueOf(cliente.getNumeroContribuinte()));
        }

        moradaJL = new JLabel("Morada");
        moradaJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moradaJL.setSize(50,20);
        moradaJL.setLocation(50,240);

        moradaJTF = new JTextField();
        moradaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moradaJTF.setSize(150,20);
        moradaJTF.setLocation(100,240);
        if(cliente != null) {
            moradaJTF.setText(cliente.getMorada());
        }


        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("VoltarmenuAlterarDados");
        sairBTN.addActionListener(event);
        sairBTN.setSize(120, 20);
        sairBTN.setLocation(50,300);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        entrarBTN = new JButton("Alterar Dados");
        entrarBTN.setActionCommand("updateCliente");
        entrarBTN.addActionListener(event);
        entrarBTN.setSize(120, 20);
        entrarBTN.setLocation(180,300);
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

        container.add(contribuinteJL);
        container.add(contribuinteJTF);

        container.add(contactJL);
        container.add(contactoJTF);

        container.add(moradaJL);
        container.add(moradaJTF);

        container.add(sairBTN);
        container.add(entrarBTN);
        container.add(pesquisarClienteBTN);



        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setTitle("Alterar Dados Cliente");
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
        String [] form = new String[7];
        form[0] = loginJTF.getText();
        form[1] = String.valueOf(passwordJPF.getPassword());
        form[2] = nameJTF.getText();
        form[3] = emailJTF.getText();
        form[4] = contribuinteJTF.getText();
        form[5] = contactoJTF.getText();
        form[6] = moradaJTF.getText();

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

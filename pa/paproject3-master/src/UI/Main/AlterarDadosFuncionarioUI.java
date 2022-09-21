package UI.Main;

import com.company.Main;
import entities.Funcionario;
import entities.Utilizador;

import javax.swing.*;
import java.awt.*;

/**
 * Classe comum para Funcionario e gestor de alteração de Dados
 */
public class AlterarDadosFuncionarioUI extends JFrame {

    private final Container container;
    private final JLabel loginJL, mainJL, passwordJL, nameJL, emailJL, especializacaoJL , contribuinteJL, contactJL, moradaJL, dataInicioJL;
    private final JTextField loginJTF, nameJTF, emailJTF, contribuinteJTF, contactoJTF, moradaJTF, dataInicioJTF;
    private final JButton entrarBTN, sairBTN, pesquisarClienteBTN;
    private final JRadioButton armazenistaRB, estafetaRB;
    private final ButtonGroup buttonGroup;
    private final JPasswordField passwordJPF;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param funcionario Recebe do main o Funcionario que está logado, caso seja um gestor, recebe null
     */
    public AlterarDadosFuncionarioUI(Main event, Funcionario funcionario) {
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

        loginJTF = new JTextField();
        loginJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJTF.setSize(150,20);
        loginJTF.setLocation(100,60);
        if(funcionario != null)
            loginJTF.setText(funcionario.getLogin());


        pesquisarClienteBTN = new JButton("Pesquisar");
        pesquisarClienteBTN.setActionCommand("pesquisarFuncionarioAlterarDados");
        pesquisarClienteBTN.addActionListener(event);
        pesquisarClienteBTN.setSize(100, 20);
        pesquisarClienteBTN.setLocation(250,60);
        pesquisarClienteBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
        if(funcionario != null)
            nameJTF.setText(funcionario.getNome());


        emailJL = new JLabel("Email");
        emailJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJL.setSize(50,20);
        emailJL.setLocation(50,150);

        emailJTF = new JTextField();
        emailJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJTF.setSize(150,20);
        emailJTF.setLocation(100,150);
        if(funcionario != null)
            emailJTF.setText(funcionario.getEmail());

        contribuinteJL = new JLabel("Contribuinte");
        contribuinteJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contribuinteJL.setSize(100,20);
        contribuinteJL.setLocation(27,210);

        contribuinteJTF = new JTextField();
        contribuinteJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contribuinteJTF.setSize(150,20);
        contribuinteJTF.setLocation(100,210);
        if(funcionario != null)
            contribuinteJTF.setText(String.valueOf(funcionario.getNumeroContribuinte()));


        contactJL = new JLabel("Contacto");
        contactJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contactJL.setSize(100,20);
        contactJL.setLocation(40,240);

        contactoJTF = new JTextField();
        contactoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contactoJTF.setSize(150,20);
        contactoJTF.setLocation(100,240);
        if(funcionario != null)
            contactoJTF.setText(String.valueOf(funcionario.getContacto()));


        moradaJL = new JLabel("Morada");
        moradaJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moradaJL.setSize(50,20);
        moradaJL.setLocation(50,270);

        moradaJTF = new JTextField();
        moradaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moradaJTF.setSize(150,20);
        moradaJTF.setLocation(100,270);
        if(funcionario != null)
            moradaJTF.setText(funcionario.getMorada());

        especializacaoJL = new JLabel();
        especializacaoJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        especializacaoJL.setSize(50,20);
        especializacaoJL.setLocation(50,300);

        armazenistaRB = new JRadioButton("armazenista");
        armazenistaRB.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        armazenistaRB.setSelected(false);
        armazenistaRB.setSize(100, 20);
        armazenistaRB.setLocation(100, 300);

        estafetaRB = new JRadioButton("estafeta");
        estafetaRB.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        estafetaRB.setSelected(false);
        estafetaRB.setSize(100, 20);
        estafetaRB.setLocation(200, 300);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(armazenistaRB);
        buttonGroup.add(estafetaRB);

        dataInicioJL = new JLabel("Data de Inicio de Atividade");
        dataInicioJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataInicioJL.setSize(50,20);
        dataInicioJL.setLocation(50,330);

        dataInicioJTF = new JTextField();
        dataInicioJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataInicioJTF.setSize(150,20);
        dataInicioJTF.setLocation(100,330);
        if(funcionario != null)
            dataInicioJTF.setText(String.valueOf(funcionario.getDataDeInicio()));


        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("VoltarmenuAlterarDados");
        sairBTN.addActionListener(event);
        sairBTN.setSize(120, 20);
        sairBTN.setLocation(50,360);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        entrarBTN = new JButton("Alterar Dados");
        entrarBTN.setActionCommand("updateFuncionario");
        entrarBTN.addActionListener(event);
        entrarBTN.setSize(120, 20);
        entrarBTN.setLocation(180,360);
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

        container.add(especializacaoJL);
        container.add(armazenistaRB);
        container.add(estafetaRB);
        container.add(dataInicioJL);
        container.add(dataInicioJTF);

        container.add(sairBTN);
        container.add(entrarBTN);



        this.setSize(350,300);
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
        String [] form = new String[8];
        form[0] = loginJTF.getText();
        form[1] = String.valueOf(passwordJPF.getPassword());
        form[2] = nameJTF.getText();
        form[3] = emailJTF.getText();
        form[4] = contribuinteJTF.getText();
        form[5] = contactoJTF.getText();
        form[6] = moradaJTF.getText();
        if(armazenistaRB.isSelected()) {
            form[7] = "armazenista";
        }else {
            form[7] = "estafeta";
        }
        form[8] = dataInicioJTF.getText();


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

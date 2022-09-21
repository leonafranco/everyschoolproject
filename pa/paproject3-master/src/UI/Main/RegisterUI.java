package UI.Main;

import com.company.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classe que permite o utilizador criar uma conta dos 3 tipos, gestor, cliente ou funcionario
 */
public class RegisterUI extends JFrame implements ItemListener {

    private final Container container;
    private final JLabel loginJL, mainJL, passwordJL, nameJL, emailJL, especializacaoJL , contribuinteJL, contactJL, moradaJL, dataInicioJL, fotoJL;
   // private JLabel contribuinteJL, contactJL, moradaJL;
    private final JTextField loginJTF, nameJTF, emailJTF, contribuinteJTF, contactoJTF, moradaJTF, dataInicioJTF, fotoLinkJTF;
    private final JRadioButton armazenistaRB, estafetaRB;
    // private JTextField contribuinteJTF, contactoJTF, moradaJTF;
    private final ButtonGroup buttonGroup;
    private final JButton entrarBTN, sairBTN, adicionarFotoBTN;
    private final JPasswordField passwordJPF;
    private final JComboBox comboBox;
    private final boolean aIsFirstAccount;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     * @param isFirstAccount verifica se o utilizador é a primeira conta ou não
     * */
    public RegisterUI(Main event, boolean isFirstAccount) {

        aIsFirstAccount = isFirstAccount;

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Registo");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(100,20);
        mainJL.setLocation(140,20);


        loginJL = new JLabel("Login");
        loginJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJL.setSize(50,20);
        loginJL.setLocation(50,60);

        loginJTF = new JTextField();
        loginJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginJTF.setSize(150,20);
        loginJTF.setLocation(100,60);


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

        emailJL = new JLabel("Email");
        emailJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJL.setSize(50,20);
        emailJL.setLocation(50,150);

        emailJTF = new JTextField();
        emailJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailJTF.setSize(150,20);
        emailJTF.setLocation(100,150);


        String [] tipo = {"Gestor", "Cliente", "Funcionário"};
        String [] onlyGestor = {"Gestor"};


        if(aIsFirstAccount == false) {
            comboBox = new JComboBox(onlyGestor);
        }else {
            comboBox = new JComboBox(tipo);
        }
        comboBox.addItemListener(this);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        comboBox.setSize(100,20);
        comboBox.setLocation(50,180);


        contribuinteJL = new JLabel("Contribuinte");
        contribuinteJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contribuinteJL.setSize(100,20);
        contribuinteJL.setLocation(27,210);

        contribuinteJTF = new JTextField();
        contribuinteJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contribuinteJTF.setSize(150,20);
        contribuinteJTF.setLocation(100,210);

        contactJL = new JLabel("Contacto");
        contactJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contactJL.setSize(100,20);
        contactJL.setLocation(40,240);

        contactoJTF = new JTextField();
        contactoJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contactoJTF.setSize(150,20);
        contactoJTF.setLocation(100,240);

        moradaJL = new JLabel("Morada");
        moradaJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moradaJL.setSize(50,20);
        moradaJL.setLocation(50,270);

        moradaJTF = new JTextField();
        moradaJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moradaJTF.setSize(150,20);
        moradaJTF.setLocation(100,270);

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

        dataInicioJL = new JLabel("<html>Data de Inicio <br> de Atividade dd/MM/YYYY:</html>");
        dataInicioJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataInicioJL.setSize(150,50);
        dataInicioJL.setLocation(10,330);

        dataInicioJTF = new JTextField();
        dataInicioJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dataInicioJTF.setSize(150,20);
        dataInicioJTF.setLocation(110,363);

        fotoLinkJTF = new JTextField();
        fotoLinkJTF.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fotoLinkJTF.setSize(150,20);
        fotoLinkJTF.setLocation(300,160);

        fotoJL = new JLabel();
        fotoJL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fotoJL.setSize(150,100);
        fotoJL.setLocation(300,40);





        container.add(mainJL);

        container.add(loginJL);
        container.add(loginJTF);

        container.add(passwordJL);
        container.add(passwordJPF);

        container.add(nameJL);
        container.add(nameJTF);

        container.add(emailJL);
        container.add(emailJTF);

        container.add(fotoLinkJTF);
        container.add(fotoJL);

        container.add(comboBox);

//        container.add(contribuinteJL);
//        container.add(contribuinteJTF);
//
//        container.add(contactJL);
//        container.add(contactoJTF);
//
//        container.add(moradaJL);
//        container.add(moradaJTF);
//
//        container.add(especializacaoJL);
//        container.add(armazenistaRB);
//        container.add(estafetaRB);

        sairBTN = new JButton("Voltar");
        sairBTN.setActionCommand("VoltarMenuRegister");
        sairBTN.addActionListener(event);
        sairBTN.setSize(100, 20);
        sairBTN.setLocation(50,210);
        sairBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        entrarBTN = new JButton("Criar Conta");
        entrarBTN.setActionCommand("CriarNovaConta");
        entrarBTN.addActionListener(event);
        entrarBTN.setSize(100, 20);
        entrarBTN.setLocation(180,210);
        entrarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        adicionarFotoBTN = new JButton("Procurar Foto");
        adicionarFotoBTN.setActionCommand("AdicionaFoto");
        adicionarFotoBTN.addActionListener(event);
        adicionarFotoBTN.setSize(150, 20);
        adicionarFotoBTN.setLocation(300,190);
        adicionarFotoBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));



        container.add(sairBTN);
        container.add(entrarBTN);
        container.add(adicionarFotoBTN);




        this.setSize(500,350);
        this.setLocationRelativeTo(null);
        this.setTitle("Registo");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);


    }


    public void clienteForm() {
        container.remove(especializacaoJL);
        container.remove(armazenistaRB);
        container.remove(estafetaRB);

        container.remove(dataInicioJL);
        container.remove(dataInicioJTF);

        sairBTN.setLocation(50,300);
        entrarBTN.setLocation(180,300);

        this.setSize(500,400);


        this.repaint();

    }

    public void clienteFuncionarioForm() {
        container.add(contribuinteJL);
        container.add(contribuinteJTF);

        container.add(contactJL);
        container.add(contactoJTF);

        container.add(moradaJL);
        container.add(moradaJTF);
    }

    public void funcionarioForm() {
        container.add(especializacaoJL);
        container.add(armazenistaRB);
        container.add(estafetaRB);
        container.add(dataInicioJL);
        container.add(dataInicioJTF);

        sairBTN.setLocation(50,400);
        entrarBTN.setLocation(180,400);

        this.setSize(500,480);


        this.repaint();
    }


    public void gestorForm() {
        container.remove(contribuinteJL);
        container.remove(contribuinteJTF);

        container.remove(contactJL);
        container.remove(contactoJTF);

        container.remove(moradaJL);
        container.remove(moradaJTF);

        container.remove(dataInicioJL);
        container.remove(dataInicioJTF);

        container.remove(especializacaoJL);
        container.remove(armazenistaRB);
        container.remove(estafetaRB);

        this.setSize(500,400);

        entrarBTN.setLocation(180,210);
        sairBTN.setLocation(50,210);

        this.repaint();
    }


    /**
     * Metodo que retira todos os valores das JtextField
     * @return Form de todos os dados alterados e não alterado do utilizador
     */
    public String[] getForm() {

        String [] form = new String[10];
        form[0] = loginJTF.getText();
        form[1] = String.valueOf(passwordJPF.getPassword());
        form[2] = nameJTF.getText();
        form[3] = emailJTF.getText();
        if(aIsFirstAccount == false || comboBox.getSelectedIndex() == 0) {
            form[4] = "Gestor";

        }else if(comboBox.getSelectedIndex() == 1) {
            form[4] = "Cliente";
            form[5] = contribuinteJTF.getText();
            form[6] = contactoJTF.getText();
            form[7] = moradaJTF.getText();

        }else if (comboBox.getSelectedIndex() == 2) {
            form[4] = "Funcionario";
            form[5] = contribuinteJTF.getText();
            form[6] = contactoJTF.getText();
            form[7] = moradaJTF.getText();
            if(armazenistaRB.isSelected()) {
                form[8] = "armazenista";
            }else {
                form[8] = "estafeta";
            }
            form[9] = dataInicioJTF.getText();
        }

        return form;

    }

    /**
     * Metodo que permite adicionar uma foto
     * @param filename Parametro do Path da foto
     * @param icon A foto em si
     */
    public void setFoto(String filename, ImageIcon icon ) {
        fotoLinkJTF.setText(filename);
        fotoJL.setIcon(icon);
    }

    /**
     * Metodo que permite selecionar entre diferentes tipo de conta, Gestor, Funcionario e Cliente cada uma com o seus campos adicionais
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            if(comboBox.getSelectedIndex() == 1) {
                clienteFuncionarioForm();
                clienteForm();
            }else if(comboBox.getSelectedIndex() == 2) {
                clienteFuncionarioForm();
                funcionarioForm();;
            }else if(comboBox.getSelectedIndex() == 0) {
                gestorForm();
            }
        }
    }

}

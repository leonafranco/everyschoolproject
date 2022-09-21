package UI.Gestor;

import com.company.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Classe padrão de Menu, contem os diversos botões que seguem para outras JFrames mais especificas
 */
public class MenuAlterarDadosUI extends JFrame {

    private final Container container;
    private final JLabel mainJL;
    private final JButton alterarDadosGestorBTN, alterarDadosClienteBTN, alterarDadosFuncionarioBTN, pedidoRemocaoContaBTN, verPedidoRemocaoContaBTN, voltarBTN;


    /**
     * Metodo principal que cria todos os componentes dentro da JFrame que a classe herda
     * @param event parametro crucial para enviar para a Main assim que houve um click num botão
     */
    public MenuAlterarDadosUI(Main event) {

        container = getContentPane();
        container.setLayout(null);

        mainJL = new JLabel("Menu Alterar Dados");
        mainJL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainJL.setSize(190,20);
        mainJL.setLocation(110,20);

        alterarDadosGestorBTN = new JButton("Alterar Dados Gestor");
        alterarDadosGestorBTN.setActionCommand("alterarDadosGestor");
        alterarDadosGestorBTN.addActionListener(event);
        alterarDadosGestorBTN.setSize(190, 20);
        alterarDadosGestorBTN.setLocation(100,60);
        alterarDadosGestorBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        alterarDadosClienteBTN = new JButton("Alterar Dados Cliente");
        alterarDadosClienteBTN.setActionCommand("alterarDadosCliente");
        alterarDadosClienteBTN.addActionListener(event);
        alterarDadosClienteBTN.setSize(190, 20);
        alterarDadosClienteBTN.setLocation(100,90);
        alterarDadosClienteBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        alterarDadosFuncionarioBTN = new JButton("Alterar Dados Funcionario");
        alterarDadosFuncionarioBTN.setActionCommand("alterarDadosFuncionario");
        alterarDadosFuncionarioBTN.addActionListener(event);
        alterarDadosFuncionarioBTN.setSize(190, 20);
        alterarDadosFuncionarioBTN.setLocation(100,120);
        alterarDadosFuncionarioBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pedidoRemocaoContaBTN = new JButton("Remover Conta");
        pedidoRemocaoContaBTN.setActionCommand("removerConta");
        pedidoRemocaoContaBTN.addActionListener(event);
        pedidoRemocaoContaBTN.setSize(190, 20);
        pedidoRemocaoContaBTN.setLocation(100,150);
        pedidoRemocaoContaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));


        verPedidoRemocaoContaBTN = new JButton("Pedidos de Remover Conta");
        verPedidoRemocaoContaBTN.setActionCommand("verPedidoRemocaoConta");
        verPedidoRemocaoContaBTN.addActionListener(event);
        verPedidoRemocaoContaBTN.setSize(190, 20);
        verPedidoRemocaoContaBTN.setLocation(100,180);
        verPedidoRemocaoContaBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        voltarBTN = new JButton("Voltar");
        voltarBTN.setActionCommand("voltarMenuGestor");
        voltarBTN.addActionListener(event);
        voltarBTN.setSize(190, 20);
        voltarBTN.setLocation(100,210);
        voltarBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        container.add(mainJL);
        container.add(alterarDadosGestorBTN);
        container.add(alterarDadosClienteBTN);
        container.add(alterarDadosFuncionarioBTN);
        container.add(pedidoRemocaoContaBTN);
        container.add(verPedidoRemocaoContaBTN);
        container.add(voltarBTN);


        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu Gestor");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(event);
    }
}

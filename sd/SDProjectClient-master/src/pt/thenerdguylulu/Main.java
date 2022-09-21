package pt.thenerdguylulu;

import pt.thenerdguylulu.interfaces.ServerInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main extends JFrame {
    public static ServerInterface serverInterface;

    public Main() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setResizable(false);

        try {
            final BufferedImage myImage = ImageIO.read(Main.class.getResource("/pt/thenerdguylulu/resources/table.jpg"));
            this.setContentPane(new ImagePanel(myImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());

        final LoginUI loginUI = new LoginUI();

        this.add(loginUI, BorderLayout.CENTER);
    }


    static class ImagePanel extends JComponent {
        private final Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }


    public static void main(String[] args) {
        // write your code here
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}

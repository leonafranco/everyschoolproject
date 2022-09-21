package pt.thenerdguylulu.model;

import javax.swing.*;
import java.awt.*;

// cada carta é apresentada num JLabel
public class CardLabel extends JLabel {

    // define o icone do label, que é a imagem da carta
    public void setCardImage(String name) {
        ImageIcon im = new ImageIcon(this.getClass().getResource("/pt/thenerdguylulu/resources/cards/" + name + ".png"));
        this.setIcon(im);
    }

    // define se a carta vai ser coberta parciamente por outra
    public void setCardCovered(boolean covered) {
        Icon ic = this.getIcon();
        int cardWidth = ic.getIconWidth();
        int cardHeight = ic.getIconHeight();
        if (covered)
            this.setPreferredSize(new Dimension(20, cardHeight));
        else
            this.setPreferredSize(new Dimension(cardWidth, cardHeight));
    }

}

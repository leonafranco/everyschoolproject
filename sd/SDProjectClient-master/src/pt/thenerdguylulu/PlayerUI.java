package pt.thenerdguylulu;

import pt.thenerdguylulu.model.Card;
import pt.thenerdguylulu.model.Dealer;
import pt.thenerdguylulu.model.Player;
import pt.thenerdguylulu.model.CardLabel;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Iterator;


public class PlayerUI extends JPanel {
    private final GridBagConstraints bgc = new GridBagConstraints();
    private final JButton hitBTN = new JButton("HIT");
    private final JButton standBTN = new JButton("STAND");
    private final ClientRMI clientRMI = ClientRMI.getInstance();

    public PlayerUI(final Player aPlayer) {
        this.buildUI(aPlayer);
    }

    public PlayerUI(final Dealer aDealer) {
        this.buildUI(aDealer);
    }

    private void buildUI(final Object obj) {
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        final JPanel cardsContainer = new JPanel();
        cardsContainer.setLayout(new GridBagLayout());
        cardsContainer.setOpaque(false);

        boolean firstLoop = true;

        if (obj == null)
            return;
        Iterator<Card> it = ((Dealer) obj).getHand().getHand().iterator();

        while (it.hasNext()) {
            final Card card = it.next();

            final CardLabel cardLabel = new CardLabel();
            cardLabel.setCardImage(!(obj instanceof Player) && (firstLoop && ((Dealer) obj).isFirstCardHidden()) ? "bv" : card.getName());
            firstLoop = false;
            cardLabel.setCardCovered(it.hasNext());
            cardsContainer.add(cardLabel);
        }
        bgc.gridx = 0;
        bgc.gridy = 1;
        this.add(cardsContainer, bgc);

        if (obj instanceof Player) {
            this.appendNameChips(((Player) obj).getName(), ((Player) obj).getChips(), ((Player) obj).isPlayerTurn());
            int value = ((Dealer) obj).getHand().getHandValue(false);
            cardValue(value);
            if (((Player) obj).getName().equals(clientRMI.getMe().getName())) {
                this.appendButtons((Player) obj);
            }
        } else if (((Dealer) obj).getHand() != null && ((Dealer) obj).getHand().getHand() != null && ((Dealer) obj).getHand().getHand().size() > 0) {
            this.appendNameChips("Dealer", null, false);
            Dealer dealer = (Dealer) obj;
            cardValue(dealer.getHand().getHandValue(dealer.isFirstCardHidden()));
        }

        if (((Dealer) obj).getHand().getHandValue(false) > 21 || obj instanceof Player && ((Player) obj).getVictoryStatus() == Player.LOSER) {
            JLabel busted = new JLabel();
            busted.setText("Perdeu!");
            setTextWhite(busted);
            bgc.gridx = 0;
            bgc.gridy = 4;
            this.add(busted, bgc);
        }

        if ((obj instanceof Player && ((Dealer) obj).getHand().getHandValue(false) == 21)
                || !((Dealer) obj).isFirstCardHidden() && ((Dealer) obj).getHand().getHandValue(false) == 21) {
            JLabel blackJack = new JLabel();
            blackJack.setText("BlackJack!");
            setTextWhite(blackJack);
            bgc.gridx = 0;
            bgc.gridy = 4;
            this.add(blackJack, bgc);
        } else if (obj instanceof Player && ((Player) obj).getVictoryStatus() == Player.WINNER) {
            JLabel winnerLabel = new JLabel();
            winnerLabel.setText("Parabéns, ganhou!");
            setTextWhite(winnerLabel);
            bgc.gridx = 0;
            bgc.gridy = 4;
            this.add(winnerLabel, bgc);
        } else if (obj instanceof Player && ((Player) obj).getVictoryStatus() == Player.DRAW) {
            JLabel winnerLabel = new JLabel();
            winnerLabel.setText("Empatou!");
            setTextWhite(winnerLabel);
            bgc.gridx = 0;
            bgc.gridy = 4;
            this.add(winnerLabel, bgc);
        }
    }

    private void appendButtons(Player player) {
        final JPanel buttonsContainer = new JPanel();
        buttonsContainer.setOpaque(false);
        buttonsContainer.add(hitBTN);
        buttonsContainer.add(standBTN);
        bgc.gridx = 0;
        bgc.gridy = 3;
        this.add(buttonsContainer, bgc);
        hitBTN.setEnabled(player.isPlayerTurn());
        standBTN.setEnabled(player.isPlayerTurn());
        hitBTN.addActionListener(e -> {
            try {
                Main.serverInterface.hit(ClientRMI.getInstance());
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });

        standBTN.addActionListener(e -> {
            try {
                Main.serverInterface.stand(ClientRMI.getInstance());
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void appendNameChips(String name, Integer chips, boolean playerTurn) {
        JPanel user = new JPanel();
        JLabel lName = new JLabel();
        JLabel lChips = new JLabel();
        user.setOpaque(false);
        setTextWhite(lName, lChips);
        if (playerTurn)
            lName.setForeground(Color.YELLOW);
        bgc.gridx = 0;
        bgc.gridy = 0;
        lName.setText(name);
        user.add(lName);
        if (chips != null) {
            String s = Integer.toString(chips);
            lChips.setText(" Chips: " + s);
            user.add(lChips);
        }
        this.add(user, bgc);
    }

    private void cardValue(int score) {
        JLabel lValue = new JLabel();
        lValue.setOpaque(false);
        setTextWhite(lValue);
        bgc.gridx = 0;
        bgc.gridy = 2;
        String s = Integer.toString(score);
        lValue.setText("Score: " + s);
        this.add(lValue, bgc);
    }

    private void setTextWhite(final JLabel... jLabel) {
        for (JLabel jl : jLabel) {
            jl.setForeground(Color.WHITE);
            jl.setFont(jl.getFont().deriveFont(Font.BOLD, 20f));
            jl.setHorizontalAlignment(JLabel.LEADING);
            jl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        }
    }
}

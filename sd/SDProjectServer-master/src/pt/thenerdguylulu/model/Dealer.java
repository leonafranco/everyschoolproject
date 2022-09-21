package pt.thenerdguylulu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dealer implements Serializable {
    protected final Hand hand = new Hand();
    private boolean isFirstCardHidden = true;

    public boolean isFirstCardHidden() {
        return this.isFirstCardHidden;
    }

    public void setFirstCardHidden(final boolean aFirstCardHidden) {
        this.isFirstCardHidden = aFirstCardHidden;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void resetDealer() {
        this.setFirstCardHidden(true);
        this.getHand().clearHand();
    }

    public static class Hand implements Serializable {
        protected final List<Card> hand = new ArrayList<>();

        public void addCard(final Card aCard) {
            this.hand.add(aCard);
        }

        public List<Card> getHand() {
            return this.hand;
        }

        public void clearHand() {
            this.hand.clear();
        }

        public int getHandValue(final boolean hideFirstCardValue) {
            int value = 0;
            boolean tempHide = hideFirstCardValue;

            for (final Card card : this.hand) {
                if (!tempHide)
                    value += card.getValue();
                tempHide = false;
            }

            tempHide = hideFirstCardValue;

            for (final Card card : this.hand) {
                if (!tempHide)
                    if (value > 21 && card.getName().contains("a"))
                        value -= 10;
                tempHide = false;
            }

            return value;
        }
    }
}

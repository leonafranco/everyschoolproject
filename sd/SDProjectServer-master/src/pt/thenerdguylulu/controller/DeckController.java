package pt.thenerdguylulu.controller;

import pt.thenerdguylulu.model.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckController {
    private final List<Card> cardList;
    private int nextToDeal;

    public DeckController() {
        this.cardList = new ArrayList<>();
        this.cardList.addAll(Arrays.asList(
                new Card("ca", 11),
                new Card("ck", 10),
                new Card("cq", 10),
                new Card("cj", 10),
                new Card("c10", 10),
                new Card("c9", 9),
                new Card("c8", 8),
                new Card("c7", 7),
                new Card("c6", 6),
                new Card("c5", 5),
                new Card("c4", 4),
                new Card("c3", 3),
                new Card("c2", 2),
                new Card("da", 11),
                new Card("dk", 10),
                new Card("dq", 10),
                new Card("dj", 10),
                new Card("d10", 10),
                new Card("d9", 9),
                new Card("d8", 8),
                new Card("d7", 7),
                new Card("d6", 6),
                new Card("d5", 5),
                new Card("d4", 4),
                new Card("d3", 3),
                new Card("d2", 2),
                new Card("sa", 11),
                new Card("sk", 10),
                new Card("sq", 10),
                new Card("sj", 10),
                new Card("s10", 10),
                new Card("s9", 9),
                new Card("s8", 8),
                new Card("s7", 7),
                new Card("s6", 6),
                new Card("s5", 5),
                new Card("s4", 4),
                new Card("s3", 3),
                new Card("s2", 2),
                new Card("ha", 11),
                new Card("hk", 10),
                new Card("hq", 10),
                new Card("hj", 10),
                new Card("h10", 10),
                new Card("h9", 9),
                new Card("h8", 8),
                new Card("h7", 7),
                new Card("h6", 6),
                new Card("h5", 5),
                new Card("h4", 4),
                new Card("h3", 3),
                new Card("h2", 2)
        ));

        nextToDeal = 0;

        this.shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cardList);
        nextToDeal = 0;
    }

    public Card deal() {
        try {
            return cardList.get(nextToDeal++);
        } catch (IndexOutOfBoundsException iofbe) {
            this.shuffle();
            return this.deal();
        }
    }
}

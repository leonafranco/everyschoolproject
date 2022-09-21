package pt.thenerdguylulu.programas;

import java.io.Serializable;

public class Card implements Serializable {
    private final String name;  // nomes das carta (mesmo nome do ficheiro .png)
    // c - cubs(paus);   d - diamonds(ouros)
    // h - heads(copas); s - spades(espadas)
    // + 1,2,3,4,5,6,7,8,9,10,j,k,q
    // exemplo: c1 - ás de paus

    private final int order;  // ordem de apresentação numa mão (não utilizado no blackjack)
    private final int value;  // numero de pontos que a carta vale (no blackjack o ás vale 11 ou 1)

    public Card(String aName, int aOrder, int aValue) {
        this.name = aName;
        this.order = aOrder;
        this.value = aValue;
    }

    public String getName() {
        return this.name;
    }

    public int getOrder() {
        return this.order;
    }
}

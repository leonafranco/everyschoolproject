package pt.thenerdguylulu.model;

import java.io.Serializable;

public class Card implements Serializable {
    private final String name;
    private final int value;

    public Card(String aName, int aValue) {
        this.name = aName;
        this.value = aValue;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}

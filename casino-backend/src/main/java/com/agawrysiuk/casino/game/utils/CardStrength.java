package com.agawrysiuk.casino.game.utils;

public enum CardStrength {
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(1, "Jack"),
    QUEEN(1, "Queen"),
    KING(2, "King"),
    ACE(10, "Ace");

    private int cardValue;
    private String cardString;

    CardStrength(int cardValue, String cardString) {
        this.cardValue = cardValue;
        this.cardString = cardString;
    }

    public int getCardValue() {
        return cardValue;
    }

    @Override
    public String toString() {
        return cardString;
    }
}

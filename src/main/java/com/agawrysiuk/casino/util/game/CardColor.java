package com.agawrysiuk.casino.util.game;

public enum CardColor {
    SPADES("Spades"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private String color;

    CardColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color;
    }
}

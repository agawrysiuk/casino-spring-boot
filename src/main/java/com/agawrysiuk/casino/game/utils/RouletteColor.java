package com.agawrysiuk.casino.game.utils;

public enum RouletteColor {
    RED("Red"),
    BLACK("Black"),
    ZERO("Green");

    private String color;

    RouletteColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
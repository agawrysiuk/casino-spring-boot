package com.agawrysiuk.casino.util.game;

public enum SlotsValue {
    FIRST(1),SECOND(2),THIRD(3),FOURTH(4),FIFTH(5),SIXTH(6),SEVENTH(7),EIGHTH(8),NINTH(9);

    public int multiplier;

    SlotsValue(int multiplier) {
        this.multiplier = multiplier;
    }
}

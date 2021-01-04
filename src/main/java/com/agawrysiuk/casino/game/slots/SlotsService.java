package com.agawrysiuk.casino.game.slots;

public interface SlotsService {
    void roll();
    int[] getResults();
    double getMultiplier();
    String getMessage();
}

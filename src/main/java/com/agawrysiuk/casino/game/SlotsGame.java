package com.agawrysiuk.casino.game;

import com.agawrysiuk.casino.util.game.SlotsValue;

public interface SlotsGame {
    void roll();
    SlotsValue[] getResults();
    double getMultiplier();
    double checkResult(int wager);
}

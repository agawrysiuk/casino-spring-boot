package com.agawrysiuk.casino.model.game;

import com.agawrysiuk.casino.util.game.SlotsValue;

public interface SlotsGame {
    void roll();
    SlotsValue[] getResults();
    double getMultiplier();
    double checkResult(int wager);
}

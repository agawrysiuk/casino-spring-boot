package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.game.SlotsGame;
import com.agawrysiuk.casino.util.game.SlotsValue;
import org.springframework.stereotype.Service;

@Service
public class SlotsServiceImpl implements SlotsService {

    private final SlotsGame slotsGame;

    public SlotsServiceImpl(SlotsGame slotsGame) {
        this.slotsGame = slotsGame;
    }

    public void roll() {
        slotsGame.roll();
    }

    public int[] getResults() {
        if (slotsGame.getMultiplier() == -1) {
            return new int[5];
        }
        SlotsValue[] slotsResults = slotsGame.getResults();
        int[] results = new int[slotsResults.length];
        for (int i = 0; i < slotsResults.length; i++) {
            results[i] = slotsResults[i].intValue;
        }
        return results;
    }

    public double getMultiplier() {
        return slotsGame.getMultiplier();
    }

    public String getMessage() {
        return slotsGame.getMultiplier() == -1 ?
                "Roll the game." :
                "You got " + slotsGame.getMultiplier() + " multiplier.";
    }

}

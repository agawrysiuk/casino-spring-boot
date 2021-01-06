package com.agawrysiuk.casino.game.slots;

import com.agawrysiuk.casino.game.utils.SlotsValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SlotsService {

    private final SlotsGame slotsGame;

    public BigDecimal roll() {
        slotsGame.roll();
        return new BigDecimal("1").multiply(getMultiplier());
    }

    public int[] getResults() {
        if (slotsGame.getMultiplier().compareTo(new BigDecimal("-1")) == 0) {
            return new int[5];
        }
        SlotsValue[] slotsResults = slotsGame.getResults();
        int[] results = new int[slotsResults.length];
        for (int i = 0; i < slotsResults.length; i++) {
            results[i] = slotsResults[i].intValue;
        }
        return results;
    }

    public BigDecimal getMultiplier() {
        return slotsGame.getMultiplier();
    }

    public String getMessage() {
        return slotsGame.getMultiplier().compareTo(new BigDecimal("-1")) == 0 ?
                "Roll the game." :
                "You got " + slotsGame.getMultiplier() + " multiplier.";
    }

    public SlotsDto getInitial(BigDecimal userBalance) {
        return SlotsDto.builder()
                .balance(userBalance)
                .mainMessage(getMessage())
                .results(getResults())
                .build();
    }

}

package com.agawrysiuk.casino.game.slots;

import com.agawrysiuk.casino.game.utils.SlotsValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SlotsGame {
    //to simplify it, there is only one win line, one row and five columns
    @Getter
    private double multiplier;
    @Getter
    private SlotsValue[] results;

    @PostConstruct
    public void init() {
        log.info("Launching new Slots Game.");
        results = new SlotsValue[5];
        multiplier = -1;
    }

    public void roll() {
        SlotsValue[] slots = SlotsValue.values();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            results[i] = slots[random.nextInt(slots.length)];
        }
        setMultiplier();
    }

    private void setMultiplier() {
        this.multiplier = 0;
        if (Arrays.stream(this.results).distinct().count() == this.results.length) {
            this.multiplier = 0;
            return;
        }
        Map<SlotsValue, Long> valueMap = Arrays.stream(this.results).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<SlotsValue, Long> entry : valueMap.entrySet()) {
            if (entry.getValue() == 1) {
                continue;
            }
            this.multiplier += Math.pow(entry.getKey().intValue * entry.getValue(), entry.getValue() - 1);
        }
        this.multiplier = this.multiplier / 100;
    }

    public double checkResult(int wager) {
        return this.multiplier * wager;
    }
}

package com.agawrysiuk.casino.game.slots;

import com.agawrysiuk.casino.game.utils.SlotsValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
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
    private BigDecimal multiplier;
    @Getter
    private SlotsValue[] results;

    @PostConstruct
    public void init() {
        log.info("Launching new Slots Game.");
        results = new SlotsValue[5];
        multiplier = new BigDecimal("-1");
        multiplier = multiplier.setScale(2, RoundingMode.CEILING);
    }

    public void roll() {
        SlotsValue[] slots = SlotsValue.values();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 5; i++) {
            results[i] = slots[random.nextInt(slots.length)];
        }
        setMultiplier();
    }

    private void setMultiplier() {
        this.multiplier = new BigDecimal("0");
        if (Arrays.stream(this.results).distinct().count() == this.results.length) {
            this.multiplier = new BigDecimal("0");
            return;
        }
        this.multiplier.setScale(2);
        Map<SlotsValue, Long> valueMap = Arrays.stream(this.results).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<SlotsValue, Long> entry : valueMap.entrySet()) {
            if (entry.getValue() == 1) {
                continue;
            }
            this.multiplier = this.multiplier.add(BigDecimal.valueOf(Math.pow(entry.getKey().intValue * entry.getValue(), entry.getValue() - 1)));
        }
        this.multiplier = this.multiplier.divide(new BigDecimal("100"));
    }
}

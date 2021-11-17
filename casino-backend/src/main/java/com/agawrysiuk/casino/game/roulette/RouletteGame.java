package com.agawrysiuk.casino.game.roulette;

import com.agawrysiuk.casino.game.utils.RouletteColor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;

import static com.agawrysiuk.casino.game.utils.RouletteColor.BLACK;
import static com.agawrysiuk.casino.game.utils.RouletteColor.RED;

@Slf4j
@Component
public class RouletteGame {

    @Getter
    private int number = -1;
    private String color;

    @PostConstruct
    public void start() {
        log.info("Launching new Roulette Game.");
//        newNumber();
//        getColor();
    }

    public void reset() {
        this.number = -1;
        this.color = null;
    }

    public int roll() {
        this.number = new SecureRandom().nextInt(37);
        getColor();
        return this.number;
    }

    public String getColor() {
        if (isBetween(number, 1, 10) || isBetween(number, 19, 28)) {
            this.color = (number % 2 == 0 ? BLACK : RED).toString();
        } else if (isBetween(number, 11, 18) || isBetween(number, 29, 36)) {
            this.color = (number % 2 != 0 ? BLACK : RED).toString();
        } else {
            this.color = RouletteColor.ZERO.toString();
        }
        return this.color;
    }

    public boolean getResultSingle(int guess) throws Exception {
        if (!isBetween(guess, 0, 36)) {
            throw new Exception("Incorrect number range.");
        }
        return guess == this.number;
    }

    public boolean getResultRedOrBlack(String guess) throws Exception {
        if (!guess.toLowerCase().startsWith("r") && !guess.toLowerCase().startsWith("b")) {
            throw new Exception("Incorrect string entered.");
        }
        String firstLetter = this.color.toLowerCase().split("")[0];
        return guess.toLowerCase().startsWith(firstLetter);
    }

    public boolean getResultEvenOrOdd(String guess) throws Exception {
        if (!guess.toLowerCase().startsWith("e") && !guess.toLowerCase().startsWith("o")) {
            throw new Exception("Incorrect string entered.");
        }
        if (number == 0) {
            return false;
        }
        if (guess.toLowerCase().startsWith("e")) {
            return this.number % 2 == 0;
        } else {
            return this.number % 2 != 0;
        }
    }

    private boolean isBetween(int checkedNumber, int min, int max) {
        return checkedNumber >= min && checkedNumber <= max;
    }

}

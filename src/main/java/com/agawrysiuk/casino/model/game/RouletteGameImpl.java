package com.agawrysiuk.casino.model.game;

import com.agawrysiuk.casino.util.game.RouletteColor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Slf4j
@Component
public class RouletteGameImpl implements RouletteGame {

    @Getter
    private int number = -1;
    private String color;

    @PostConstruct
    public void roll() {
        log.info("Launching new Roulette Game.");
        newNumber();
        getColor();
    }

    @Override
    public int newNumber() {
        this.number = new Random().nextInt(37);
        return this.number;
    }

    @Override
    public String getColor() {
        if (isBetween(number, 1, 10) || isBetween(number, 19, 28)) {
            if (number % 2 == 0) {
                this.color = RouletteColor.BLACK.toString();
            } else {
                this.color = RouletteColor.RED.toString();
            }
        } else if (isBetween(number, 11, 18) || isBetween(number, 29, 36)) {
            if (number % 2 != 0) {
                this.color = RouletteColor.BLACK.toString();
            } else {
                this.color = RouletteColor.RED.toString();
            }
        } else {
            this.color = RouletteColor.ZERO.toString();
        }
        return this.color;
    }

    @Override
    public boolean getResultSingle(int guess) throws Exception {
        if(!isBetween(guess,0,36)) {
            throw new Exception("Incorrect number range.");
        }
        return guess == this.number;
    }

    @Override
    public boolean getResultRedOrBlack(String guess) throws Exception {
        if(!guess.toLowerCase().startsWith("r") && !guess.toLowerCase().startsWith("b")) {
            throw new Exception("Incorrect string entered.");
        }
        String firstLetter = this.color.toLowerCase().split("")[0];
        return guess.toLowerCase().startsWith(firstLetter);
    }

    @Override
    public boolean getResultEvenOrOdd(String guess) throws Exception {
        if(!guess.toLowerCase().startsWith("e") && !guess.toLowerCase().startsWith("o")) {
            throw new Exception("Incorrect string entered.");
        }
        String firstLetter = this.color.toLowerCase().split("")[0];
        return guess.toLowerCase().startsWith(firstLetter);
    }

    private boolean isBetween(int checkedNumber, int min, int max) {
        return checkedNumber >= min && checkedNumber <= max;
    }

}

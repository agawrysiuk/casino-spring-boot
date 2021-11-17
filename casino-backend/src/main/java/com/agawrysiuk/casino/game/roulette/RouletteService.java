package com.agawrysiuk.casino.game.roulette;

import org.springframework.stereotype.Service;

@Service
public class RouletteService {
    private final RouletteGame rouletteGame;

    public RouletteService(RouletteGame rouletteGame) {
        this.rouletteGame = rouletteGame;
    }

    public String getMainMessage() {
        if (rouletteGame.getNumber() == -1) {
            return "Place your bet";
        } else {
            return "It's " + rouletteGame.getNumber() + " " + rouletteGame.getColor();
        }
    }

    public String getResultMessageRedOrBlack(String guess) {

        try {
            return (rouletteGame.getResultRedOrBlack(guess)) ?
                    "You won" :
                    "You lost";
        } catch (Exception e) {
            return "Incorrect guess";
        }
    }

    public String getResultMessageEvenOrOdd(String guess) {
        try {
            return (rouletteGame.getResultEvenOrOdd(guess)) ?
                    "You won" :
                    "You lost";
        } catch (Exception e) {
            return "Incorrect guess";
        }
    }

    public String getResultMessageSingle(int guess) {
        try {
            return (rouletteGame.getResultSingle(guess)) ?
                    "You won" :
                    "You lost";
        } catch (Exception e) {
            return "Incorrect guess";
        }
    }

    public void roll() {
        rouletteGame.roll();
    }

    public void reset() {
        rouletteGame.reset();
    }
}

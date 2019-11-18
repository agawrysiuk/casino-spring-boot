package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.game.RouletteGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouletteServiceImpl implements RouletteService {
    private final RouletteGame rouletteGame;

    @Autowired
    public RouletteServiceImpl(RouletteGame rouletteGame) {
        this.rouletteGame = rouletteGame;
    }

    @Override
    public String getMainMessage() {
        if (rouletteGame.getNumber() == -1) {
            return "Place your bet";
        } else {
            return "It's " + rouletteGame.getNumber() + " " + rouletteGame.getColor();
        }
    }

    @Override
    public String getResultMessageRedOrBlack(String guess) {
        try {
            return (rouletteGame.getResultRedOrBlack(guess)) ?
                    "You won" :
                    "You lost";
        } catch (Exception e) {
            return "Incorrect guess";
        }
    }

    @Override
    public String getResultMessageEvenOrOdd(String guess) {
        try {
            return (rouletteGame.getResultEvenOrOdd(guess)) ?
                    "You won" :
                    "You lost";
        } catch (Exception e) {
            return "Incorrect guess";
        }
    }

    @Override
    public String getResultMessageSingle(int guess) {
        try {
            return (rouletteGame.getResultSingle(guess)) ?
                    "You won" :
                    "You lost";
        } catch (Exception e) {
            return "Incorrect guess";
        }
    }

    @Override
    public void roll() {
        rouletteGame.newNumber();
    }

    @Override
    public void reset() {
        rouletteGame.reset();
    }
}

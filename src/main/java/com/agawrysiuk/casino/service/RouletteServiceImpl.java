package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.game.RouletteGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouletteServiceImpl implements RouletteService{
    private final RouletteGame rouletteGame;

    @Autowired
    public RouletteServiceImpl(RouletteGame rouletteGame) {
        this.rouletteGame = rouletteGame;
    }

    @Override
    public String getMainMessage() {
        if(rouletteGame.getNumber()==-1) {
            return "Place your bet";
        } else {
            return "It's " + rouletteGame.getNumber() + " " + rouletteGame.getColor();
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

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

    public String getMainMessage() {
        if(rouletteGame.getNumber()==-1) {
            return "Place your bet";
        } else {
            return "It's " + rouletteGame.getNumber() + " " + rouletteGame.getColor();
        }
    }

    public void roll() {
        rouletteGame.newNumber();
    }
}

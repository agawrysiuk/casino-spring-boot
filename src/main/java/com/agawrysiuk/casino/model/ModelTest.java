package com.agawrysiuk.casino.model;

import com.agawrysiuk.casino.model.game.RouletteGameImpl;

public class ModelTest {
    public static void main(String[] args) {
        RouletteGameImpl rouletteGame = new RouletteGameImpl();
        rouletteGame.roll();
        System.out.println("Number = " + rouletteGame.getNumber() + ", Color = " + rouletteGame.getColor());
        try {
            System.out.println("Test Single: " + rouletteGame.getResultSingle(25));
            System.out.println("Test RedOrBlack: " + rouletteGame.getResultRedOrBlack("b"));
            System.out.println("Test LowOrHigh: " + rouletteGame.getResultLowOrHigh("low"));
            System.out.println("Test EvenOrOdd: " + rouletteGame.getResultEvenOrOdd("e"));
            System.out.println("Test Dozen: " + rouletteGame.getResultDozen(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

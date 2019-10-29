package com.agawrysiuk.casino.model;

import com.agawrysiuk.casino.model.game.SlotsGameImpl;

import java.util.Arrays;

public class ModelTest {
    public static void main(String[] args) {
//        RouletteGameImpl rouletteGame = new RouletteGameImpl();
//        rouletteGame.roll();
//        System.out.println("Number = " + rouletteGame.getNumber() + ", Color = " + rouletteGame.getColor());
//        try {
//            System.out.println("Test Single: " + rouletteGame.getResultSingle(25));
//            System.out.println("Test RedOrBlack: " + rouletteGame.getResultRedOrBlack("b"));
//            System.out.println("Test EvenOrOdd: " + rouletteGame.getResultEvenOrOdd("e"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SlotsGameImpl slots = new SlotsGameImpl();
        slots.roll();
        System.out.println(Arrays.toString(slots.getResults()));
        System.out.println(slots.getMultiplier());
    }
}

package com.agawrysiuk.casino.model;

import com.agawrysiuk.casino.model.game.TwentyOneGameImpl;

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

//        SlotsGameImpl slots = new SlotsGameImpl();
//        slots.roll();
//        System.out.println(Arrays.toString(slots.getResults()));
//        System.out.println(slots.getMultiplier());

        TwentyOneGameImpl twentyOneGame = new TwentyOneGameImpl();
        twentyOneGame.init();
        System.out.println("Cards in deck: "+twentyOneGame.getCardsInDeck().size());
        System.out.println("Your hand: "+twentyOneGame.getYourHand());
        System.out.println("Dealer's hand: "+twentyOneGame.getDealersHand());
        System.out.println("Is game over? "+twentyOneGame.getGameState());
        System.out.println("Did you win? "+twentyOneGame.getGameResult());
        twentyOneGame.hitMe();
        System.out.println("Your hand: "+twentyOneGame.getYourHand());
        System.out.println("Your sum: "+twentyOneGame.getYourSum());
        System.out.println("Is game over? "+twentyOneGame.getGameState());
        System.out.println("Did you win? "+twentyOneGame.getGameResult());
        twentyOneGame.hitMe();
        System.out.println("Your hand: "+twentyOneGame.getYourHand());
        System.out.println("Your sum: "+twentyOneGame.getYourSum());
        System.out.println("Is game over? "+twentyOneGame.getGameState());
        System.out.println("Did you win? "+twentyOneGame.getGameResult());
        twentyOneGame.hitMe();
        System.out.println("Your hand: "+twentyOneGame.getYourHand());
        System.out.println("Your sum: "+twentyOneGame.getYourSum());
        System.out.println("Is game over? "+twentyOneGame.getGameState());
        System.out.println("Did you win? "+twentyOneGame.getGameResult());
        twentyOneGame.dealersChoice();
        System.out.println("Dealer's hand: "+twentyOneGame.getDealersHand());
        System.out.println("Your sum: "+twentyOneGame.getYourSum());
        System.out.println("Dealer's sum: "+twentyOneGame.getDealersSum());
        System.out.println("Is game over? "+twentyOneGame.getGameState());
        System.out.println("Did you win? "+twentyOneGame.getGameResult());


    }
}

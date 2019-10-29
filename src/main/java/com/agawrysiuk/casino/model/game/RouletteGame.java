package com.agawrysiuk.casino.model.game;

public interface RouletteGame {
    int newNumber();
    String getColor();
    boolean getResultSingle(int guess) throws Exception;
    boolean getResultRedOrBlack(String guess) throws Exception;
    boolean getResultEvenOrOdd(String guess) throws Exception;
    boolean getResultDozen(int guess) throws Exception;
    boolean getResultLowOrHigh(String guess) throws Exception;
}

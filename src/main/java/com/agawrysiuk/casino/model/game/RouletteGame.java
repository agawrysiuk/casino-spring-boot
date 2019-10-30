package com.agawrysiuk.casino.model.game;

public interface RouletteGame {
    int newNumber();
    String getColor();
    int getNumber();
    boolean getResultSingle(int guess) throws Exception;
    boolean getResultRedOrBlack(String guess) throws Exception;
    boolean getResultEvenOrOdd(String guess) throws Exception;
}

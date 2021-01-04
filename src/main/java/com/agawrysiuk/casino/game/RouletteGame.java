package com.agawrysiuk.casino.game;

public interface RouletteGame {
    int roll();
    String getColor();
    int getNumber();
    void reset();
    boolean getResultSingle(int guess) throws Exception;
    boolean getResultRedOrBlack(String guess) throws Exception;
    boolean getResultEvenOrOdd(String guess) throws Exception;
}

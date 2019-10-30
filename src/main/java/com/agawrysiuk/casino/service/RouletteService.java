package com.agawrysiuk.casino.service;

public interface RouletteService {
    String getMainMessage();
    String getResultMessageRedOrBlack(String guess);
    String getResultMessageEvenOrOdd(String guess);
    String getResultMessageSingle(int guess);
    void roll();
    void reset();
}

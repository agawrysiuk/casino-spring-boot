package com.agawrysiuk.casino.model.game;

import java.util.List;

public interface TwentyOneGame {
    void reset();
    void hitMe();
    void dealersChoice();
    int getYourSum();
    int getDealersSum();
    List<Card> getYourHand();
    List<Card> getDealersHand();
    boolean getGameState();
    boolean getGameResult();
}

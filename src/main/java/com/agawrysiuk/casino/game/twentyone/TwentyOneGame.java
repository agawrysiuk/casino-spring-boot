package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.game.utils.Card;

import java.util.List;

public interface TwentyOneGame {
    void reset();
    Card hitMe();
    void dealersChoice();
    int getYourSum();
    int getDealersSum();
    List<Card> getYourHand();
    List<Card> getDealersHand();
    boolean getGameState();
    boolean getGameResult();
}

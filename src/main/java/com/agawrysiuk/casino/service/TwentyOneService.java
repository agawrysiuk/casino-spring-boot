package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.util.game.Card;

import java.util.List;

public interface TwentyOneService {
    Card hitMe();
    void newGame();
    List<Card> getYourCards();
    List<Card> getDealersCards();
    boolean getGameState();
    boolean getGameResult();
    String getMainMessage();
    String getResultMessage();
    void resetGame();
    void dealersTurn();
}

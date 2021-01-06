package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.game.utils.Card;

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

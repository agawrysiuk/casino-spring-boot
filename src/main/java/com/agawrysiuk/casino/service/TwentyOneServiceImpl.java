package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.util.game.Card;
import com.agawrysiuk.casino.game.TwentyOneGame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwentyOneServiceImpl implements TwentyOneService {

    private final TwentyOneGame twentyOneGame;

    public TwentyOneServiceImpl(TwentyOneGame twentyOneGame) {
        this.twentyOneGame = twentyOneGame;
    }

    @Override
    public Card hitMe() {
        return twentyOneGame.hitMe();
    }

    @Override
    public void newGame() {
        twentyOneGame.reset();
    }

    @Override
    public List<Card> getYourCards() {
        return twentyOneGame.getYourHand();
    }

    @Override
    public List<Card> getDealersCards() {
        return twentyOneGame.getDealersHand();
    }

    @Override
    public boolean getGameState() {
        return twentyOneGame.getGameState();
    }

    @Override
    public boolean getGameResult() {
        return twentyOneGame.getGameResult();
    }

    @Override
    public String getMainMessage() {
        if (twentyOneGame.getYourSum() == 0 && !twentyOneGame.getGameState()) {
            return "Click \"Hit Me\" to play the game.";
        } else if (!twentyOneGame.getGameState()) {
            return "Do you want another card or stay with your hand?";
        } else {
            return "Game Over.";
        }
    }

    @Override
    public String getResultMessage() {
        if (twentyOneGame.getYourSum() == 0 && !twentyOneGame.getGameState()) {
            return "";
        } else if (twentyOneGame.getYourSum() != 0 && !twentyOneGame.getGameState()) {
            return "Your sum is now " + twentyOneGame.getYourSum() + ".";
        } else {
            return twentyOneGame.getGameResult() ?
                    "You won." : "You lost.";
        }
    }

    @Override
    public void resetGame() {
        twentyOneGame.reset();
    }

    @Override
    public void dealersTurn() {
        twentyOneGame.dealersChoice();
    }
}

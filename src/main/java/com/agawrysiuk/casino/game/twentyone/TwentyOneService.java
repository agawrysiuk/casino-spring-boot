package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.game.utils.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TwentyOneService {

    private final TwentyOneGame twentyOneGame;

    public Card hitMe() {
        return twentyOneGame.hitMe();
    }

    public void newGame() {
        twentyOneGame.reset();
    }

    public List<Card> getYourCards() {
        return twentyOneGame.getYourHand();
    }

    public List<Card> getDealersCards() {
        return twentyOneGame.getDealersHand();
    }

    public boolean isGameFinished() {
        return twentyOneGame.isGameFinished();
    }

    public boolean getGameResult() {
        return twentyOneGame.getGameResult();
    }

    public String getMainMessage() {
        if (twentyOneGame.getYourSum() == 0 && !twentyOneGame.isGameFinished()) {
            return "Click \"Hit Me\" to play the game.";
        } else if (!twentyOneGame.isGameFinished()) {
            return "Do you want another card or stay with your hand?";
        } else {
            return "Game Over.";
        }
    }

    public String getResultMessage() {
        if (twentyOneGame.getYourSum() == 0 && !twentyOneGame.isGameFinished()) {
            return "";
        } else if (twentyOneGame.getYourSum() != 0 && !twentyOneGame.isGameFinished()) {
            return "Your sum is now " + twentyOneGame.getYourSum() + ".";
        } else {
            return twentyOneGame.getGameResult() ?
                    "You won." : "You lost.";
        }
    }

    public void resetGame() {
        twentyOneGame.reset();
    }

    public void dealersTurn() {
        twentyOneGame.dealersChoice();
    }
}

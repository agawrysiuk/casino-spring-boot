package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.util.game.Card;
import com.agawrysiuk.casino.util.game.CardColor;
import com.agawrysiuk.casino.util.game.CardStrength;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@Getter
public class TwentyOneGameImpl implements TwentyOneGame {
    private int yourSum;
    private int dealersSum;
    private List<Card> yourHand;
    private List<Card> dealersHand;
    private List<Card> cardsInDeck;
    private boolean gameFinished;
    private boolean gameLost;
    private boolean gameWon;

    @PostConstruct
    public void init() {
        log.info("Creating card types.");
        cardsInDeck = new ArrayList<>();
        yourHand = new ArrayList<>();
        dealersHand = new ArrayList<>();
        reset();
    }

    @Override
    public void reset() {
        yourSum = 0;
        dealersSum = 0;
        yourHand.clear();
        dealersHand.clear();
        shuffleDeck();
        gameFinished = false;
        gameLost = false;
        gameWon = false;
    }

    @Override
    public Card hitMe() {
        if (gameLost || gameWon) {
            return null;
        }
        Card card = cardsInDeck.get(new Random().nextInt(cardsInDeck.size()));
        yourHand.add(card);
        yourSum += card.getCardStrength().getCardValue();
        cardsInDeck.remove(card);
        if (yourSum > 21) {
            gameFinished = true;
            gameLost = true;
        }
        return card;
    }

    @Override
    public void dealersChoice() {
        if (gameLost || gameWon) {
            return;
        }
        gameFinished = true;
        while ((dealersSum <= 14 || dealersSum < yourSum) && !gameWon && !gameLost) {
            Card card = cardsInDeck.get(new Random().nextInt(cardsInDeck.size()));
            dealersHand.add(card);
            cardsInDeck.remove(card);
            dealersSum += card.getCardStrength().getCardValue();
            if (dealersSum > 21) {
                gameWon = true;
                return;
            }
            if (dealersSum > yourSum) {
                gameLost = true;
                return;
            }
        }
        if (dealersSum >= yourSum) {
            gameLost = true;
        } else {
            gameWon = true;
        }
    }

    @Override
    public boolean getGameState() {
        return gameFinished;
    }

    @Override
    public boolean getGameResult() {
        if (gameLost) {
            return false;
        }
        if (gameWon) {
            return true;
        }
        if (gameFinished) {
            return yourSum > dealersSum;
        } else {
            return false;
        }
    }

    private void shuffleDeck() {
        cardsInDeck.clear();
        for (CardStrength cardStrength : CardStrength.values()) {
            for (CardColor cardColor : CardColor.values()) {
                cardsInDeck.add(new Card(cardColor, cardStrength));
            }
        }
        Collections.shuffle(cardsInDeck);
    }
}

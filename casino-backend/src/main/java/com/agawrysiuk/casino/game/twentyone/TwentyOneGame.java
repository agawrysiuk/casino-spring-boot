package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.game.utils.Card;
import com.agawrysiuk.casino.game.utils.CardColor;
import com.agawrysiuk.casino.game.utils.CardStrength;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@Getter
public class TwentyOneGame {
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

    public Card hitMe() {
        if (gameLost || gameWon) {
            return null;
        }
        Card card = cardsInDeck.get(new SecureRandom().nextInt(cardsInDeck.size()));
        yourHand.add(card);
        yourSum += card.getCardStrength().getCardValue();
        cardsInDeck.remove(card);
        if (yourSum > 21) {
            gameFinished = true;
            gameLost = true;
        }
        return card;
    }

    public void dealersChoice() {
        if (gameLost || gameWon) {
            return;
        }
        gameFinished = true;
        while ((dealersSum <= 14 || dealersSum < yourSum) && !gameWon && !gameLost) {
            Card card = cardsInDeck.get(new SecureRandom().nextInt(cardsInDeck.size()));
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

package com.agawrysiuk.casino.game;

import com.agawrysiuk.casino.game.twentyone.TwentyOneGame;
import com.agawrysiuk.casino.game.utils.Card;
import com.agawrysiuk.casino.game.utils.CardColor;
import com.agawrysiuk.casino.game.utils.CardStrength;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwentyOneGameTest {

    @Test
    void initTestForGameState() {
        TwentyOneGame twentyOneGame = new TwentyOneGame();
        twentyOneGame.init();
        assertNotNull(twentyOneGame.getCardsInDeck());
        assertNotNull(twentyOneGame.getYourHand());
        assertNotNull(twentyOneGame.getDealersHand());
        assertFalse(twentyOneGame.getGameResult());
        assertFalse(twentyOneGame.isGameFinished());
        assertFalse(twentyOneGame.isGameLost());
        assertFalse(twentyOneGame.isGameWon());
        assertEquals(0,twentyOneGame.getYourSum());
        assertEquals(0,twentyOneGame.getDealersSum());
        assertEquals(32,twentyOneGame.getCardsInDeck().size());
    }

    @Test
    void initTestIfCardsShuffled() {
        TwentyOneGame twentyOneGame = new TwentyOneGame();
        twentyOneGame.init();
        List<Card> cardsTest = new ArrayList<>();
        for (CardStrength cardStrength : CardStrength.values()) {
            for (CardColor cardColor : CardColor.values()) {
                cardsTest.add(new Card(cardColor, cardStrength));
            }
        }
        assertThrows(AssertionFailedError.class, () -> assertArrayEquals(cardsTest.toArray(),twentyOneGame.getCardsInDeck().toArray()));
    }

    @Test
    void hitMeWithOneCardTest() {
        TwentyOneGame twentyOneGame = new TwentyOneGame();
        twentyOneGame.init();
        assertNotNull(twentyOneGame.hitMe());
        assertNotEquals(0, twentyOneGame.getYourSum());
        assertNotEquals(0,twentyOneGame.getYourHand().size());
        assertFalse(twentyOneGame.isGameFinished());
        assertFalse(twentyOneGame.isGameLost());
        assertFalse(twentyOneGame.isGameWon());
    }

    @Test
    void hitMe100TimesAssertYouDontDrawMoreCards() {
        TwentyOneGame twentyOneGame = new TwentyOneGame();
        twentyOneGame.init();
        for (int i = 0; i < 100; i++) {
            twentyOneGame.hitMe();
        }
        assertNotEquals(0,twentyOneGame.getYourHand().size());
        assertNotEquals(0,twentyOneGame.getCardsInDeck().size());
        assertTrue(twentyOneGame.isGameFinished());
        assertTrue(twentyOneGame.isGameLost());
        assertFalse(twentyOneGame.isGameWon());
    }

    @Test
    void dealerAlwaysWins() {
        TwentyOneGame twentyOneGame = new TwentyOneGame();
        twentyOneGame.init();
        twentyOneGame.dealersChoice();
        assertTrue(twentyOneGame.isGameFinished());
        assertTrue(twentyOneGame.isGameLost());
        assertFalse(twentyOneGame.isGameWon());
        assertTrue(twentyOneGame.getYourSum() < twentyOneGame.getDealersSum());
    }
}
package com.agawrysiuk.casino.game;

import com.agawrysiuk.casino.game.twentyone.TwentyOneGameImpl;
import com.agawrysiuk.casino.game.utils.Card;
import com.agawrysiuk.casino.game.utils.CardColor;
import com.agawrysiuk.casino.game.utils.CardStrength;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwentyOneGameImplTest {

    @Test
    void initTestForGameState() {
        TwentyOneGameImpl twentyOneGame = new TwentyOneGameImpl();
        twentyOneGame.init();
        assertNotNull(twentyOneGame.getCardsInDeck());
        assertNotNull(twentyOneGame.getYourHand());
        assertNotNull(twentyOneGame.getDealersHand());
        assertFalse(twentyOneGame.getGameState());
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
        TwentyOneGameImpl twentyOneGame = new TwentyOneGameImpl();
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
        TwentyOneGameImpl twentyOneGame = new TwentyOneGameImpl();
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
        TwentyOneGameImpl twentyOneGame = new TwentyOneGameImpl();
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
        TwentyOneGameImpl twentyOneGame = new TwentyOneGameImpl();
        twentyOneGame.init();
        twentyOneGame.dealersChoice();
        assertTrue(twentyOneGame.isGameFinished());
        assertTrue(twentyOneGame.isGameLost());
        assertFalse(twentyOneGame.isGameWon());
        assertTrue(twentyOneGame.getYourSum() < twentyOneGame.getDealersSum());
    }
}
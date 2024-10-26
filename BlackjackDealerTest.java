//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class BlackjackDealerTest {
//    private BlackjackDealer dealer;
//
//    @BeforeEach
//    void setUp() {
//        dealer = new BlackjackDealer();
//    }
//
//    @Test
//    void deckSizeAfterInitialization() {
//        assertEquals(52, dealer.deckSize());
//    }
//
//    @Test
//    void dealHandSize() {
//        ArrayList<Card> hand = dealer.dealHand();
//        assertEquals(2, hand.size());
//    }
//
//    @Test
//    void deckSizeAfterDealingHand() {
//        dealer.dealHand();
//        assertEquals(50, dealer.deckSize());
//    }
//
//    @Test
//    void shuffleDeckChangesOrder() {
//        ArrayList<Card> deckBeforeShuffle = dealer.dealHand();
//        dealer.shuffleDeck();
//        ArrayList<Card> deckAfterShuffle = dealer.dealHand();
//
//        assertNotEquals(deckBeforeShuffle, deckAfterShuffle);
//    }
//
//    @Test
//    void resetHandRestoresFullDeck() {
//        dealer.dealHand();
//        dealer.resetHand();
//        assertEquals(52, dealer.deckSize());
//    }
//
//    @Test
//    void allCardsUniqueAfterShuffle() {
//        dealer.shuffleDeck();
//        Set<Card> cardSet = new HashSet<>(dealer.getDeck()); // Hypothetical getDeck method
//        assertEquals(52, cardSet.size());
//    }
//}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BlackjackDealerTest {
    private BlackjackDealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new BlackjackDealer();
    }

    @Test
    void deckSizeAfterInitialization() {
        assertEquals(52, dealer.deckSize());
    }

    @Test
    void dealHandSize() {
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(2, hand.size());
    }

    @Test
    void deckSizeAfterDealingHand() {
        dealer.dealHand();
        assertEquals(50, dealer.deckSize());
    }

    @Test
    void shuffleDeckChangesOrder() {
        ArrayList<Card> deckBeforeShuffle = new ArrayList<>(dealer.getDeck());
        dealer.shuffleDeck();
        ArrayList<Card> deckAfterShuffle = dealer.getDeck();
        assertNotEquals(deckBeforeShuffle, deckAfterShuffle);
    }


    @Test
    void resetHandRestoresFullDeck() {
        dealer.dealHand();
        dealer.resetHand();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    void allCardsUniqueAfterShuffle() {
        dealer.shuffleDeck();
        Set<Card> cardSet = new HashSet<>(dealer.getDeck()); // Hypothetical getDeck method
        assertEquals(52, cardSet.size());
    }

    @Test
    void drawOneReducesDeckSize() {
        int originalSize = dealer.deckSize();
        dealer.drawOne();
        assertEquals(originalSize - 1, dealer.deckSize());
    }

    @Test
    void drawOneReturnsACard() {
        Card card = dealer.drawOne();
        assertNotNull(card);
    }

    @Test
    void twoConsecutiveShufflesProduceDifferentOrders() {
        dealer.shuffleDeck();
        ArrayList<Card> firstShuffle = new ArrayList<>(dealer.getDeck());
        dealer.shuffleDeck();
        ArrayList<Card> secondShuffle = dealer.getDeck();
        assertNotEquals(firstShuffle, secondShuffle);
    }
}

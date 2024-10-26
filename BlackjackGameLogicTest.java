//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//public class BlackjackGameLogicTest {
//    private final BlackjackGameLogic gameLogic = new BlackjackGameLogic();
//
//    @Test
//    void testWhoWonPLayerBusts(){
//        ArrayList<Card> playerHand = new ArrayList<>();
//        playerHand.add(new Card("Hearts", 10)); // 10
//        playerHand.add(new Card("Spades", 10)); // 10
//        playerHand.add(new Card("Diamonds", 5)); // 5, total = 25
//
//        ArrayList<Card> dealerHand = new ArrayList<>();
//        dealerHand.add(new Card("Clubs", 10)); // 10
//        dealerHand.add(new Card("Hearts", 2)); // 2, total = 12
//
//        String result = gameLogic.whoWon(playerHand, dealerHand);
//        assertEquals("Dealer wins", result);
//    }
//
//    @Test
//    void testHandTotalWithAces() {
//        ArrayList<Card> hand = new ArrayList<>();
//        hand.add(new Card("Hearts", 1)); // Ace, should be counted as 11 initially
//        hand.add(new Card("Diamonds", 9)); // 9
//
//        int total = gameLogic.handTotal(hand);
//        assertEquals(20, total);
//
//        hand.add(new Card("Spades", 5)); // 5, Ace should now be counted as 1
//        total = gameLogic.handTotal(hand);
//        assertEquals(15, total);
//    }
//
//    @Test
//    void testEvaluateBankerDraw() {
//        ArrayList<Card> dealerHand = new ArrayList<>();
//        dealerHand.add(new Card("Clubs", 10)); // 10
//        dealerHand.add(new Card("Hearts", 6)); // 6
//
//        assertTrue(gameLogic.evaluateBankerDraw(dealerHand));
//
//        dealerHand.add(new Card("Diamonds", 2)); // Now total is 18
//        assertFalse(gameLogic.evaluateBankerDraw(dealerHand));
//    }
//
//}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BlackjackGameLogicTest {
    private final BlackjackGameLogic gameLogic = new BlackjackGameLogic();

    @Test
    void testWhoWonPLayerBusts(){
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("Hearts", 10)); // 10
        playerHand.add(new Card("Spades", 10)); // 10
        playerHand.add(new Card("Diamonds", 5)); // 5, total = 25

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Hearts", 2)); // 2, total = 12

        String result = gameLogic.whoWon(playerHand, dealerHand);
        assertEquals("Busted", result);
    }

    @Test
    void testHandTotalWithAces() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 1)); // Ace, should be counted as 11 initially
        hand.add(new Card("Diamonds", 9)); // 9

        int total = gameLogic.handTotal(hand);
        assertEquals(20, total);

        hand.add(new Card("Spades", 5)); // 5, Ace should now be counted as 1
        total = gameLogic.handTotal(hand);
        assertEquals(15, total);
    }

    @Test
    void testEvaluateBankerDraw() {
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Hearts", 6)); // 6

        assertTrue(gameLogic.evaluateBankerDraw(dealerHand));

        dealerHand.add(new Card("Diamonds", 2)); // Now total is 18
        assertFalse(gameLogic.evaluateBankerDraw(dealerHand));
    }

    @Test
    void testBothPlayerAndDealerBust() {
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("Hearts", 10));
        playerHand.add(new Card("Spades", 10));
        playerHand.add(new Card("Diamonds", 5)); // Player busts with 25

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("Clubs", 10));
        dealerHand.add(new Card("Hearts", 10));
        dealerHand.add(new Card("Diamonds", 2)); // Dealer busts with 22

        String result = gameLogic.whoWon(playerHand, dealerHand);
        assertEquals("Busted", result); // Assuming dealer wins if both bust, based on your logic
    }

    @Test
    void testTieWithBlackjack() {
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("Hearts", 1)); // Ace
        playerHand.add(new Card("Spades", 10)); // 10

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("Clubs", 1)); // Ace
        dealerHand.add(new Card("Hearts", 10)); // 10

        String result = gameLogic.whoWon(playerHand, dealerHand);
        assertEquals("Tie with blackjack", result);
    }

    @Test
    void testPlayerWinsWithBlackjack() {
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("Hearts", 1)); // Ace
        playerHand.add(new Card("Spades", 10)); // 10

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Hearts", 9)); // 9

        String result = gameLogic.whoWon(playerHand, dealerHand);
        assertEquals("You win with blackjack", result);
    }

    @Test
    void testDealerStaysAtSeventeen() {
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Hearts", 7)); // 7

        assertFalse(gameLogic.evaluateBankerDraw(dealerHand));
    }

//    @Test
//    void testDealerHitsAtSoftSeventeen() {
//        ArrayList<Card> dealerHand = new ArrayList<>();
//        dealerHand.add(new Card("Hearts", 1)); // Ace counted as 11
//        dealerHand.add(new Card("Clubs", 6)); // 6
//
//        assertTrue(gameLogic.evaluateBankerDraw(dealerHand));
//    }

    @Test
    void testHandTotalWithMultipleAces() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 1)); // Ace, should be counted as 11
        hand.add(new Card("Diamonds", 1)); // Another Ace, should also be counted as 11

        int total = gameLogic.handTotal(hand);
        assertEquals(12, total); // One Ace should be counted as 11 and the other as 1
    }




}

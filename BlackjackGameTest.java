//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//public class BlackjackGameTest {
//    private BlackjackGameLogic gameLogic;
//    private ArrayList<Card> playerHand;
//    private ArrayList<Card> dealerHand;
//
//    @BeforeEach
//    void setUp() {
//        gameLogic = new BlackjackGameLogic();
//        playerHand = new ArrayList<>();
//        dealerHand = new ArrayList<>();
//    }
//
//    @Test
//    void testHandTotal() {
//        playerHand.add(new Card("Hearts", 10)); // 10
//        playerHand.add(new Card("Diamonds", 1)); // Ace as 11
//        assertEquals(21, gameLogic.handTotal(playerHand)); // Hand total should be 21
//
//        dealerHand.add(new Card("Clubs", 10)); // 10
//        dealerHand.add(new Card("Spades", 13)); // King as 10
//        assertEquals(20, gameLogic.handTotal(dealerHand)); // Hand total should be 20
//    }
//
//    @Test
//    void testWhoWonPlayerWins() {
//        playerHand.add(new Card("Hearts", 10)); // 10
//        playerHand.add(new Card("Diamonds", 1)); // Ace as 11
//        playerHand.add(new Card("Clubs", 10)); // 10
//        playerHand.add(new Card("Spades", 9)); // 9
//        assertEquals("You win", gameLogic.whoWon(playerHand, dealerHand)); // Player should win with a blackjack
//    }
//
//    @Test
//    void testWhoWonDealerWins() {
//        playerHand.add(new Card("Hearts", 10)); // 10
//        playerHand.add(new Card("Diamonds", 10)); // 10
//        dealerHand.add(new Card("Clubs", 10)); // 10
//        dealerHand.add(new Card("Spades", 1)); // Ace as 11
//        assertEquals("Dealer wins", gameLogic.whoWon(playerHand, dealerHand)); // Dealer should win with a blackjack
//    }
//
//    @Test
//    void testWhoWonTie() {
//        playerHand.add(new Card("Hearts", 10)); // 10
//        playerHand.add(new Card("Diamonds", 10)); // 10
//        dealerHand.add(new Card("Clubs", 10)); // 10
//        dealerHand.add(new Card("Spades", 10)); // 10
//        assertEquals("Tie", gameLogic.whoWon(playerHand, dealerHand)); // Should be a tie
//    }
//
//    @Test
//    void testEvaluateBankerDraw() {
//        dealerHand.add(new Card("Clubs", 10)); // 10
//        dealerHand.add(new Card("Spades", 6)); // 6
//        assertTrue(gameLogic.evaluateBankerDraw(dealerHand)); // Dealer should draw with a hand total of 16 or less
//
//        dealerHand.add(new Card("Spades", 2)); // Now total is 18
//        assertFalse(gameLogic.evaluateBankerDraw(dealerHand)); // Dealer should not draw with a hand total of 17 or more
//    }
//}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BlackjackGameTest {
    private BlackjackGameLogic gameLogic;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;

    @BeforeEach
    void setUp() {
        gameLogic = new BlackjackGameLogic();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    @Test
    void testHandTotal() {
        playerHand.add(new Card("Hearts", 10)); // 10
        playerHand.add(new Card("Diamonds", 1)); // Ace as 11
        assertEquals(21, gameLogic.handTotal(playerHand)); // Hand total should be 21

        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Spades", 13)); // King as 10
        assertEquals(20, gameLogic.handTotal(dealerHand)); // Hand total should be 20
    }

    @Test
    void testWhoWonPlayerWins() {
        playerHand.add(new Card("Hearts", 10)); // 10
        playerHand.add(new Card("Diamonds", 1)); // Ace as 11, blackjack
        dealerHand.add(new Card("Clubs", 9)); // 9
        dealerHand.add(new Card("Spades", 9)); // 9, total 18
        assertEquals("You win with blackjack", gameLogic.whoWon(playerHand, dealerHand)); // Player should win with a blackjack
    }

    @Test
    void testWhoWonDealerWins() {
        playerHand.add(new Card("Hearts", 10)); // 10
        playerHand.add(new Card("Diamonds", 10)); // 10
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Spades", 1)); // Ace as 11
        assertEquals("Dealer wins with blackjack", gameLogic.whoWon(playerHand, dealerHand)); // Dealer should win with a blackjack
    }

    @Test
    void testWhoWonTie() {
        playerHand.add(new Card("Hearts", 10)); // 10
        playerHand.add(new Card("Diamonds", 10)); // 10
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Spades", 10)); // 10
        assertEquals("Tie", gameLogic.whoWon(playerHand, dealerHand)); // Should be a tie
    }

    @Test
    void testEvaluateBankerDraw() {
        dealerHand.add(new Card("Clubs", 10)); // 10
        dealerHand.add(new Card("Spades", 6)); // 6
        assertTrue(gameLogic.evaluateBankerDraw(dealerHand)); // Dealer should draw with a hand total of 16 or less

        dealerHand.add(new Card("Spades", 2)); // Now total is 18
        assertFalse(gameLogic.evaluateBankerDraw(dealerHand)); // Dealer should not draw with a hand total of 17 or more
    }

    @Test
    public void testIsBlackjackTrue() {
        // Assuming BlackjackGame has a constructor that initializes gameLogic and theDealer
        BlackjackGame game = new BlackjackGame();
        game.getPlayerHand().add(new Card("Hearts", 1)); // Ace
        game.getPlayerHand().add(new Card("Diamonds", 10)); // Ten
        assertTrue(game.isBlackjack(game.getPlayerHand()));
    }

    @Test
    public void testIsBustedTrue() {
        BlackjackGame game = new BlackjackGame();
        game.getPlayerHand().add(new Card("Hearts", 10)); // Ten
        game.getPlayerHand().add(new Card("Clubs", 10)); // Ten
        game.getPlayerHand().add(new Card("Diamonds", 2)); // Two
        assertTrue(game.isBusted(game.getPlayerHand()));
    }

    @Test
    public void testResetHands() {
        BlackjackGame game = new BlackjackGame();
        game.getPlayerHand().add(new Card("Hearts", 9));
        game.getDealerHand().add(new Card("Clubs", 8));
        game.resetHands();
        assertTrue(game.getPlayerHand().isEmpty());
        assertTrue(game.getDealerHand().isEmpty());
    }

}

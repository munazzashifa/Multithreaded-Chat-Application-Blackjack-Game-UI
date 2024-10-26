//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CardTest {
//    @Test
//    void createCardAndValidateSuitAndValue() {
//        Card card = new Card("Hearts", 10);
//        assertNotNull(card, "Card should be created");
//        assertEquals("Hearts", card.getSuit());
//        assertEquals(10, card.getValue());
//    }
//
//    @Test
//    void validateCardValueBoundaries() {
//        Card lowValueCard = new Card("Diamonds", 1); // Ace
//        assertEquals(1, lowValueCard.getValue());
//
//        Card highValueCard = new Card("Spades", 13); // King
//        assertEquals(13, highValueCard.getValue());
//    }
//
//    @Test
//    void validateCardSuit() {
//        Card clubCard = new Card("Clubs", 5);
//        assertEquals("Clubs", clubCard.getSuit());
//
//        Card spadeCard = new Card("Spades", 5);
//        assertEquals("Spades", spadeCard.getSuit());
//
//        Card diamondCard = new Card("Diamonds", 5);
//        assertEquals("Diamonds", diamondCard.getSuit());
//
//        Card heartCard = new Card("Hearts", 5);
//        assertEquals("Hearts", heartCard.getSuit());
//    }
//
////    @Test
////    void testCardConstructorAndGettermethods() {
////        // Test with a number card
////        Card numberCard = new Card("Hearts", 5);
////        assertEquals("Hearts", numberCard.getSuit());
////        assertEquals(5,numberCard.getValue());
////
////        // Test with a face card (Jack, Queen, King)
////        Card faceCard = new Card("Spades", 13);
////        assertEquals("Spades", faceCard.getSuit());
////        assertEquals(13, faceCard.getValue());
////    }
////
////    @Test
////    void testCardValueBoundaries() {
////        // Test the lowest value (Ace)
////        Card lowCard = new Card("Diamonds", 1);
////        assertEquals("Diamonds", lowCard.getSuit());
////        assertEquals(1, lowCard.getValue());
////
////        // Test the highest value (King)
////        Card highCard = new Card("Clubs", 13);
////        assertEquals("Clubs", highCard.getSuit());
////        assertEquals(13, highCard.getValue());
////    }
//}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {


    @Test
    void cardCreationAndAttributes() {
        Card card = new Card("Hearts", 10);
        assertEquals("Hearts", card.getSuit());
        assertEquals(10, card.getValue());
    }

    @Test
    void cardImageFileNameForNumberCard() {
        Card card = new Card("Clubs", 2);
        assertEquals("2_of_clubs", card.getImageFileName());
    }

    @Test
    void cardImageFileNameForFaceCard() {
        Card jack = new Card("Spades", 11);
        assertEquals("jack_of_spades", jack.getImageFileName());

        Card queen = new Card("Diamonds", 12);
        assertEquals("queen_of_diamonds", queen.getImageFileName());

        Card king = new Card("Hearts", 13);
        assertEquals("king_of_hearts", king.getImageFileName());
    }

    @Test
    void cardImageFileNameForAce() {
        Card ace = new Card("Hearts", 1);
        assertEquals("ace_of_hearts", ace.getImageFileName());
    }

    @Test
    void setHiddenAndGetHidden() {
        Card card = new Card("Diamonds", 7);
        assertFalse(card.isHidden());

        card.setHidden(true);
        assertTrue(card.isHidden());
    }

    @Test
    void validateImageFileName() {
        Card aceOfHearts = new Card("Hearts", 1);
        assertEquals("ace_of_hearts", aceOfHearts.getImageFileName());

        Card kingOfClubs = new Card("Clubs", 13);
        assertEquals("king_of_clubs", kingOfClubs.getImageFileName());

        Card fiveOfDiamonds = new Card("Diamonds", 5);
        assertEquals("5_of_diamonds", fiveOfDiamonds.getImageFileName());
    }

//    @Test
//    void createCardAndValidateSuitAndValue() {
//        Card card = new Card("Hearts", 10);
//        assertNotNull(card, "Card should be created");
//        assertEquals("Hearts", card.getSuit());
//        assertEquals(10, card.getValue());
//    }
//
//    @Test
//    void validateCardValueBoundaries() {
//        Card lowValueCard = new Card("Diamonds", 1); // Ace
//        assertEquals(1, lowValueCard.getValue());
//
//        Card highValueCard = new Card("Spades", 13); // King
//        assertEquals(13, highValueCard.getValue());
//    }
//
//    @Test
//    void validateCardSuit() {
//        Card clubCard = new Card("Clubs", 5);
//        assertEquals("Clubs", clubCard.getSuit());
//
//        Card spadeCard = new Card("Spades", 5);
//        assertEquals("Spades", spadeCard.getSuit());
//
//        Card diamondCard = new Card("Diamonds", 5);
//        assertEquals("Diamonds", diamondCard.getSuit());
//
//        Card heartCard = new Card("Hearts", 5);
//        assertEquals("Hearts", heartCard.getSuit());
//    }


}

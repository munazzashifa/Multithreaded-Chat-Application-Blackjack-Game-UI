import java.util.ArrayList;
public class BlackjackGameLogic {


    public boolean isBlackjack(ArrayList<Card> hand) {
        // Checks if the hand is a natural blackjack
        if (hand.size() == 2 && handTotal(hand) == 21) {
            return (hand.get(0).getValue() == 1 || hand.get(0).getValue() >= 10) &&
                    (hand.get(1).getValue() == 1 || hand.get(1).getValue() >= 10);
        }
        return false;
    }

    public String whoWon(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        boolean playerBlackjack = isBlackjack(playerHand);
        boolean dealerBlackjack = isBlackjack(dealerHand);
        int playerScore = handTotal(playerHand);
        int dealerScore = handTotal(dealerHand);


        if (playerBlackjack && !dealerBlackjack) {
            return "You win with blackjack";
        } else if (!playerBlackjack && dealerBlackjack) {
            return "Dealer wins with blackjack";
        } else if (playerBlackjack && dealerBlackjack) {
            return "Tie with blackjack";
        } else if (playerScore > 21 ) {
            return "Busted";
        } else if ((playerScore > dealerScore && playerScore <= 21) || dealerScore > 21) {
            return "You win";
        } else if (playerScore < dealerScore && dealerScore <= 21 ) {
            return "Dealer wins";
        } else {
            return "Tie";
        }
    }

    public int handTotal(ArrayList <Card> hand){
        int score = 0;
        int aces = 0;

        for(Card card : hand){
            if (card != null) {
                int value = card.getValue();
                if (value > 10) {
                    value = 10;
                } else if (value == 1) {
                    aces++;
                    value = 11;
                }
                score += value;
            }
        }
        while(score > 21 && aces > 0){
            score -= 10;
            aces--;
        }
        return score;
    }

    public boolean evaluateBankerDraw(ArrayList <Card> hand){

        return handTotal(hand) <= 16;
    }
}
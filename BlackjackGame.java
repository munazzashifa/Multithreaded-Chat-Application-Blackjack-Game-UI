import java.util.ArrayList;
public class BlackjackGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> dealerHand;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet;
    double totalWinnings;
    double totalMoney;
    BlackjackGameLogic blackjackGame;

    //  This method returns the game logic instance
    public BlackjackGameLogic getGameLogic() {
        return this.gameLogic;
    }

    // This method returns the player's hand
    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    // This method returns the dealer's hand
    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    // Checks if the hand is a blackjack (Ace and a 10-point card)
    public boolean isBlackjack(ArrayList<Card> hand) {
        return gameLogic.handTotal(hand) == 21 && hand.size() == 2;
    }

    // Checks if the hand is busted (exceeds 21)
    public boolean isBusted(ArrayList<Card> hand) {
        return gameLogic.handTotal(hand) > 21;
    }

    public BlackjackGame() {
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        gameLogic = new BlackjackGameLogic();
        theDealer = new BlackjackDealer();
        // Initialize other members as needed
    }
    public double getTotalWinnings() {
        return totalWinnings;

    }

    // place bets
    public void placeBet(double bet) {
        currentBet = bet;
        totalMoney -= bet;
    }

    // player hits
    public void playerHits() {
        playerHand.add(theDealer.drawOne());
        if(gameLogic.handTotal(playerHand) > 21) {
            System.out.println("Busted! You lose.");
            currentBet = 0;
        }
    }


//     player stays
    public void playerStay() {
        String winner;
        if (!dealerHand.isEmpty()) {
            while (gameLogic.handTotal(dealerHand) <= 16) {
                dealerHand.add(theDealer.deal1Card());
            }
        }
        System.out.println("Dealer's hand total: " + gameLogic.handTotal(dealerHand));
        winner = gameLogic.whoWon(playerHand, dealerHand);
        System.out.println(winner);


        if(winner.equals("You win")){
            totalWinnings += currentBet;
        }
        else if(winner.equals("Dealer wins")){
            totalWinnings-=currentBet;
        }
    }

    //reset Hands
    public void resetHands(){
        playerHand.clear();
        dealerHand.clear();
        theDealer.resetHand();
    }


     //A method to get the image file name for a given card
    public String getImageFileName(Card card) {
        String rank = String.valueOf(card.getValue()); // Assuming getValue returns 1-13
        String suit = card.getSuit().toLowerCase(); // Assuming getSuit returns "Hearts", "Diamonds", etc.
        // You might need to adjust for face cards, as usually images are named "jack", "queen", "king", "ace"
        if (card.getValue() == 1) rank = "ace";
        if (card.getValue() == 11) rank = "jack";
        if (card.getValue() == 12) rank = "queen";
        if (card.getValue() == 13) rank = "king";
        return rank + "_of_" + suit + ".png"; // Assuming this is how your images are named
    }


}


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class BlackjackDealer {
    private ArrayList<Card> deck;
    //ArrayList<Card> newhand = new ArrayList<>();
    private Card[] deckArray = new Card[52];

        public BlackjackDealer() {
            generateDeck();
        }

        public void generateDeck() {
            String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
            deck = new ArrayList<>();
            for (String suit : suits) {
                for (int i = 1; i <= 13; i++) {
                    //trying to implement cards
//                    String imageDeck = "2_of_clubs.png", "2_of_diamonds.png", "2_of_hearts.png" + suit.toLowerCase();

                    deck.add(new Card(suit, i));
                }
            }
            shuffleDeck();
        }


    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;
    }
    public Card deal1Card(){
            Card card = deck.get(0);
            deck.remove(0);
            return card;
    }
    public Card drawOne(){
            if(deck.isEmpty()){
                return null;
            }
            return deck.remove(0);
    }
    public void shuffleDeck(){
//            Random r = new Random();
//            for(int i=51;i>0;i--){
//                int j = r.nextInt(i+1);
//                Card temp = deckArray[i];
//                deckArray[i] = deckArray[j];
//                deckArray[j]=temp;
//            }

        //can do tis way
        //Collections.shuffle(deck, new Random());

        // another option
//        Collections.shuffle(deck);

        //or
         ArrayList<Card> shuffled = new ArrayList<Card>();
         while(deck.size()>0) {
             int cardToPull = (int) (Math.random() * (deck.size() - 1));
             shuffled.add(deck.get(cardToPull));
             deck.remove(cardToPull);
         }
         deck=shuffled;
    }

    public int deckSize(){
            return deck.size();
    }

    public void resetHand(){
            generateDeck();
            shuffleDeck();
    }

    // method to provide a way for tests to get the entire deck
    public ArrayList<Card> getDeck() {

            return new ArrayList<>(deck); // Return a copy of the deck
    }



}


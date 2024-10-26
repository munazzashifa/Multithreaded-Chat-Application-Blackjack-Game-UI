public class Card {
    String suit;
    int value;

    public Card(String theSuit, int theValue) {
        this.suit = theSuit; // e.g. "spades", "diamonds", "clubs", "hearts"
        this.value = theValue; // e/g "2", "3", "4",..."jack", "queen, "king", "ace"
    }

    public int getValue(){
        return value;
    }

    public String getSuit(){
        return suit;
    }


    public String getImageFileName() {
        String val;
        if(value==1){
            val = "ace";
        }
        else if(value==11){
            val = "jack";

        }
        else if(value ==12){
            val= "queen";

        }
        else if(value ==13){
            val = "king";
        }
        else{
            val = String.valueOf(value);
        }
        return val + "_of_" + suit.toLowerCase();
    }


    private boolean hidden;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}

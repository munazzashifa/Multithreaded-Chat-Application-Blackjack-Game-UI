import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class JavaFXTemplate extends Application{

    private BlackjackGame blackjackGame;
    private BlackjackDealer blackjackDealer;

    private BlackjackGameLogic gameLogic;

    // Define the display VBoxes at the class level
    private VBox playerHandDisplay = new VBox(5); // Use some spacing between cards
    private VBox dealerHandDisplay = new VBox(5);

    TextField startMoney = new TextField();
    Label displayMoney = new Label();
    TextField betAmount = new TextField();

    public static void main(String[] args){
        launch(args);
    }

    public void determineOutcomeAndSwitchScene(Stage primaryStage) {
        String test = blackjackGame.getGameLogic().whoWon(blackjackGame.getPlayerHand(), blackjackGame.getDealerHand());

        System.out.println("test:" + test);
        switch (test) {
            case "You win with blackjack":
                blackjack(primaryStage);
                break;
            case "Dealer wins with blackjack":
                dealblackjack(primaryStage);
                break;
            case "Tie with blackjack":
                draw(primaryStage);
                break;
            case "You win":
                win(primaryStage);
                break;
            case "Dealer wins":
                lose(primaryStage);
                break;
            case "Tie":
                draw(primaryStage);
                break;
            case "Busted":
                busted(primaryStage);
                break;
        }
    }


    @Override
    public void start(Stage primaryStage){

        blackjackGame = new BlackjackGame();
        blackjackDealer = new BlackjackDealer();
        blackjackGame.theDealer = blackjackDealer;

        primaryStage.setTitle("Blackjack Game");
        Label label= new Label("Welcome to Blackjack!");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Play");
        Button b2 = new Button("How to play");
        Image image = new Image("86RpIkbEsTPJI.png!sw800");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300); // Set the desired width
        imageView.setFitHeight(300);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, b1, b2);
        StackPane.setAlignment(b1, Pos.BOTTOM_CENTER);
        StackPane.setMargin(b1, new Insets(0, 0, 105, 0));
        StackPane.setAlignment(b2, Pos.BOTTOM_CENTER);
        StackPane.setMargin(b2, new Insets(0, 0, 20,0));

        VBox vbox = new VBox(label, stackPane);
        //VBox vbox = new VBox(label, imageView, b1, b2);
        vbox.setStyle("-fx-background-color: black;");
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 800, 600);
        b2.setOnAction(e -> rules(primaryStage));
        b1.setOnAction(e -> game(primaryStage));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void game(Stage primaryStage) {
        HBox playerHandDisplay = new HBox();
        HBox dealerHandDisplay = new HBox();

        Label label = new Label("Dealer's Hand");
        label.setStyle("-fx-font-size: 24px; -fx-alignment: center;");
        Label label2 = new Label("Your Hand");
        label2.setStyle("-fx-font-size: 24px; -fx-alignment: center;");

        Button stayButton = new Button("Stay");
        Button hitButton = new Button("Hit");
        Button exitButton = new Button("Exit");
        Button dealButton = new Button("Deal");

        betAmount.setPromptText("Enter bet");
        betAmount.setMaxWidth(150);
        betAmount.setEditable(true);

        startMoney.setPromptText("Enter start money");
        startMoney.setMaxWidth(150);

        displayMoney.setStyle("-fx-font-size: 15px;");

        HBox buttonBox = new HBox(stayButton, hitButton, dealButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        VBox leftBox = new VBox(startMoney, betAmount);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setSpacing(10);
        leftBox.setPadding(new Insets(50, 0, 0, 0));

        VBox rightBox = new VBox(displayMoney, exitButton);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.setSpacing(10);
        rightBox.setPadding(new Insets(50, 0, 0, 0));

        HBox topHBox = new HBox(leftBox, rightBox);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setSpacing(500);


        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topHBox);

        // THE PLAYER HAND AND DEALER HAND DOES NOT HAVE ELEMENTS IN THEM, BUT WHY?
        // Look at when you create the ImageView for the cards, and make sure you add them to the variable "playerHandDisplay"

        System.out.println(dealerHandDisplay.getChildren());
        System.out.println(playerHandDisplay.getChildren());


        borderPane.setLeft(dealerHandDisplay);
        borderPane.setBottom(playerHandDisplay);
        borderPane.setCenter(buttonBox);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        hitButton.setDisable(true);
        stayButton.setDisable(true);
        dealButton.setDisable(true);

        startMoney.setOnAction(e -> {
            String startText = startMoney.getText();
            if (startText.isEmpty()) {
                System.err.println("Please enter a valid starting amount.");
                return;
            }
            try {
                double startAmount = Double.parseDouble(startText);
                if (startAmount <= 0) { // Check if start amount is zero or negative
                    System.err.println("Starting amount must be greater than zero.");
                    return;
                }
                startMoney.setText("Starting amount: $" + startText);
            startMoney.setEditable(false);
            betAmount.setEditable(true);
            hitButton.setDisable(true);
            stayButton.setDisable(true);
            dealButton.setDisable(true);
            } catch (NumberFormatException ex) {
                System.err.println("Please enter a valid numeric starting amount.");
            }

        });

        betAmount.setOnAction(e -> {
            try {
                String betText = betAmount.getText().replace("Bet amount: $", "");
                double bet = Double.parseDouble(betText);
                double startingAmount = Double.parseDouble(startMoney.getText().replace("Starting amount: $", ""));
                System.out.println("Bet zero:" +bet);
                if (bet <= 0.0) {
                    // Display an error message if the bet amount is zero or negative
//                    hitButton.setDisable(true);
//                    stayButton.setDisable(true);
                    dealButton.setDisable(true);
                    System.err.println("Error: Bet amount must be greater than zero");

                }


                else if (bet > startingAmount) {
                    betExceeds(primaryStage);
                } else {
                    blackjackGame.placeBet(bet);
                    double remainingAmount = startingAmount - bet;
                    if (remainingAmount < 0) {
                        remainingAmount = 0;
                    }
                    displayMoney.setText("Money left: $" + remainingAmount);
                    betAmount.setText("Bet amount: $" + betText);
                    betAmount.setEditable(false);
                    if (remainingAmount < 0) {
                        start(primaryStage);
                    }
                }
                hitButton.setDisable(true);
                stayButton.setDisable(true);
                dealButton.setDisable(false);

                //betAmount.setEditable(true);
            } catch (NumberFormatException ex) {
                System.err.println("Error: Invalid input format");
            }
        });

        dealButton.setOnAction(e ->{
            hitButton.setDisable(false);
            stayButton.setDisable(false);
            dealButton.setDisable(true);
            // Clear both player's and dealer's hands
            blackjackGame.playerHand.clear();
            blackjackGame.dealerHand.clear();

            // Deal new hands from the deck
            blackjackGame.playerHand.addAll(blackjackGame.theDealer.dealHand());
            blackjackGame.dealerHand.addAll(blackjackGame.theDealer.dealHand());

            // Clear the display of both player's and dealer's hands
            playerHandDisplay.getChildren().clear();
            dealerHandDisplay.getChildren().clear();


            for (Card card : blackjackGame.playerHand) {
                if (card != null) { // Add null check
                    String imagePath = "src/main/resources/PNG-cards-1.3/PNG-cards-1.3/" + card.getImageFileName() + ".png";
                    try {
                        InputStream stream = new FileInputStream(imagePath);
                        Image cardImage = new Image(stream);
                        ImageView imageCard = new ImageView(cardImage);
                        imageCard.setFitWidth(110);
                        imageCard.setFitHeight(154);
                        playerHandDisplay.getChildren().add(imageCard);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            for (Card card : blackjackGame.dealerHand) {
                if (card != null) { // Add a null check
                    String imagePath = "src/main/resources/PNG-cards-1.3/PNG-cards-1.3/" + card.getImageFileName() + ".png";
                    System.out.println("Displaying cards: " + imagePath);
                    System.out.println("Displaying size: " + blackjackGame.dealerHand.size());
                    try {
                        InputStream stream = new FileInputStream(imagePath);
                        Image cardImage = new Image(stream);
                        ImageView imageCard = new ImageView(cardImage);
                        imageCard.setFitWidth(110);
                        imageCard.setFitHeight(154);
                        dealerHandDisplay.getChildren().add(imageCard);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });


        stayButton.setOnAction(e -> {
            int handTotal = blackjackGame.getGameLogic().handTotal(blackjackGame.getPlayerHand());
            blackjackGame.playerStay();


            System.out.println("Displaying size: " + blackjackGame.dealerHand.size());
            System.out.println("Player Hand" + handTotal);
            if(blackjackGame.dealerHand.size() > 2) {
                for (int i=2; i< blackjackGame.dealerHand.size(); i++) {
                    Card card = blackjackGame.dealerHand.get(i);
                    String imagePath = "src/main/resources/PNG-cards-1.3/PNG-cards-1.3/" + card.getImageFileName() + ".png";
                    System.out.println("Displaying cards: " + imagePath);
                    try {
                        InputStream stream = new FileInputStream(imagePath);
                        Image cardImage = new Image(stream);
                        ImageView imageCard = new ImageView(cardImage);
                        imageCard.setFitWidth(110);
                        imageCard.setFitHeight(154);
                        dealerHandDisplay.getChildren().add(imageCard);

                    }
                    catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            PauseTransition pause = new PauseTransition(Duration.millis(2000));
            // Set the action to be performed after the delay
            pause.setOnFinished(event -> determineOutcomeAndSwitchScene(primaryStage));
            // Start the pause transition
            pause.play();
            hitButton.setDisable(true);
            stayButton.setDisable(true);

        });


        hitButton.setOnAction(e ->{

            blackjackGame.playerHits();

            playerHandDisplay.getChildren().clear();
            int handTotal = blackjackGame.getGameLogic().handTotal(blackjackGame.getPlayerHand());
            System.out.println("Hand Total: " + handTotal);
            for (Card card : blackjackGame.playerHand) {
                if (card != null) {
                    String imagePath = "src/main/resources/PNG-cards-1.3/PNG-cards-1.3/" + card.getImageFileName() + ".png";
                    System.out.println("Displaying cards: " + imagePath);
                    try {
                        InputStream stream = new FileInputStream(imagePath);
                        Image cardImage = new Image(stream);
                        ImageView imageCard = new ImageView(cardImage);
                        imageCard.setFitWidth(110);
                        imageCard.setFitHeight(154);
                        playerHandDisplay.getChildren().add(imageCard);

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if (handTotal > 21) {
                    // Player has busted, show the "Busted" screen
                    PauseTransition pause = new PauseTransition(Duration.millis(2000));
                    // Set the action to be performed after the delay
                    pause.setOnFinished(event -> busted(primaryStage));
                    // Start the pause transition
                    pause.play();
                }
            }

        });

        exitButton.setOnAction(e -> start(primaryStage));
    }

    private void playAgain(Stage primaryStage, double remainingAmount) {
        startMoney.setText("Starting amount: $" + remainingAmount); // Update the start money for the new game
        displayMoney.setText("Money left: $" + remainingAmount); // Update displayMoney if needed
        betAmount.setText(""); // Reset the bet amount field for the new game
        betAmount.setEditable(true);
        game(primaryStage); // Start a new game
    }

    public void win(Stage primaryStage){
        Label label = new Label("You win");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: green;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> {
            String moneyText = displayMoney.getText().replace("Money left: $", "");
            String betText = betAmount.getText().replace("Bet amount: $", "");
            double remainingAmount = (Double.parseDouble(betText) *2)+ Double.parseDouble(moneyText);
            playAgain(primaryStage, remainingAmount);
        });
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void lose (Stage primaryStage){
        Label label = new Label("You lose");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: red;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> {
            // Extract the remaining money from the displayMoney Label
            String moneyText = displayMoney.getText().replace("Money left: $", "");
            double remainingAmount = Double.parseDouble(moneyText);
            playAgain(primaryStage, remainingAmount); // Call playAgain method with the remaining amount
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void draw(Stage primaryStage){
        Label label = new Label("It's a Draw");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: orange;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> {
            String moneyText = displayMoney.getText().replace("Money left: $", "");
            double remainingAmount = Double.parseDouble(moneyText);
            playAgain(primaryStage, remainingAmount);
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void busted(Stage primaryStage){
        Label label = new Label("Your Busted");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: yellow;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> {
            String moneyText = displayMoney.getText().replace("Money left: $", "");
            double remainingAmount = Double.parseDouble(moneyText);
            playAgain(primaryStage, remainingAmount);
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void dealblackjack(Stage primaryStage){
        Label label = new Label("Dealer got a Blackjack");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: black;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> {
            String moneyText = displayMoney.getText().replace("Money left: $", "");
            double remainingAmount = Double.parseDouble(moneyText);
            playAgain(primaryStage, remainingAmount);
        });
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void blackjack(Stage primaryStage){
        Label label = new Label("You got a Blackjack");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: black;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> {
            String moneyText = displayMoney.getText().replace("Money left: $", "");
            String betText = betAmount.getText().replace("Bet amount: $", "");
            double bet = Double.parseDouble(betText);
            double remainingAmount = Double.parseDouble(moneyText) + (bet + (bet*1.5));
            playAgain(primaryStage, remainingAmount);
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void betExceeds(Stage primaryStage){
        Label label = new Label("Bet exceeds wallet amount ");
        label.setStyle("-fx-font-size: 24px;");
        Button b1 = new Button("Return Home");
        Button b2 = new Button("Play Again");

        VBox vbox = new VBox(label, b1, b2);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements

        // Increase spacing between labels and buttons
        VBox.setMargin(label, new Insets(10, 0, 105, 0)); // Adjust top margin for label2
        VBox.setMargin(b1, new Insets(20, 0, 20, 0)); // Adjust top margin for b1

        BorderPane root = new BorderPane();
        root.setCenter(vbox); // Center the VBox in the BorderPane
        root.setStyle("-fx-background-color: purple;");

        b1.setOnAction(e -> start(primaryStage));
        b2.setOnAction(e -> game(primaryStage));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void rules(Stage primaryStage){
        Label label = new Label("Rules of the Game:");
        label.setStyle("-fx-font-size: 24px;");
        Label label2 = new Label("The game Blackjack has the objective to get to the value of 21 without going over. " +
                "The player needs to set a starting amount and bet before they can deal out the cards" +
                "Each player receives 2 cards in the beginning of the  game. " +
                "The player has an option to hit and receive another card or stay and keep their current hand. " +
                "If the hand exceeds 21, they will bust. Once all players decided to stay, it's the dealers turn. " +
                "The dealer must hit on any hand with a value of 16 or lower and stay on any hand with a value of 17 or higher. " +
                "The winner is determined by comparing hand values. " +
                "If a player achieves a hand value of 21 with their initial two cards (an Ace and a 10-point card), they have 'blackjack' and win 150% of their bet, unless the dealer also has blackjack, resulting in a draw. " +
                "The game continues until players decide to stop playing or run out of money. May luck be on your side as you aim for the lucky 21!");
        label2.setWrapText(true);
        Button b1= new Button("Exit");
        Image image = new Image("blackjack-card-values.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        VBox vbox = new VBox(label, label2, imageView, b1);
        vbox.setAlignment(Pos.CENTER); // Center align all elements vertically
        vbox.setSpacing(20); // Set spacing between all elements
        vbox.setStyle("-fx-background-color: grey;");

        b1.setOnAction(e -> start(primaryStage));
        // Create the scene and set it on the primaryStage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
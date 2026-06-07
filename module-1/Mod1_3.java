//Vanessa Espino
//Write a program that displays four images from a deck of 52 cards

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mod1_3 extends Application {

	private static final int TOTAL_CARDS = 52;
	private static final int CARDS_TO_SHOW = 4;
	private final HBox cardContainer = new HBox(15);
	private final List<Integer> cardDeck = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		for (int i = 1; i <= TOTAL_CARDS; i++){
			cardDeck.add(i);
		}

		cardContainer.setAlignment(Pos.CENTER);
		cardContainer.setPadding(new Insets(20));

		Button btnRefresh = new Button("Refresh Cards");


		btnRefresh.setOnAction(e -> refreshCards());


		refreshCards();


		VBox root = new VBox(20, cardContainer, btnRefresh);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(20));

		Scene scene = new Scene(root, 700, 400);
		primaryStage.setTitle("Random 4 Cards Display");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	private void refreshCards() {
		cardContainer.getChildren().clear();
		Collections.shuffle(cardDeck);



		for (int i = 0; i < CARDS_TO_SHOW; i++) {
			int cardNum = cardDeck.get(i);

			String imagePath = "file:/Users/Vanessa/Desktop/csd-420/module-1/cards/" + cardNum + ".png";


			try {
				Image cardImage = new Image(imagePath, 130, 200, true, true);
				ImageView imageView = new ImageView(cardImage);
				cardContainer.getChildren().add(imageView);
			} catch (Exception ex) {
				System.out.println("Could not load: " + imagePath);
			}
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
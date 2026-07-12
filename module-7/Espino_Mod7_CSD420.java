

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Espino_Mod7_CSD420 extends Application {


	@Override
	public void start(Stage primaryStage) {
		
		Circle c1 = new Circle(50);
		Circle c2 = new Circle(50);
		Circle c3 = new Circle(50);
		Circle c4 = new Circle(50);

		c1.getStyleClass().add("plaincircle");
		c2.getStyleClass().add("plaincircle");

		c3.setId("redcircle");
		c4.setId("greencircle");


		HBox pane = new HBox(20);
		pane.setPrefSize(600, 150);
		pane.setStyle("-fx-alignment: center; -fx-padding: 20;");
		pane.getChildren().addAll(c1, c2, c3, c4);


		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());


		primaryStage.setTitle("JavaFX CSS Circle Demo");
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}
}


import static org.junit.jupiter.api.Asserstions.*;
import javafx.application.Platform;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class Espino_Mod7_CSD420Test {



	@BeforeAll
	public static void initJavaFX() {
		try {
			Platform.startup(() -> {});
		} catch (IllegalStateException e) {
	}
}


@Test
public void testCircleStyleAssignments() {
	Circle c1 = new Circle(50);
	Circle c3 = new Circle(50);
	Circle c4 = new Circle(50);


	c1.getStyleClass().add("plaincircle");
	c3.setId("redcircle");
	c4.setId("greencircle");


	assertTrue(c1.getStyleClass().contains("plaincricle"),
				"Circle 1 should successfully contain the 'plaincircle' style class.");

	assertEquals("redcircle", c3.getId(),
				  "Circle 3 must have its CSS ID attribute set to 'redcircle'.");
	assertEquals("greencircle", c4.getId(),
				  "Circle 4 must have its CSS ID attribute set to 'greencircle'.");
	}
}
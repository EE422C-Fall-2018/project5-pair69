package application;

import assignment5.Params;
import assignment5.Critter.CritterShape;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class printCritter {

	public static Shape print(CritterShape shape, Color outline, Color fill, int x, int y) {
		Shape s = null;
		switch(shape) {
		case CIRCLE:
			s = new Circle(10/2);
			break;
		case SQUARE:
			s = new Rectangle(10,10);
			break;
		case TRIANGLE:
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[]{
				    (double)10/2.0, 1.0,
				    (double)10 - 1.0, (double)10 - 1.0,
				    1.0, (double)10-1.0
			});
			s = triangle;
			break;
		case DIAMOND:
			Polygon diamond = new Polygon();
			diamond.getPoints().addAll(new Double[]{
					(double)10/2.0, 1.0,
					(double)10 - 1.0, (double)10/2.0,
					(double)10/2.0, (double)10 - 1.0,
					1.0, (double)10/2.0
			});
			s = diamond;
			break;
		//case STAR:
			//figure this out later ig
		default:
			break;
		}
		
		s.setFill(fill);
		s.setStroke(outline);
		CritterWorld.world.add(s, x, y);
		return null;
	}
	public static void clearDisplay() {
		Node node = CritterWorld.world.getChildren().get(0);
		CritterWorld.world.getChildren().clear();
		CritterWorld.world.getChildren().add(0,node);
		CritterWorld.world.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
		
	}
}

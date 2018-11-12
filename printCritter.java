package application;

import assignment5.Critter.CritterShape;
import assignment5.Params;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class printCritter {
	
	public static Shape print(CritterShape shape, Color outline, Color fill, int x, int y) {
		int size = 0;
		if(Params.world_width > 50 && Params.world_height > 50) {
			size = 5;
		}
		else {
			size = 10;
		}
		Shape s = null;
		switch(shape) {
		case CIRCLE:
			s = new Circle(size/2);
			break;
		case SQUARE:
			s = new Rectangle(size,size);
			break;
		case TRIANGLE:
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[]{
				    (double)size/2.0, 1.0,
				    (double)size - 1.0, (double)size - 1.0,
				    1.0, (double)size-1.0
			});
			s = triangle;
			break;
		case DIAMOND:
			Polygon diamond = new Polygon();
			diamond.getPoints().addAll(new Double[]{
					(double)size/2.0, 1.0,
					(double)size - 1.0, (double)size/2.0,
					(double)size/2.0, (double)size - 1.0,
					1.0, (double)size/2.0
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



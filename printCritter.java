package application;

import assignment5.Critter;
import assignment5.Params;
import assignment5.Critter.CritterShape;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class printCritter {
	private static void setCanvas(Canvas canvas, Image img) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(img, 0, 0,canvas.getWidth(), canvas.getHeight());
    }
	public static Shape print(CritterShape shape, Color outline, Color fill, int x, int y) {
		Shape s = null;
		int size;
			if (Params.world_height <= 7||Params.world_width<=7) {
				size = 50;
			}
			else if (Params.world_height <= 30||Params.world_width<=30) {
				size = 18;
			}
			else if (Params.world_height <= 50||Params.world_width <= 50) {
				size = 12;
			}
			else if (Params.world_height <= 75||Params.world_width <= 75) {
				size = 8;
			}
			else if (Params.world_height <= 100||Params.world_width <= 100) {
				size = 6;
			}
			else if (Params.world_height <= 120||Params.world_height <= 120) {
				size = 4;
			}
			else {
				size = 3;
			}
		
		switch(shape) {
		case CIRCLE:
			s = new Circle(size/2);
			s.setFill(fill);
			s.setStroke(outline);
			break;
		case SQUARE:
			s = new Rectangle(size,size);
			
//			if(size <= 10) {
//				s = new Rectangle(0.75*size*size,0.75*size*size);
//			}
//			else {
//				s = new Rectangle(2*size,2*size);
//			}
			s.setFill(fill);
			s.setStroke(outline);
			break;
		case TRIANGLE:
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[]{
					(double)size/2.0, 1.0,
				    (double)size - 1.0, (double)size - 1.0,
				    1.0, (double)size-1.0
			});
			s = triangle;
			s.setFill(fill);
			s.setStroke(outline);
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
			s.setFill(fill);
			s.setStroke(outline);
			break;
		case STAR:
			Polygon star = new Polygon();
			
			Double[] points = {2050.0, 1500.0, 2170.0, 1860.0, 2590.0, 1860.0, 
				      2230.0, 2040.0, 2330.0, 2460.0, 2050.0, 2220.0, 1770.0, 2460.0, 1870.0, 2040.0, 
				      1510.0, 1860.0, 1930.0, 1860.0};
			if(size <= 6) {
				for(int i = 0; i < points.length; i++) {
					points[i] = points[i]/(45*size^2);
				}
			}
			else {
				for(int i = 0; i < points.length; i++) {
					points[i] = points[i]/(size*7);
				}
			}
			 star.getPoints().addAll( points );
			 s=star;
			 s.setFill(fill);
			 s.setStroke(outline);
			 break;
		
		case TEKASHI:
		
		s = new Rectangle(size,size);
		
//		if(size <= 10) {
//			s = new Rectangle(0.75*size*size,0.75*size*size);
//		}
//		else {
//			s = new Rectangle(2*size,2*size);
//		}
		//Image img = new Image("https://static.gulfnews.com/polopoly_fs/1.2282395!/image/35137129.jpg_gen/derivatives/box_460346/35137129.jpg");
		//ImagePattern imgp = new ImagePattern(img);
		
		//s.setFill(imgp);
		s.setFill(fill);
		s.setStroke(outline);
		break;
		default:
			break;
		}
		
		
		CritterWorld.world.add(s, x, y);
		return null;
	}
	public static void clearDisplay() {
		Node node = CritterWorld.world.getChildren().get(0);
		CritterWorld.world.getChildren().clear();
		CritterWorld.world.getChildren().add(0,node);
		BackgroundImage myBI= new BackgroundImage(new Image("http://www.ece.utexas.edu/sites/default/files/portraits/VNK2016_smaller.jpg",1250,1000,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        
        CritterWorld.world.setBackground(new Background(myBI));
		
	}
	
	
		
	
}
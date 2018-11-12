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
		int size = 0;
		if(Params.world_height > 50 && Params.world_width>50) {
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
			if(size == 10) {
				s = new Rectangle(0.75*size*size,0.75*size*size);
			}
			else {
				s = new Rectangle(2*size,2*size);
			}
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
		case STAR:
			Polygon star = new Polygon();
			double shs = 5.0;
			Double[] points = {2050.0, 1500.0, 2170.0, 1860.0, 2590.0, 1860.0, 
				      2230.0, 2040.0, 2330.0, 2460.0, 2050.0, 2220.0, 1770.0, 2460.0, 1870.0, 2040.0, 
				      1510.0, 1860.0, 1930.0, 1860.0};
			if(size == 5) {
				for(int i = 0; i < points.length; i++) {
					points[i] = points[i]/(45*size^2);
				}
			}
			else {
				for(int i = 0; i < points.length; i++) {
					points[i] = points[i]/(size);
				}
			}
			 star.getPoints().addAll( points );
			 s=star;
			//figure this out later ig
			 break;
			 
//		case TEKASHI:
//			Rectangle tekashi;
//			if(size == 10) {
//				tekashi = new Rectangle(0.75*size*size,0.75*size*size);
//			}
//			else {
//				tekashi = new Rectangle(2*size,2*size);
//			}
//			//Image image = new Image(getClass().getResource("src/assignment5/35137129.png").toExternalForm())
//			Image img = new Image("https://static.gulfnews.com/polopoly_fs/1.2282395!/image/35137129.jpg_gen/derivatives/box_460346/35137129.jpg");
//			Canvas imgc = new Canvas();
//			setCanvas(imgc, img);
//			tekashi.setFill(imgc);
//			s = tekashi;
//			break;
//		default:
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
		BackgroundImage myBI= new BackgroundImage(new Image("http://www.ece.utexas.edu/sites/default/files/portraits/VNK2016_smaller.jpg",1250,1000,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        
        CritterWorld.world.setBackground(new Background(myBI));
		
	}
	
	
		
	
}
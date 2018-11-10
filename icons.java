package assignment5;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class icons {
	static int size = 10;
	static Shape getIcon(Critter.CritterShape shape) {
		Shape s = null;
		switch(shape) { 
			case SQUARE: s = new Rectangle(size, size); 
				break; 
			case CIRCLE: s = new Circle(size/2); 
				break;
			case TRIANGLE: s = makeT();
				break;
			case DIAMOND: s = makeD();
				break;
			case STAR: s = makeS();
		default:
			break;
		} 
		return s;
	}
	
	public static void changeSize(int i) {
		size = i; ///?????
	} 
	
	private static Shape makeT() {
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[]{
			    (double)size/2.0, 1.0,
			    (double)size - 1.0, (double)size - 1.0,
			    1.0, (double)size-1.0
		});
		return triangle;
	}	
	
	private static Shape makeD() {
		Polygon diamond = new Polygon();
		diamond.getPoints().addAll(new Double[]{
				(double)size/2.0, 1.0,
				(double)size - 1.0, (double)size/2.0,
				(double)size/2.0, (double)size - 1.0,
				1.0, (double)size/2.0
		});
		return diamond;
	}
	
	private static Shape makeS() {
		Polygon star = new Polygon();
		star.getPoints().addAll(new Double[]{
				(double)size/2.0, 1.0,
				(double)size/2.0 + (double)size/4.0, (double)size/3.0,
				(double)size - 1.0, (double)size/2.0,
				(double)size/2.0 + (double)size/4.0, (double)size/2.0 + 1.0,
			    (double)size - 1.0, (double)size - 1.0,
			    (double)size/2.0, (double)size/2.0 + (double)size/4.0,
			    1.0, (double)size-1.0,
			    (double)size/2.0 - (double)size/4.0, (double)size/2.0 + 1.0,
			    2.0, (double)size/2.0
				
		});
		return star;
	}
}

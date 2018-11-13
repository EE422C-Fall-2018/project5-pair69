package assignment5;

import assignment5.Critter.CritterShape;

public class Critter2 extends Critter{

	public String toString() { return "P"; }
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}

	@Override
	public void doTimeStep() {
		int x = Critter.getRandomInt(8);
		run(x);
		
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}

	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.YELLOW; }
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.YELLOW; }
}

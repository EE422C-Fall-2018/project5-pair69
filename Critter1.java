package assignment5;

import assignment5.Critter.CritterShape;

public class Critter1 extends Critter{

	public String toString() { return "K"; }
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}

	@Override
	public void doTimeStep() {
		int x = Critter.getRandomInt(8);
		walk(x);
		
	}

	@Override
	public boolean fight(String oponent) {
		int x = Critter.getRandomInt(8);
		if(look(x,false) == null) {
			walk(x);
			return false;
		}else {
			return true;
		}
	}

	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.PINK; }
}

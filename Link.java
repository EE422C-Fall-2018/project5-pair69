package assignment5;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import assignment5.Critter.CritterShape;

public class Link extends Critter {

	private int dir;
	private boolean hasMoved;

	@Override 
	public String toString() { return "2"; }

	public  Link() {
		dir = Critter.getRandomInt(2);
	}

	public boolean fight(String opponent) {
		if (!hasMoved) {
			if (opponent.equals("project5. Link")) { walk(dir); return false; }
		}
		return true;
	} 

	@Override 
	public void doTimeStep() {
		
		walk(dir);
		hasMoved = true;
		
	
		

			int new_dir = Critter.getRandomInt(2);
			switch(new_dir) {
				case 0: 
					dir = 2; 
				case 1: 
					dir = 6;
			}
			try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/assignment5/WW_Link_PowerAttack1.wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.TRIANGLE;
	}
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.CHARTREUSE; }

	


}

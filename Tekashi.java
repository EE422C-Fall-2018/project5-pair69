package assignment5;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Tekashi extends Critter{
	@Override
	public String toString() {return "1";}

	private int dir;
	private int sugma;
	private boolean hasMoved;


	public Tekashi() {
		dir = Critter.getRandomInt(8);
		hasMoved = false;
		sugma = 0;
	}
	
	public boolean fight(String opponent) {
		if (hasMoved == false) {
			if(getEnergy() >= 100) {
				return true;
			}
			else if(getEnergy() < 100) {
				run(dir);
				return false;
			}
		}
		return true; 
	}


	public void doTimeStep() {

		hasMoved = false;
		if(getEnergy() < 500) {
			boolean f = true;
				if(look(dir, f) == null || look(dir, f) == "Algae") {
					walk(dir);
					hasMoved = true;
			}
		}

		if(getEnergy() >= 500) {
			walk(dir);
			hasMoved = true;
			Tekashi baby = new Tekashi();
			reproduce(baby, Critter.getRandomInt(8));
			sugma++;
		}
		dir = Critter.getRandomInt(8);
//		try {
//	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/assignment5/SCUM_GANG_SOUND_EFFECT-uLZGG_6YZBo.wav").getAbsoluteFile());
//	        Clip clip = AudioSystem.getClip();
//	        clip.open(audioInputStream);
//	        clip.start();
//	    } catch(Exception ex) {
//	        System.out.println("Error with playing sound.");
//	        ex.printStackTrace();
//	    }
	}
	
	public static String runStats(java.util.List<Critter> list) {
		int bofa = 0 ;
		for(Object obj : list) {
			Tekashi crit = (Tekashi) obj;
			bofa += crit.getEnergy();
		}
		String s = "";
		s += list.size()  + "total Tekashis" + " " + bofa + "total energy";
		
		return s;
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.TEKASHI;
	}
	//public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.PINK; }
}

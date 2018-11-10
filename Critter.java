package assignment5;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.CritterWorld;
import application.printCritter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	public static HashMap <Key, List<Critter>> positions  = new HashMap<Key, List<Critter> >();
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected final String look(int direction, boolean steps) {
		this.energy = this.energy - Params.look_energy_cost;
		int x;
		int y;
		if(!steps) {
			switch(direction) {
			case 0:
				x = step('x', 1, this.x_coord);
				y = this.y_coord;
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 1:
				x = step('x', 1, this.x_coord);
				y = step('y', -1, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 2:
				x = this.x_coord;
				y = step('y', -1, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 3:
				x = step('x', -1, this.x_coord);
				y = step('y', -1, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 4:
				x = step('x', -1, this.x_coord);
				y = this.y_coord;
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 5:
				x = step('x', -1, this.x_coord);
				y = step('y', 1, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 6:
				x = this.x_coord;
				y = step('y', 1, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 7:
				x = step('x', 1, this.x_coord);
				y = step('y', 1, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			}
		}
		if(steps) {
			switch(direction) {
			case 0:
				x = step('x', 2, this.x_coord);
				y = this.y_coord;
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 1:
				x = step('x', 2, this.x_coord);
				y = step('y', -2, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 2:
				x = this.x_coord;
				y = step('y', -2, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 3:
				x = step('x', -2, this.x_coord);
				y = step('y', -2, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 4:
				x = step('x', -2, this.x_coord);
				y = this.y_coord;
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 5:
				x = step('x', -2, this.x_coord);
				y = step('y', 2, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 6:
				x = this.x_coord;
				y = step('y', 2, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			case 7:
				x = step('x', 2, this.x_coord);
				y = step('y', 2, this.y_coord);
				if(hasCritterthere(x, y)) {
					Key pos = new Key(x,y);
					Critter crit = positions.get(pos).get(0);
					return crit.toString();
				}
				else {
					return null;
				}
			}
		}
		return null;
		
		}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	private boolean moved;
	private boolean inFight;
	
	private final boolean hasCritterthere(int x, int y) {  	
		//checks if there is a critter in the position that the fighting critter wants to flee to
	Key pos = new Key(x,y);
	if(positions.containsKey(pos)) {		//checks if position critter is at position
		List<Critter> crit = positions.get(pos);
		
		for(Critter c: crit ) {					//checks if critter at that position is alive
			if(!(c.energy <= 0)) {
				return true;
			}
		}
	}
	
	return false;
	}
	
private final int step(Character type, int steps, int initVal) {
		
		if(type.equals('x')) { // updates x position
			
			initVal = initVal + steps; //adds the number of steps the critter must take to the inital position of the critter

			if(initVal == -1) { 					//wraps the critters around
				initVal = Params.world_width-1;
			}
			if(initVal == -2) {
				initVal = Params.world_width-2;
			}
			if(initVal == Params.world_width) {
				initVal = 0;
			}
			if(initVal == Params.world_width+1) {
				initVal = 1;
			}
			
		}
		if(type.equals('y')) {				//updates the critter's y position

			initVal = initVal + steps;  		//adds the number of steps to the critter's inital position

			if(initVal == -1) {
				initVal = Params.world_width-1;		//wraps critter world
			}
			if(initVal == -2) {
				initVal = Params.world_width-2;
			}
			if(initVal == Params.world_width) {
				initVal = 0;
			}
			if(initVal == Params.world_width+1) {
				initVal = 1;
			}
			
		}
		return initVal;
	}

	protected final void walk(int direction) {
		int xpos;
		int ypos;
		this.energy = this.energy - Params.walk_energy_cost;	//deducts walking energy cost
		if(!this.moved) {
			switch(direction) {							//updates position based on direction
				case 0:										
					
					xpos = step('x', 1, this.x_coord);		
					ypos = this.y_coord;
					if(this.inFight) {							//run method is critter is fighting
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
						}
					}else {
						this.x_coord = xpos;
					}
					break;
					
				case 1:
					xpos = step('x', 1, this.x_coord); 
					ypos = step('y', -1, this.y_coord);
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 2:
					xpos = this.x_coord;
					ypos = step('y', -1, this.y_coord);
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 3:
					xpos = step('x', -1, this.x_coord);
					ypos = step('y', -1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 4:
					xpos = step('x', -1, this.x_coord);
					ypos = this.y_coord;
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 5:
					xpos = step('x', -1, this.x_coord); 
					ypos = step('y', 1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 6:
					xpos = this.x_coord;
					ypos = step('y', 1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 7:
					xpos = step('x', 1, this.x_coord);
					ypos = step('y', 1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
					
			}
			this.moved = true;
		}
		
	}
	
	protected final void run(int direction) {
		int xpos;
		int ypos;
		this.energy = this.energy - Params.run_energy_cost;			//deducts run energy cost
		if(!this.moved) {										//checks if critter has already moved before
			switch(direction) {								//updates position based on direction
				case 0:											
					xpos = step('x', 2, this.x_coord);
					ypos = this.y_coord;
					if(this.inFight) {							//run method if critter is in fight
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 1:
					xpos = step('x', 2, this.x_coord); 
					ypos = step('y', -2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 2:
					xpos = this.x_coord;
					ypos = step('y', -2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 3:
					xpos = step('x', -2, this.x_coord); 
					ypos = step('y', -2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 4:
					xpos = step('x', -2, this.x_coord); 
					ypos = this.y_coord;
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 5:
					xpos = step('x', -2, this.x_coord); 
					ypos = step('y', 2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 6:
					xpos = this.x_coord;
					ypos = step('y', 2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 7:
					xpos = step('x', 2, this.x_coord); 
					ypos = step('y', 2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
			}
			this.moved = true;
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(!(this.energy > Params.min_reproduce_energy)) {			//makes sure that parent critter has enough energy to reproduce
			return;
		}
		offspring.energy = this.energy/2;					//gives offspring half of parents energy
		this.energy = (int)Math.ceil(this.energy/2);		//reduces parents energy
		switch(direction) {									//updates offsprings position based on parent's position and direction
		case 0:
			
			offspring.x_coord = step('x', 1, this.x_coord);
			offspring.y_coord = this.y_coord;
			break;
		case 1:
			offspring.y_coord = step('y', -1, this.y_coord);
			offspring.x_coord = step('x', 1, this.x_coord); 			
			break;
		case 2:
			offspring.y_coord = step('y', -1, this.y_coord);
			offspring.x_coord = this.x_coord;
			break;
		case 3:
			offspring.y_coord = step('y', -1, this.y_coord);
			offspring.x_coord = step('x', -1, this.x_coord); 
			break;
		case 4:
			offspring.x_coord = step('x', -1, this.x_coord);
			offspring.y_coord = this.y_coord;
			break;
		case 5:
			offspring.y_coord = step('y', 1, this.y_coord);
			offspring.x_coord = step('x', -1, this.x_coord); 
			break;
		case 6:
			offspring.y_coord = step('y', 1, this.y_coord);
			offspring.x_coord = this.x_coord;
			break;
		case 7:
			offspring.y_coord = step('y', 1, this.y_coord);
			offspring.x_coord = step('x', 1, this.x_coord);
			break;
	}
		
		babies.add(offspring);						//adds offsprings to baby population
		
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {
		if(population == null || population.isEmpty()) {	//returns if population is empty
			return;
		}
		for(Critter c : population) {
			c.moved = false;
			c.doTimeStep();
		}
		
		updatePositions();
		for(List<Critter> crit: positions.values()) {		//gets array list of all critters at different positions in hash map
			boolean aFight;
			boolean bFight;
			int aDice;
			int bDice;
			if(crit.size() > 1) {		//if arraylist is bigger than 1, then multiple critters at one position, and must fight
				int nextOp = 1;			//keeps track of next opponents (if there's more than 1)
				Critter a = crit.get(0);
				Critter b = crit.get(nextOp);
				while(nextOp < crit.size()){		//ends of no more opponents
					b = crit.get(nextOp);
					a.inFight = true;
					b.inFight = true;
					aFight = a.fight(b.toString());
					bFight = b.fight(a.toString());
					if((a.energy > 0) && (b.energy > 0) && (a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
						if(!aFight) {
							aDice = 0;
						}else {
							aDice = Critter.getRandomInt(a.energy);
						}
						if(!bFight) {
							bDice = 0;
						}else {
							bDice = Critter.getRandomInt(b.energy);
						}
						if(aDice >= bDice) { //remove loser?
							a.energy = a.energy + (b.energy/2);
							population.remove(b);
						}
						if(bDice > aDice) {
							b.energy = b.energy + (a.energy/2);
							population.remove(a);
							a = b;
						}
						
					}
					nextOp ++;
				}
			}
			
		}
		for(Critter c: population) {  				//deducts rest energy cost for all critters

			c.energy = c.energy - Params.rest_energy_cost;
		}
		
		
		for(int i=0;i<population.size();i++) { //removes dead critters

			if(population.get(i) != null)
			{
				if(population.get(i).energy <= 0) {
					population.remove(i);
				}
			}
		}
		for(int i=0;i<Params.refresh_algae_count;i++) {	//creates new algae
			try {
				Critter.makeCritter("Algae");
				
			}
			catch(InvalidCritterException e) {
				e.printStackTrace();
			}
			
		}
		population.addAll(babies); //adds all babies to population
		babies.clear();
	}
	
//	public static void displayWorld(Object pane) {
//		Stage primaryStage = new Stage();
//		pane = primaryStage;
//		GridPane gridPane = new GridPane();
//		gridPane.setMinSize(Params.world_width, Params.world_height);
//		gridPane.setPadding(new Insets(25,25,25,25));
//		//gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(new Scene(gridPane,300,250));
//		primaryStage.show();
//		//viewColor
//		
//	} 
	//Alternate displayWorld, where you use Main.<pane> to reach into your
	 //  display component.
	public static void displayWorld() {
		for(Critter c: population) {
			printCritter.print(c.viewShape(), c.viewOutlineColor(), c.viewFillColor(), c.x_coord,c.y_coord);
		}
		Application.launch(CritterWorld.class);
	}
	
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Class<?> fefe = null;

		
		try {
			fefe  = Class.forName(Critter.myPackage + "." + critter_class_name);
			Constructor<?> construct = fefe.getConstructor();
			Object newcrit = construct.newInstance();
			
			if(!(newcrit instanceof Critter)) {							//makes sure that new critter is a valid critter
				throw new InvalidCritterException(critter_class_name);
			}
			
			((Critter) newcrit).x_coord = getRandomInt(Params.world_width);
			((Critter) newcrit).y_coord = getRandomInt(Params.world_height);
			((Critter) newcrit).energy = Params.start_energy;
			((Critter) newcrit).moved = false;
			population.add((Critter) newcrit);						
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		return null;
	}
	
	public static String runStats(List<Critter> critters) {
		return null;
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	public static void updatePositions() {
		positions.clear();	
		
		for(Critter c: population) {
			Key po = new Key(c.x_coord,c.y_coord);		//creates key with critter's position
			if(!(positions.containsKey(po))) {					//adds key if it doesn't exist, and adds critter
				List <Critter> clist = new ArrayList<Critter>();	
				clist.add(c);
				positions.put(po, clist);
				
			}else {
				
				List <Critter> clist = positions.get(po);		//adds critter to array list if key exists already
				clist.add(c);
				positions.put(po, clist);
			}
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		updatePositions();
	}
	
	
}

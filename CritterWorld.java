package application;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import assignment5.Critter;
import assignment5.Params;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CritterWorld extends Application {

	public static GridPane world = new GridPane();
	static BorderPane bp = new BorderPane();
	static Timeline timeline  = new Timeline();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Critter");
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setGridLinesVisible(false);
		
		
		//gridPane.setMinSize(Params.world_width + 30 , Params.world_height+ 30);
//		Button button1 = new Button("Button 1");
//		Button button2 = new Button("Button 2");
//		grid.add(button2, 0, 0);
//		grid.add(button1, 0, 2);
		BackgroundImage myBI= new BackgroundImage(new Image("http://www.ece.utexas.edu/sites/default/files/portraits/VNK2016_smaller.jpg",1250,1000,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        
        CritterWorld.world.setBackground(new Background(myBI));
		world.setStyle("-fx-grid-lines-visible: true");
		world.setPadding(new Insets(10,10,10,10));
		//world.setGridLinesVisible(true);
		for (int i = 0; i < Params.world_width; i++) {
           	ColumnConstraints cc = new ColumnConstraints();
           	cc.setPercentWidth(100.0 / Params.world_width);
           	cc.setHalignment(HPos.CENTER);
          	world.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < Params.world_height; i++) {
           	RowConstraints rc = new RowConstraints();
           	rc.setPercentHeight(100.0 / Params.world_height);
           	rc.setValignment(VPos.CENTER);
        	world.getRowConstraints().add(rc);
        }
        
        //make critter button
    	
        TextField makeField = new TextField ();
        makeField.setPromptText("Enter Critter Name");
        TextField makenum = new TextField ();
        makenum.setPromptText("Enter # of Critters");
        
        grid.add(makeField, 0, 0);
        grid.add(makenum, 1, 0);
        Button make = new Button();
        make.setText("Make");
        grid.add(make, 2, 0);
        
        //step button
        Button step = new Button();
        step.setText("Step");
        grid.add(step, 1, 1);
        //Label steplabel = new Label("Number of Steps:");
        TextField stepField = new TextField ();
        stepField.setPromptText("Enter # of Steps");
        //grid.add(steplabel, 0, 2);
        grid.add(stepField, 0, 1);
        
        //seed button
        Button seed = new Button();
        seed.setText("Set Seed");
        grid.add(seed, 1, 2);
        TextField seedField = new TextField ();
        seedField.setPromptText("Enter Seed Number");
        grid.add(seedField, 0, 2);
        
        //run stats button
        Button stat = new Button();
        stat.setText("Run Stats");
        grid.add(stat, 1, 4);
        TextField statField = new TextField ();
        statField.setPromptText("Enter Critter Class");
        grid.add(statField, 0, 4);
        
        TextArea statbox = new TextArea();
        //VBox vbox = new VBox(statbox);
        //statbox.setPrefColumnCount(2);
        grid.add(statbox,0,5,2,1);
        
        //quit button
        Button end = new Button();
        end.setText("Quit");
        grid.add(end, 0, 8);
        
        Button animation = new Button();
        animation.setText("Animate");
        grid.add(animation, 0, 7);
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        grid.add(slider, 0, 6);
        Button stop = new Button();
        stop.setText("Stop");
        grid.add(stop, 1, 7);
        stop.setDisable(true);
        
        Button music = new Button();
        music.setText("Music");
        grid.add(music, 0, 8);

        make.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				try {
					if(makeField.getText().isEmpty()) {
						System.out.println("error processing: make " + makeField.getText()+ " "+ makenum.getText());
					}else if(makenum.getText().isEmpty()) {
						Critter.makeCritter(makeField.getText());
						Critter.updatePositions();
						Critter.updateDisplay();
					}else {
						int num = Integer.parseInt(makenum.getText());
						for(int i=0;i<num;i++) {
							Critter.makeCritter(makeField.getText());
						}
						Critter.updatePositions();
						Critter.updateDisplay();
					}
					
				} 
				catch (Exception e) {//exception right?
					System.out.println("error processing: " + makeField.getText()+ " " + makenum.getText()); 
				}
			}
        });
        
        step.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(stepField.getText().isEmpty()) {
					Critter.worldTimeStep();
					//System.out.println("stepped 1 time");
    			}else {
    				try {
    					int steps = Integer.parseInt(stepField.getText());
        				for (int i = 0; i < steps; i++) {
        					Critter.worldTimeStep();
        					//System.out.println("stepped multiple times");
        				}
    				}
    				catch(Exception e){
    					System.out.println("error processing: " + stepField.getText() + " seed");
    				}
    				
    			}
				Critter.updateDisplay();
			}
		});
        
        seed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					int num = Integer.parseInt(seedField.getText());
        			Critter.setSeed(num);
				}
				catch(Exception e) {
    					System.out.println("error processing: " + seedField.getText());
    				}
			}
		});
        
        
        stat.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		try {
        			List<Critter> result = null;
					result = Critter.getInstances(statField.getText());
					Class<?> crit = null;
					Class [] list = new Class[1];
					list[0] = java.util.List.class;
					crit = Class.forName(Critter.getPackage() + "." + statField.getText());
					java.lang.reflect.Method runStats = crit.getMethod("runStats", list);
				 	statbox.setText((String) runStats.invoke(crit, result));
					
					}
					
				catch (Exception e) {
					System.out.println("error processing: " + statField.getText());
				}
        	}
        });
        
        
        end.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		System.exit(0);
        	}
        });
        
        music.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		
        		try {
        	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/assignment5/song.wav").getAbsoluteFile());
        	        Clip clip = AudioSystem.getClip();
        	        clip.open(audioInputStream);
        	        clip.start();
        	    } catch(Exception ex) {
        	        System.out.println("Error with playing sound.");
        	        ex.printStackTrace();
        	    }
                
              	}
        });
        
        
        stop.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		makeField.setDisable(false);
        		makenum.setDisable(false);
        		make.setDisable(false);
        		step.setDisable(false);
        		stepField.setDisable(false);
        		seed.setDisable(false);
        		seedField.setDisable(false);
        		stat.setDisable(false);
        		statField.setDisable(false);
        		statbox.setDisable(false);
        		end.setDisable(false);
        		animation.setDisable(false);
        		stop.setDisable(true);
        		timeline.stop();
        		//System.out.println("Stopped");
        	}
        });
        
        animation.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		double speed = slider.getValue();
        		makeField.setDisable(true);
        		makenum.setDisable(true);
        		make.setDisable(true);
        		step.setDisable(true);
        		stepField.setDisable(true);
        		seed.setDisable(true);
        		seedField.setDisable(true);
        		stat.setDisable(true);
        		statField.setDisable(true);
        		statbox.setDisable(true);
        		end.setDisable(true);
        		animation.setDisable(true);
        		stop.setDisable(false);
        		
        		KeyFrame keyFrame  = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        	        	@Override
        	        	public void handle(ActionEvent event) {
        	        		for(int i =0;i<speed;i++) {
        	        			Critter.worldTimeStep();
        	        			
        	        		}
        	        		Critter.updateDisplay();
        	        	}
        	        });

        			timeline.getKeyFrames().add(keyFrame);
        			timeline.setCycleCount(Timeline.INDEFINITE); 
        			timeline.play();

        	}
        });
        
        
        
        
        //BorderPane.setAlignment(world, Pos.CENTER);
        //BorderPane.setAlignment(grid, Pos.TOP_RIGHT);
        BorderPane bp = new BorderPane(world, null, grid, null, null);
		//gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	    

//	    final Task task = new Task() {
//
//	        protected Object call() throws Exception {
//	            int s = 2147483647;
//	            AudioClip audio = new AudioClip(getClass().getResource("Schoolboy Q -  Hell Of A Night.wav").toExternalForm());
//	            audio.setVolume(0.5f);
//	            audio.setCycleCount(s);
//	            audio.play();
//	            return null;
//	        }
//	    };
//	    Thread thread = new Thread(task);
//	    thread.start();
	    primaryStage.setScene(new Scene(bp,300,250));
		primaryStage.show();
	
	}

}
package application;

import java.util.List;

import java.io.File;
import assignment5.Critter;
import assignment5.Params;
import javafx.animation.KeyFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.concurrent.Task;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

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
		grid.setPadding(new Insets(5,5,5,5));
		grid.setGridLinesVisible(false);
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/assignment5/song.wav").getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		
		world.setMinSize(Params.world_width , Params.world_height);

//		Image img = new Image("http://www.ece.utexas.edu/sites/default/files/portraits/VNK2016_smaller.jpg");
//		
//		img.fitWidthProperty().bind(world.widthProperty());
//		img.fitHeightProperty().bind(world.heightProperty());
//		bp.setCenter(img);
		//world.setStyle("-fx-background-image: url('http://www.ece.utexas.edu/sites/default/files/portraits/VNK2016_smaller.jpg')");
        //CritterWorld.world.setBackground(img);
		BackgroundImage myBI= new BackgroundImage(new Image("http://www.ece.utexas.edu/sites/default/files/portraits/VNK2016_smaller.jpg",1250,1000,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        
        world.setBackground(new Background(myBI));
		world.setStyle("-fx-grid-lines-visible: true");
		world.setPadding(new Insets(5,5,5,5));

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
        int columns = Params.world_width;
        int rows = Params.world_height;
        for (int i = 0; i < columns; i++) {
        for (int j = 0; j < rows; j++) {
              Pane pane = new Pane();
//              world.setOnMouseReleased(e -> {
//                  world.getChildren().add(Anims.getAtoms(1));
//              });
              pane.getStyleClass().add("game-grid-cell");
              if (i == 0) {
                  pane.getStyleClass().add("first-column");
              }
              if (j == 0) {
                  pane.getStyleClass().add("first-row");
              }
              CritterWorld.world.add(pane, i, j);
          }
        
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
        grid.add(step, 4, 0);
        //Label steplabel = new Label("Number of Steps:");
        TextField stepField = new TextField ();
        stepField.setPromptText("Enter # of Steps");
        //grid.add(steplabel, 0, 2);
        grid.add(stepField, 3, 0);
        
        //seed button
        Button seed = new Button();
        seed.setText("Set Seed");
        grid.add(seed, 6, 0);
        TextField seedField = new TextField ();
        seedField.setPromptText("Enter Seed Number");
        grid.add(seedField, 5, 0);
        
        //run stats button
        Button stat = new Button();
        stat.setText("Run Stats");
        grid.add(stat, 1, 1);
        TextField statField = new TextField ();
        statField.setPromptText("Enter Critter Class");
        grid.add(statField, 0, 1);
        
        TextField statbox = new TextField();
        //VBox vbox = new VBox(statbox);
        //statbox.setPrefColumnCount(2);
        statbox.setPromptText("Critter Stats will appear here");
        grid.add(statbox,2,1,6,1);
        
        //quit button
        Button end = new Button();
        end.setText("Quit");
        grid.add(end, 2, 2);
        
        //animation button
        Button animation = new Button();
        animation.setText("Animate");
        grid.add(animation, 8, 0);
        
        //slider for animation
        Slider slider = new Slider();
        slider.setMin(1);
        slider.setMax(100);
        slider.setValue(50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(49);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        grid.add(slider, 7, 0);
        Button stop = new Button();
        
        //stop animation button
        stop.setText("Stop Animation");
        grid.add(stop, 9, 0);
        stop.setDisable(true);
        
        //music button
        Button music = new Button();
        music.setText("Music");
        grid.add(music, 0, 2);
        
        //stop music button
        Button stopmusic = new Button();
        stopmusic.setText("Stop Music");
        grid.add(stopmusic, 1,2);
        stopmusic.setDisable(true);
        
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
					System.out.println("stepped 1 time");
    			}else {
    				try {
    					int steps = Integer.parseInt(stepField.getText());
        				for (int i = 0; i < steps; i++) {
        					Critter.worldTimeStep();
        					System.out.println("stepped multiple times");
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
        	        clip.start();
        	        music.setDisable(true);
        	        stopmusic.setDisable(false);
        	    } catch(Exception ex) {
        	        System.out.println("Error with playing sound.");
        	        ex.printStackTrace();
        	    }
                
              	}
        });
        
        stopmusic.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		
        		try {   
        	      clip.stop();
        	      music.setDisable(false);
        	      stopmusic.setDisable(true);
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
        		slider.setDisable(false);
        		music.setDisable(false);
        		stopmusic.setDisable(false);
        		timeline.stop();
        		System.out.println("Stopped");
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
        		slider.setDisable(true);
        		music.setDisable(true);
        		stopmusic.setDisable(true);
        		
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
        BorderPane bp = new BorderPane(world, null, null, grid, null);
		//gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //Scene scene = new Scene(world, (columns * 40) + 100, (rows * 40) + 100, Color.WHITE);
        //primaryStage.setScene(scene);
        Scene scene = new Scene(bp,300,250);
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.setFill(printCritter.createGridPattern());
		
		
	
	}

}

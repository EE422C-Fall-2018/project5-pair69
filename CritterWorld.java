package application;

import assignment5.Critter;
import assignment5.Params;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CritterWorld extends Application{

	static GridPane grid = new GridPane();
	static GridPane world = new GridPane();
	static BorderPane bp = new BorderPane();

	@Override
	public void start(Stage primaryStage) {
		try {
			grid.setHgap(5);
			grid.setVgap(5);
			grid.setGridLinesVisible(false);
			grid.setPadding(new Insets(10, 10, 10, 10));
			BorderPane root = new BorderPane();
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Label makelabel = new Label("Critter Name:");
	        TextField makeField = new TextField ();
	        grid.add(makelabel, 0, 0);
	        grid.add(makeField, 1, 0);
	        Button make = new Button();
	        make.setText("Make");
	        grid.add(make, 2, 0);
	        Button step = new Button();
	        step.setText("Step");
	        grid.add(step, 2, 2);
	        Label steplabel = new Label("Number of Steps:");
	        TextField stepField = new TextField ();
	        grid.add(steplabel, 0, 2);
	        grid.add(stepField, 1, 2);
	        Button seed = new Button();
	        seed.setText("Set Seed");
	        grid.add(seed, 2, 3);
	        Label seedlabel = new Label("Seed:");
	        TextField seedField = new TextField ();
	        grid.add(seedlabel, 0, 3);
	        grid.add(seedField, 1, 3);
	        Button stat = new Button();
	        stat.setText("Run Stats");
	        grid.add(stat, 1, 4);
	        TextArea statbox = new TextArea();
	        grid.add(statbox, 1, 5);
	        Button end = new Button();
	        end.setText("End");
	        grid.add(end, 1, 6);
	        make.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
    					int num = Integer.parseInt(makeField.getText());
    					for(int i=0;i<num;i++) {
    						Critter.makeCritter(makeField.getText());
    					}
						
					} catch (Exception e) {//exception right?
						System.out.println("error processing: " + makeField.getText()); 
					}
				}
	        });
	        
	        step.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(stepField.getText() == null) {
						Critter.worldTimeStep();
						System.out.println("stepped 1 time");
        			}else {
        				int steps = Integer.parseInt(stepField.getText());
        				for (int i = 0; i < steps; i++) {
        					Critter.worldTimeStep();
        					System.out.println("stepped multiple times");
        				}
        			}
				}
			});
	        
	        seed.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
    					int num = Integer.parseInt(seedField.getText());
            			Critter.setSeed(num);
    				}
    				catch(NumberFormatException e) {
        					System.out.println("error processing: " + seedField.getText());
        				}
				}
			});
	        
	        
	        stat.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		//statbox.appendText()
	        	}
	        });
	        
	        
	        end.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		System.exit(0);
	        	}
	        });
	        
	    	world.setGridLinesVisible(true);
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
	        
	        BorderPane bp = new BorderPane(world, null, grid, null, null);
	        Scene scene = new Scene(bp, 400, 400);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	       
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
		
}
	


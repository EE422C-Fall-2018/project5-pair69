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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class CritterWorld extends Application {

	static GridPane world = new GridPane();
	static BorderPane bp = new BorderPane();

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
		
		world.setPadding(new Insets(10,10,10,10));
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
        
        //make critter button
    	//Label makelabel = new Label("Critter Name:");
        //grid.add(makelabel, 0, 0);
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
        grid.add(stat, 0, 4);
        TextArea statbox = new TextArea();
        statbox.setPrefColumnCount(2);
        grid.add(statbox, 0, 5);
        
        //quit button
        Button end = new Button();
        end.setText("Quit");
        grid.add(end, 0, 6);
        
        make.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				try {
					if(makeField.getText().isEmpty()) {
						System.out.println("error processing: make " + makeField.getText()+ " "+ makenum.getText());
					}else if(makenum.getText().isEmpty()) {
						Critter.makeCritter(makeField.getText());
						Critter.updatePositions();
						Critter.displayWorld();
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
				if(stepField.getText() == "") {
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

        
        //BorderPane.setAlignment(world, Pos.CENTER);
        //BorderPane.setAlignment(grid, Pos.TOP_RIGHT);
        BorderPane bp = new BorderPane(world, null, grid, null, null);
		//gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(new Scene(bp,300,250));
		primaryStage.show();
		
		
		
	
	}

}

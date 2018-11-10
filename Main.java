package assignment5;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();
	static GridPane modelGrid = new GridPane();
	static TextArea stat = new TextArea();
	static VBox statsArea = new VBox(stat);
	static int steps =0;
	static boolean shown = false;
	
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

package application;

import java.util.List;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 560;
	private static final int WINDOW_HEIGHT = 220;
	private static final String APP_TITLE = "Milk Weights Program";

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Save args example
		args = this.getParameters().getRaw();

		//Creating TextField for file input
		TextField fileInput = new TextField();
		fileInput.setPromptText("Input a valid text file (.csv)");

		//Creating ComboBox for report type selection
		ComboBox<String> reportComboBox = new ComboBox<String>();
		reportComboBox.getItems().addAll(
				"Farm Report",
				"Annual Report",
				"Monthly Report",
				"Data Range Report");
		reportComboBox.setValue("Select a report");

		TextArea fileOutput = new TextArea();
		fileOutput.setEditable(false);
		fileOutput.setPromptText("Display for selected report type and statistics");

		//Creating VBox layout manager for buttons
		VBox vBox = new VBox();
		
		//Defining Buttons to operate on the file
		Button add = new Button("Add");
		Button remove = new Button("Remove");
		Button edit = new Button("Edit");
		Button display = new Button("Display");
		vBox.getChildren().addAll(add, remove, edit, display);

		//Adding to GridPane
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("File input: "), 0, 0);
		grid.add(fileInput, 1, 0);
		grid.add(new Label("Farm Report Type: "), 2, 0);
		grid.add(reportComboBox, 3, 0);
		grid.add(fileOutput, 0, 1, 4, 1);
		grid.add(vBox, 4, 1);

		//Setting up stage and scene
		Scene scene = new Scene(new Group(), WINDOW_WIDTH, WINDOW_HEIGHT);
		Group root = (Group)scene.getRoot();
		root.getChildren().add(grid);
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}   


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

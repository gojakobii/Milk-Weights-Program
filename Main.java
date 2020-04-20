package application;

import java.util.List;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
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
		fileInput.setPromptText("Input a valid text file .csv");

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
		add.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GridPane addGrid = new GridPane();
				addGrid.setVgap(4);
				addGrid.setHgap(10);
				addGrid.setPadding(new Insets(5, 5, 5, 5));
				addGrid.add(new Label("Add to: "), 0, 0);
				addGrid.add(new TextField(), 1, 0);
				addGrid.add(new Label("Enter year: "), 0, 1);
				addGrid.add(new TextField(), 1, 1);
				addGrid.add(new Label("Enter month: "), 2, 1);
				addGrid.add(new TextField(), 3, 1);
				addGrid.add(new Label("Enter day: "), 4, 1);
				addGrid.add(new TextField(), 5, 1);
				
				Scene secondScene = new Scene(addGrid, 695, 65);

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setScene(secondScene);
				newWindow.show();
			}
		});
		
		Button remove = new Button("Remove");
		remove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GridPane removeGrid = new GridPane();
				removeGrid.setVgap(4);
				removeGrid.setHgap(10);
				removeGrid.setPadding(new Insets(5, 5, 5, 5));
				removeGrid.add(new Label("Remove: "), 0, 0);
				removeGrid.add(new TextField(), 1, 0);
				removeGrid.add(new Label("Enter year: "), 0, 1);
				removeGrid.add(new TextField(), 1, 1);
				removeGrid.add(new Label("Enter month: "), 2, 1);
				removeGrid.add(new TextField(), 3, 1);
				removeGrid.add(new Label("Enter day: "), 4, 1);
				removeGrid.add(new TextField(), 5, 1);
				
				Scene secondScene = new Scene(removeGrid, 695, 65);

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setScene(secondScene);
				newWindow.show();
			}
		});
		
		Button edit = new Button("Edit");
		edit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GridPane editGrid = new GridPane();
				editGrid.setVgap(4);
				editGrid.setHgap(10);
				editGrid.setPadding(new Insets(5, 5, 5, 5));
				editGrid.add(new Label("Edit: "), 0, 0);
				editGrid.add(new TextField(), 1, 0);
				editGrid.add(new Label("Enter year: "), 0, 1);
				editGrid.add(new TextField(), 1, 1);
				editGrid.add(new Label("Enter month: "), 2, 1);
				editGrid.add(new TextField(), 3, 1);
				editGrid.add(new Label("Enter day: "), 4, 1);
				editGrid.add(new TextField(), 5, 1);
				
				Scene secondScene = new Scene(editGrid, 695, 65);

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setScene(secondScene);
				newWindow.show();
			}
		});
		
		Button display = new Button("Display");
		display.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				VBox displayButtons = new VBox();
				Stage newWindow = new Stage();
				
				Button statMonth = new Button("Display statistics by month");
				statMonth.setTextAlignment(TextAlignment.CENTER);
				statMonth.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						newWindow.hide();
						GridPane monthGrid = new GridPane();
						
						monthGrid.setVgap(4);
						monthGrid.setHgap(10);
						monthGrid.setPadding(new Insets(5, 5, 5, 5));
						monthGrid.add(new Label("Display statistics by month"), 0, 0, 2, 1);
						monthGrid.add(new Label("Enter farm: "), 0, 1);
						monthGrid.add(new TextField(), 0, 2);
						monthGrid.add(new Label("Enter year: "), 1, 1);
						monthGrid.add(new TextField(), 1, 2);
						
						Scene secondScene = new Scene(monthGrid, 300, 75);

						// New window (Stage)
						Stage newWindow = new Stage();
						newWindow.setScene(secondScene);
						newWindow.show();
					}
				});
				
				Button statFarm = new Button("Display statistics by farm");
				statFarm.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						newWindow.hide();
						GridPane farmGrid = new GridPane();
						
						farmGrid.setVgap(4);
						farmGrid.setHgap(10);
						farmGrid.setPadding(new Insets(5, 5, 5, 5));
						farmGrid.add(new Label("Display statistics by farm"), 0, 0, 2, 1);
						farmGrid.add(new Label("Enter month: "), 0, 1);
						farmGrid.add(new TextField(), 0, 2);
						farmGrid.add(new Label("Enter year: "), 1, 1);
						farmGrid.add(new TextField(), 1, 2);
						
						Scene secondScene = new Scene(farmGrid, 300, 75);

						// New window (Stage)
						Stage newWindow = new Stage();
						newWindow.setScene(secondScene);
						newWindow.show();
					}
				});
				statFarm.setTextAlignment(TextAlignment.CENTER);
				
				Button farmShares = new Button("Display farm shares");
				farmShares.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						newWindow.hide();
						GridPane shareGrid = new GridPane();
						
						shareGrid.setVgap(4);
						shareGrid.setHgap(10);
						shareGrid.setPadding(new Insets(5, 5, 5, 5));
						shareGrid.add(new Label("Display farm shares"), 0, 0, 2, 1);
						shareGrid.add(new Label("Enter month: "), 0, 1);
						shareGrid.add(new TextField(), 0, 2);
						shareGrid.add(new Label("Enter year: "), 1, 1);
						shareGrid.add(new TextField(), 1, 2);
						
						Scene secondScene = new Scene(shareGrid, 300, 75);

						// New window (Stage)
						Stage newWindow = new Stage();
						newWindow.setScene(secondScene);
						newWindow.show();
					}
				});
				farmShares.setTextAlignment(TextAlignment.CENTER);
				
				displayButtons.setPadding(new Insets(5, 5, 5, 5));
				displayButtons.getChildren().addAll(statMonth, statFarm, farmShares);
				displayButtons.setAlignment(Pos.CENTER);
				Scene secondScene = new Scene(displayButtons, 200, 85);

				// New window (Stage)
				
				newWindow.setScene(secondScene);
				newWindow.show();
			}
		});
		
		display.getOnMouseClicked();
		vBox.getChildren().addAll(add, remove, edit, display);

		//Adding to GridPane
		GridPane mainGrid = new GridPane();
		mainGrid.setVgap(4);
		mainGrid.setHgap(10);
		mainGrid.setPadding(new Insets(5, 5, 5, 5));
		mainGrid.add(new Label("File input: "), 0, 0);
		mainGrid.add(fileInput, 1, 0);
		mainGrid.add(new Label("Farm Report Type: "), 2, 0);
		mainGrid.add(reportComboBox, 3, 0);
		mainGrid.add(fileOutput, 0, 1, 4, 1);
		mainGrid.add(vBox, 4, 1);

		//Setting up stage and scene
		Scene scene = new Scene(new Group(), WINDOW_WIDTH, WINDOW_HEIGHT);
		Group root = (Group)scene.getRoot();
		root.getChildren().add(mainGrid);
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

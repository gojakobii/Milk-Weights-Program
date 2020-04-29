package application;

import java.util.ArrayList;
import java.util.List;

import application.Farm.Details;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 650;
	private static final int WINDOW_HEIGHT = 220;
	private static final String APP_TITLE = "Milk Weights Program";
	private Farm farm;
	private ArrayList<Details> displayFarms;

	public void addEditSetup(String change, TextArea fileOutput) {
		Stage newWindow = new Stage();
		GridPane gPane = new GridPane();

		gPane.add(new Label("Enter year: "), 0, 0);
		gPane.add(new TextField(), 1, 0);
		gPane.add(new Label("Enter month: "), 0, 1);
		gPane.add(new TextField(), 1, 1);
		gPane.add(new Label("Enter day: "), 0, 2);
		gPane.add(new TextField(), 1, 2);
		gPane.add(new Label("Enter farm: "), 0, 3);
		gPane.add(new TextField(), 1, 3);
		gPane.add(new Label("Enter weight: "), 0, 4);
		gPane.add(new TextField(), 1, 4);

		gPane.setVgap(4);
		gPane.setHgap(10);
		gPane.setPadding(new Insets(5, 5, 5, 5));

		Scene secondScene = new Scene(gPane, 330, 190);

		Button submit = new Button("Submit!");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// VERIFY DATA IS ENTERED CORRECTLY BEFORE PROCEEDING
				// (I.E FILE EXISTS, FILE PARSES CORRECTLY, VALID ENTRIES IN TEXT FIELDS)
				newWindow.hide();
				fileOutput.setText(change); //CHANGE WITH ADD/EDIT
			}
		});
		gPane.add(submit, 0, 5);

		// New window (Stage)
		newWindow.setTitle(change);
		newWindow.setScene(secondScene);
		newWindow.show();
	}

	public void removeSetup(String change, TextArea fileOutput) {
		Stage newWindow = new Stage();
		GridPane gPane = new GridPane();

		gPane.add(new Label("Enter year: "), 0, 0);
		gPane.add(new TextField(), 1, 0);
		gPane.add(new Label("Enter month: "), 0, 1);
		gPane.add(new TextField(), 1, 1);
		gPane.add(new Label("Enter day: "), 0, 2);
		gPane.add(new TextField(), 1, 2);
		gPane.add(new Label("Enter farm: "), 0, 3);
		gPane.add(new TextField(), 1, 3);

		gPane.setVgap(4);
		gPane.setHgap(10);
		gPane.setPadding(new Insets(5, 5, 5, 5));

		Scene secondScene = new Scene(gPane, 330, 125);

		Button submit = new Button("Submit!");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// VERIFY DATA IS ENTERED CORRECTLY BEFORE PROCEEDING
				// (I.E FILE EXISTS, FILE PARSES CORRECTLY, VALID ENTRIES IN TEXT FIELDS)
				newWindow.hide();
				fileOutput.setText(change); //CHANGE WITH REMOVE
			}
		});
		gPane.add(submit, 0, 5);

		// New window (Stage)
		newWindow.setTitle(change);
		newWindow.setScene(secondScene);
		newWindow.show();
	}

	public void parse(String filename, Button submit) throws Exception {
		System.out.println("load started.\n");

		try {
			FileManager fm = new FileManager();
			this.farm = fm.load(filename);
			System.out.println("load worked.\n");

		} catch (Exception e) { 
			throw new Exception();
		}
	}

	public void displayReport(String report, ArrayList<TextField> variables, TextArea fileOutput) throws Exception{
		try {
			ArrayList<String> keys = new ArrayList<String>();
			String output = "";

			for (TextField t : variables) {
				keys.add(t.getText()); //Gets the text from each TextField
			}

			//Based on the report, the ArrayList WILL have the correct Strings, and we use these
			if (report.equals("Farm Report")) {
				String farmid = keys.get(0);
				String year = keys.get(1);
				//				System.out.println(farmid + year);
				//				System.out.println("farm: " + farm.toString());

				displayFarms = farm.farmReport(farmid, year);				
			} else if (report.equals("Monthly Report")) {
				String month = keys.get(0);
				String year = keys.get(1);
				//				System.out.println(month + year);
				//				System.out.println("farm: " +farm.toString());

				displayFarms = farm.monthlyReport(month, year);
			} else if (report.equals("Annual Report")) {
				String year = keys.get(0);

				displayFarms = farm.annualReport(year);
			} else {
				String year = keys.get(0);
				String startMonth = keys.get(1);
				String day = keys.get(2);
				String endMonth = keys.get(3);
				String endDay = keys.get(4);

				displayFarms = farm.dateRange(year, startMonth, day, endMonth, endDay);
			}
			output += "  Farm ID    Month    Weight\n";

			for (Details d: displayFarms) {
				output += String.format("%d  %d  %d\n", d.getFarmID(), d.getMonth(), d.getMilkWeight());
			}

			fileOutput.setText(output);

		} catch (Exception e) {
			fileOutput.setText("Error: " + e.getMessage());
			//e.printStackTrace();
			throw new Exception();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Save args example
		args = this.getParameters().getRaw();

		//Creating TextField for file input
		TextField fileInput = new TextField();
		fileInput.setPromptText("Input a valid text file .csv");

		//Creating TextArea for reports and statistics
		TextArea fileOutput = new TextArea();
		fileOutput.setEditable(false);
		fileOutput.setPromptText("Display for selected report type and statistics");

		//Creating ComboBox for report type selection
		ComboBox<String> reportComboBox = new ComboBox<String>();
		reportComboBox.getItems().addAll(
				"Farm Report",
				"Annual Report",
				"Monthly Report",
				"Data Range Report");
		reportComboBox.setValue("Select a report");
		reportComboBox.setOnAction((e) -> {
			Stage newWindow = new Stage();
			GridPane gPane = new GridPane();
			String select = reportComboBox.getSelectionModel().getSelectedItem();
			ArrayList<TextField> variables = new ArrayList<TextField>();
			
			//set up for various reports
			if (select.equals("Farm Report")) {
				gPane.add(new Label("Enter farm: "), 0, 0);
				TextField tFarm = new TextField();
				gPane.add(tFarm, 1, 0);

				gPane.add(new Label("Enter year: "), 0, 1);
				TextField tYear = new TextField();
				gPane.add(tYear, 1, 1);

				variables.add(tFarm);
				variables.add(tYear);
			}
			else if (select.equals("Annual Report")) {
				gPane.add(new Label("Enter year: "), 0, 0);
				TextField tYear = new TextField();
				gPane.add(tYear, 1, 0);

				variables.add(tYear);
			}
			else if (select.equals("Monthly Report")) {
				gPane.add(new Label("Enter month: "), 0, 0);
				TextField tMonth = new TextField();
				gPane.add(tMonth, 1, 0);

				gPane.add(new Label("Enter year: "), 0, 1);
				TextField tYear = new TextField();
				gPane.add(tYear, 1, 1);

				variables.add(tMonth);
				variables.add(tYear);
			}
			else {
				gPane.add(new Label("Enter year: "), 0, 0);
				TextField tYear = new TextField();
				gPane.add(tYear, 1, 0);

				gPane.add(new Label("Enter start month: "), 0, 1);
				TextField tSMonth = new TextField();
				gPane.add(tSMonth, 1, 1);

				gPane.add(new Label("Enter start day: "), 0, 2);
				TextField tSDay = new TextField();
				gPane.add(tSDay, 1, 2);

				gPane.add(new Label("Enter end month: "), 0, 3);
				TextField tEMonth = new TextField();
				gPane.add(tEMonth, 1, 3);

				gPane.add(new Label("Enter end day: "), 0, 4);
				TextField tEDay = new TextField();
				gPane.add(tEDay, 1, 4);

				variables.add(tYear);
				variables.add(tSMonth);
				variables.add(tSDay);
				variables.add(tEMonth);
				variables.add(tEDay);
			}

			//Creating Button for submission of inserted text
			//When clicked, file parses, and and info displays
			Button submit = new Button("Submit!");
			submit.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// VERIFY DATA IS ENTERED CORRECTLY BEFORE PROCEEDING
					// (I.E FILE EXISTS, FILE PARSES CORRECTLY, VALID ENTRIES IN TEXT FIELDS)
					newWindow.hide();

					try {
						System.out.println("START PARSING....");
						parse(fileInput.getText(), submit);
						displayReport(select, variables, fileOutput);//display that report
					} catch (Exception e) {
						Alert warning = new Alert(AlertType.WARNING, "Something went wrong! "
								+ "Check that you've entered a valid file name and input valid information intended for "
								+ "computation."); 
						warning.show();
					}
				}
			});
			gPane.add(submit, 0, 5);
			gPane.setVgap(4);
			gPane.setHgap(10);
			gPane.setPadding(new Insets(5, 5, 5, 5));

			Scene secondScene = new Scene(gPane, 330, 200);
			newWindow.setTitle(select);
			newWindow.setScene(secondScene);
			newWindow.show();
		});

		//Creating VBox layout manager for buttons
		VBox vBox = new VBox();

		//Defining Buttons to operate on the file
		Button add = new Button("Add");
		add.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Alert confirm = new Alert(AlertType.CONFIRMATION, "This is for display purposes "
						+ "only and cannot yet accurately perform computations."); 
				confirm.show();
				//addEditSetup("Add", fileOutput);
			}
		});

		Button remove = new Button("Remove");
		remove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Alert confirm = new Alert(AlertType.CONFIRMATION, "This is for display purposes "
						+ "only and cannot yet accurately perform computations."); 
				confirm.show();
				//removeSetup("Remove", fileOutput);
			}
		});

		Button edit = new Button("Edit");
		edit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Alert confirm = new Alert(AlertType.CONFIRMATION, "This is for display purposes "
						+ "only and cannot yet accurately perform computations."); 
				confirm.show();
				//addEditSetup("Edit", fileOutput);
			}
		});

		Button display = new Button("Display");
		display.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Alert confirm = new Alert(AlertType.CONFIRMATION, "This is for display purposes "
						+ "only and cannot yet accurately perform computations."); 
				confirm.show();
				//				VBox displayButtons = new VBox();
				//				Stage newWindow = new Stage();
				//
				//				Button statMonth = new Button("Display statistics by month");
				//				statMonth.setTextAlignment(TextAlignment.CENTER);
				//				statMonth.setOnAction(new EventHandler<ActionEvent>() {
				//					public void handle(ActionEvent event) {
				//						newWindow.hide();
				//						Stage newWindow2 = new Stage();
				//
				//						GridPane monthGrid = new GridPane();
				//
				//						monthGrid.setVgap(4);
				//						monthGrid.setHgap(10);
				//						monthGrid.setPadding(new Insets(5, 5, 5, 5));
				//						monthGrid.add(new Label("Display statistics by month"), 0, 0, 2, 1);
				//						monthGrid.add(new Label("Enter farm: "), 0, 1);
				//						monthGrid.add(new TextField(), 0, 2);
				//						monthGrid.add(new Label("Enter year: "), 1, 1);
				//						monthGrid.add(new TextField(), 1, 2);
				//
				//						Scene secondScene = new Scene(monthGrid, 300, 100);
				//
				//						Button submit = new Button("Submit!");
				//						submit.setOnAction(new EventHandler<ActionEvent>() {
				//							public void handle(ActionEvent event) {
				//								// VERIFY DATA IS ENTERED CORRECTLY BEFORE PROCEEDING
				//								// (I.E FILE EXISTS, FILE PARSES CORRECTLY, VALID ENTRIES IN TEXT FIELDS)
				//								newWindow2.hide();
				//								fileOutput.setText("DISPLAY MONTH STATS"); //CHANGE WITH ACTUAL MONTHLY STATS CODE
				//							}
				//						});
				//						monthGrid.add(submit, 0, 5);
				//
				//						// New window (Stage)
				//						newWindow2.setTitle("Display");
				//						newWindow2.setScene(secondScene);
				//						newWindow2.show();
				//					}
				//				});
				//
				//				Button statFarm = new Button("Display statistics by farm");
				//				statFarm.setOnAction(new EventHandler<ActionEvent>() {
				//					public void handle(ActionEvent event) {
				//						newWindow.hide();
				//						Stage newWindow2 = new Stage();
				//						GridPane farmGrid = new GridPane();
				//
				//						farmGrid.setVgap(4);
				//						farmGrid.setHgap(10);
				//						farmGrid.setPadding(new Insets(5, 5, 5, 5));
				//						farmGrid.add(new Label("Display statistics by farm"), 0, 0, 2, 1);
				//						farmGrid.add(new Label("Enter month: "), 0, 1);
				//						farmGrid.add(new TextField(), 0, 2);
				//						farmGrid.add(new Label("Enter year: "), 1, 1);
				//						farmGrid.add(new TextField(), 1, 2);
				//
				//						Scene secondScene = new Scene(farmGrid, 300, 100);
				//
				//						Button submit = new Button("Submit!");
				//						submit.setOnAction(new EventHandler<ActionEvent>() {
				//							public void handle(ActionEvent event) {
				//								// VERIFY DATA IS ENTERED CORRECTLY BEFORE PROCEEDING
				//								// (I.E FILE EXISTS, FILE PARSES CORRECTLY, VALID ENTRIES IN TEXT FIELDS)
				//								newWindow2.hide();
				//								fileOutput.setText("DISPLAY FARM STATS"); //CHANGE WITH ACTUAL FARM STATS CODE
				//							}
				//						});
				//						farmGrid.add(submit, 0, 5);
				//
				//						// New window (Stage)
				//						newWindow2.setScene(secondScene);
				//						newWindow2.show();
				//					}
				//				});
				//				statFarm.setTextAlignment(TextAlignment.CENTER);
				//
				//				Button farmShares = new Button("Display farm shares");
				//				farmShares.setOnAction(new EventHandler<ActionEvent>() {
				//					public void handle(ActionEvent event) {
				//						newWindow.hide();
				//						Stage newWindow2 = new Stage();
				//						GridPane shareGrid = new GridPane();
				//
				//						shareGrid.setVgap(4);
				//						shareGrid.setHgap(10);
				//						shareGrid.setPadding(new Insets(5, 5, 5, 5));
				//						shareGrid.add(new Label("Display farm shares"), 0, 0, 2, 1);
				//						shareGrid.add(new Label("Enter month: "), 0, 1);
				//						shareGrid.add(new TextField(), 0, 2);
				//						shareGrid.add(new Label("Enter year: "), 1, 1);
				//						shareGrid.add(new TextField(), 1, 2);
				//
				//						Scene secondScene = new Scene(shareGrid, 300, 100);
				//
				//						Button submit = new Button("Submit!");
				//						submit.setOnAction(new EventHandler<ActionEvent>() {
				//							public void handle(ActionEvent event) {
				//								// VERIFY DATA IS ENTERED CORRECTLY BEFORE PROCEEDING
				//								// (I.E FILE EXISTS, FILE PARSES CORRECTLY, VALID ENTRIES IN TEXT FIELDS)
				//								newWindow2.hide();
				//								fileOutput.setText("DISPLAY FARM SHARES"); //CHANGE WITH ACTUAL FARM SHARES CODE
				//							}
				//						});
				//						shareGrid.add(submit, 0, 5);
				//
				//						// New window (Stage)
				//						newWindow2.setScene(secondScene);
				//						newWindow2.show();
				//					}
				//				});
				//				farmShares.setTextAlignment(TextAlignment.CENTER);
				//
				//				displayButtons.setPadding(new Insets(5, 5, 5, 5));
				//				displayButtons.getChildren().addAll(statMonth, statFarm, farmShares);
				//				displayButtons.setAlignment(Pos.CENTER);
				//				Scene secondScene = new Scene(displayButtons, 200, 85);
				//
				//				// New window (Stage)
				//				newWindow.setScene(secondScene);
				//				newWindow.show();
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

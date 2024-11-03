
package application;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class Driver extends Application {
	static DateScreen dateSc = new DateScreen();
	static Scene dateSce = new Scene(dateSc.getGrid(), 800, 800);
	static LinkedStack<DateOfMartyrs> stack = new LinkedStack<>();
	static LinkedStack<DateOfMartyrs> temp = new LinkedStack<>();
	static Node<DateOfMartyrs> currDate = null;
	static MartyrScreen martyrSc = new MartyrScreen();
	static Scene martyrScene = new Scene(martyrSc.getGrid(), 700, 700);
	static Scene insertScene = new Scene(martyrSc.getInsertGrid(), 500, 500);

	static Scene updateScene = new Scene(martyrSc.getUpdatedGrid(), 500, 500);
	static LinkedList<District> districts = new LinkedList<>();
	
	static QuadraticHashing<DateOfMartyrs> dates = new QuadraticHashing<>();

	@Override
	public void start(Stage primaryStage) {
		try {

			Button chooseFile = new Button("choos file and upload data"); // this button is to choose file
			Button loadDateSc = new Button("Load dates screen");
			Button save = new Button("save");
			GridPane grid = new GridPane();
			grid.add(chooseFile, 0, 0);
			grid.add(loadDateSc, 0, 1);
			grid.add(save, 0, 2);

			Scene scene = new Scene(grid, 700, 700);
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10);
			grid.setHgap(10);

			primaryStage.setScene(scene);
			primaryStage.setTitle("choose file");
			primaryStage.show();

			// read from file
			chooseFile.setOnAction(e -> { // this action to Let the user to choose the file
				FileChooser filechooser = new FileChooser(); // create a file chooser
				filechooser.setTitle("Choose file"); // title of file chooser
				filechooser.setInitialDirectory(new File("C:\\")); // the initial directory when the file chooser opened
				// the type of files appears on file chooser
				filechooser.getExtensionFilters().addAll(new ExtensionFilter("csv files", "*.csv"));
				File selectedFile = filechooser.showOpenDialog(primaryStage);
				if (selectedFile != null) { // if the selected file not null
					try {
						Scanner sc = new Scanner(selectedFile); // read the information of martyrs from the file

						while (sc.hasNext()) {

							String[] line = sc.nextLine().split(",");
							if (line.length == 6) { // if the lines contains all information read to store
								if (Character.toUpperCase(line[5].charAt(0)) == 'F'
										|| Character.toUpperCase(line[5].charAt(0)) == 'M') { // if the gender F or M
																								// store the martyr
									String[] date = line[1].split("/");
									if (date.length == 3) { // check the date
										try {
											int age = Integer.parseInt(line[2]);
											if (age >= 0 && age < 150) {
												// create a date from the information
												Date dateOfDeath = new Date(Integer.parseInt(date[2]) - 1900,
														Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]));
												Martyr martyr = new Martyr(line[0], age, line[3], line[4],
														line[5].charAt(0));
												DateOfMartyrs dateOfMartyrs = new DateOfMartyrs(dateOfDeath);
												if (dates.find(dateOfMartyrs) == null) {
													dates.insert(dateOfMartyrs);
												}
												Node<District> districtNode = districts
														.find(new District(martyr.getDistrict()));
												if (districtNode == null) {
													districts.insert(new District(martyr.getDistrict()));
												}
												districtNode = districts.find(new District(martyr.getDistrict()));
												Location location = new Location(martyr.getLocation());
												if (districtNode.getData().getLocatons().find(location) == null) {
													districtNode.getData().getLocatons().insert(location);
												}
												dateOfMartyrs = dates.find(dateOfMartyrs);
												dateOfMartyrs.getMartyrs().insert(martyr);

											}
										} catch (Exception ex) {

										}
									}
								}
							}
						}

					} catch (FileNotFoundException e1) {

					}
				}
				dates.print();
				System.out.println(dates.getArr().length);
				System.out.println(dates.getData());
				loadDatesToCombo();
				loadDatesToStack();
				Node<District> currdis = districts.getHead();
				while (currdis != null) {
					System.out.print(currdis.getData().getDistrict() + "  " + currdis.getData().getLocatons());
					System.out.println();
					currdis = currdis.getNext();
				}

			});
			loadDateSc.setOnAction(e -> {
				
				primaryStage.setScene(dateSce);
				primaryStage.setTitle("Date Screen");

			});
			dateSc.getInsert().setOnAction(e -> {

				dateSc.insertAction();
				dateSc.getCombo().getItems().clear();
				loadDatesToCombo();
				reLoadToStack();
			});
			dateSc.getDelete().setOnAction(e -> {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Are you sure you want to delete this date?");
				alert.setContentText("You will lose all  data");
				alert.showAndWait();
				if (alert.getResult().getText().equalsIgnoreCase("OK")) {
					DateOfMartyrs deleted = dateSc.deleteAction();
					dateSc.getCombo().getItems().clear();
					if (currDate != null) {
						if (currDate.getData().equals(deleted))
							currDate = temp.pop();
					}
					loadDatesToCombo();

					reLoadToStack();
				}
			});
			
			
			
			dateSc.getUpdate().setOnAction(e -> {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Are you sure you want to update this ditrict?");
				alert.setContentText("You cannot undo this action");
				alert.showAndWait();
				if (alert.getResult().getText().equalsIgnoreCase("OK")) {
					dateSc.updateAction();
					dateSc.getCombo().getItems().clear();
					loadDatesToCombo();
					//
					reLoadToStack();
					dateSc.getCombo().setPromptText("Dates");
				}
			});
			dateSc.getPrint().setOnAction(e -> {
				dateSc.print();
			});
			dateSc.getNext().setOnAction(e -> {
				if (currDate != null)
					temp.push(currDate.getData());
				if (!stack.isEmpty()) {

					currDate = stack.pop();
					dateSc.displayInfo(currDate.getData());				
				} else {
					currDate = null;
					dateSc.getInfo().setText("no more");
				}
			});
			dateSc.getPrev().setOnAction(e -> {
				if (!temp.isEmpty()) {
					if (currDate != null)
						stack.push(currDate.getData());
					currDate = temp.pop();
					dateSc.displayInfo(currDate.getData());
				}

			});
			dateSc.getLoad().setOnAction(e -> {
				if (currDate != null) {
					primaryStage.setScene(martyrScene);
					primaryStage.setTitle("Martyr Screen");
					loadTreeToCombo(currDate.getData().getMartyrs());
				} else {

					Label label = new Label("There is no date");
					StackPane stack = new StackPane();
					stack.getChildren().add(label);
					Scene sc = new Scene(stack, 500, 100);
					Stage stage = new Stage();
					stage.setScene(sc);
					stage.show();
				}

			});
			dateSc.getBack().setOnAction(e -> {
				primaryStage.setScene(scene);
				primaryStage.setTitle("choose file");
				stack.clear();
				temp.clear();
				currDate = null;
				loadDatesToStack();
				dateSc.clear();
				dateSc.getCombo().getItems().clear();
			});
			martyrSc.getUpdatedMartyrDistrictCombo().setOnAction(e1 -> {
				martyrSc.getUpdatedMartyrLocationCombo().getItems().clear();
				District district = martyrSc.getUpdatedMartyrDistrictCombo().getValue();
				if(district != null) {
				Node<Location> currLoc = district.getLocatons().getHead();
				while (currLoc != null) {
					martyrSc.getUpdatedMartyrLocationCombo().getItems().add(currLoc.getData());
					currLoc = currLoc.getNext();
				}
				}
			});
			martyrSc.getMartyrDistrictCombo().setOnAction(e1 -> {
				martyrSc.getMartyrLocationCombo().getItems().clear();
				District district = martyrSc.getMartyrDistrictCombo().getValue();
				if(district != null) {
				Node<Location> currLoc = district.getLocatons().getHead();
				while (currLoc != null) {
					martyrSc.getMartyrLocationCombo().getItems().add(currLoc.getData());
					currLoc = currLoc.getNext();
				}
				}
			});
			martyrSc.getInsert().setOnAction(e -> {
				Node<District> currDis = districts.getHead();
				while (currDis != null) {
					martyrSc.getMartyrDistrictCombo().getItems().add(currDis.getData());
					currDis = currDis.getNext();
				}

			
				if (currDate != null) {
					primaryStage.setScene(insertScene);
					primaryStage.setTitle("insert martyr");
				}

			});
			martyrSc.getInsertMartyr().setOnAction(e1 -> {
				martyrSc.insertAction(currDate.getData());
				martyrSc.getComboMartyrs().getItems().clear();
				loadTreeToCombo(currDate.getData().getMartyrs());
			});
			martyrSc.getInsertBack().setOnAction(e -> {
				primaryStage.setScene(martyrScene);
				primaryStage.setTitle("");
				martyrSc.getInsertResult().setText("");
				martyrSc.getMartyrNameTxt().setText("");
				martyrSc.getMartyrAgeTxt().setText("");
				martyrSc.getMartyrDistrictCombo().getItems().clear();
				martyrSc.getMartyrLocationCombo().getItems().clear();
			});
			martyrSc.getDelete().setOnAction(e -> {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Are you sure you want to delete this martyr?");
				alert.setContentText("You will lose all  data");
				alert.showAndWait();
				if (alert.getResult().getText().equalsIgnoreCase("OK")) {
					if (currDate != null)
						if (martyrSc.deleteAction(currDate.getData())) {
							martyrSc.getComboMartyrs().getItems().clear();
							loadTreeToCombo(currDate.getData().getMartyrs());
						}
				}

			});
			martyrSc.getUpdate().setOnAction(e -> {
				Node<District> currDis = districts.getHead();
				while (currDis != null) {
					martyrSc.getUpdatedMartyrDistrictCombo().getItems().add(currDis.getData());
					currDis = currDis.getNext();
				}

				if (martyrSc.getComboMartyrs().getValue() != null) {

					primaryStage.setScene(updateScene);
					primaryStage.setTitle("update martyr");

				} else {
					martyrSc.getUpdatedResult().setText("");
					Label label = new Label("You don`t choose a martyr");
					StackPane stack = new StackPane();
					stack.getChildren().add(label);
					Scene sc = new Scene(stack, 500, 100);
					Stage stage2 = new Stage();
					stage2.setScene(sc);
					stage2.show();
				}

			});
			martyrSc.getUpdatedMartyr().setOnAction(e -> {
				martyrSc.updateAction(currDate.getData(), martyrSc.getComboMartyrs().getValue());
				martyrSc.getComboMartyrs().getItems().clear();
				loadTreeToCombo(currDate.getData().getMartyrs());
			});
			martyrSc.getUpdateBack().setOnAction(e -> {
				primaryStage.setScene(martyrScene);
				primaryStage.setTitle("Martyr scene");
				martyrSc.getUpdatedResult().setText("");
				martyrSc.getUpdatedMartyrNameTxt().setText("");
				martyrSc.getUpdatedMartyrAgeTxt().setText("");
				martyrSc.getUpdatedMartyrDistrictCombo().getItems().clear();
				martyrSc.getUpdatedMartyrLocationCombo().getItems().clear();
			});
			martyrSc.getSizeAndHeightBtn().setOnAction(e -> {
				if (currDate != null)
					martyrSc.displaySizeAndHeightAction(currDate.getData());
			});
			martyrSc.getPrintTree().setOnAction(e -> {
				if (currDate != null) {
					martyrSc.printTree(currDate.getData());
				}
			});
			martyrSc.getPrintMartyrs().setOnAction(e -> {
				if (currDate != null) {
					martyrSc.printMartyrs(currDate.getData());
				}
			});
			martyrSc.getBack().setOnAction(e -> {
				primaryStage.setScene(dateSce);
				primaryStage.setTitle("Date Screen");
				martyrSc.clear();
				martyrSc.getComboMartyrs().getItems().clear();
			});
			save.setOnAction(e -> {
				saveAction();
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void loadDatesToCombo() {
		for (int i = 0; i < dates.getArr().length; i++) {
			if (dates.getArr()[i].getFlag() == 'F') {
				dateSc.getCombo().getItems().add(dates.getArr()[i].getData());
			}
		}
	}

	public static void loadDatesToStack() {
		for (int i = dates.getArr().length - 1; i >= 0; i--) {
			if (dates.getArr()[i].getFlag() == 'F') {
				stack.push(dates.getArr()[i].getData()); // dates.getArr()[i].getData();
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static String printDate(Date date) {
		if (date != null)
			return (date.getMonth() + 1) + "/" + (date.getDate()) + "/" + (date.getYear() + 1900);
		else
			return "null";
	}

	public static void reLoadToStack() {

		stack.clear();
		temp.clear();
		loadDatesToStack();
		// stack.print();

		if (currDate == null) {
			while (!stack.isEmpty()) {
				temp.push(stack.pop().getData());
			}
		} else {
			while (!stack.isEmpty() && !stack.peek().getData().equals(currDate.getData())) {
				temp.push(stack.pop().getData());
			}
		}
		currDate = stack.pop();

	}

	public static void loadTreeToCombo(AVLTree<Martyr> tree) {

		loadTreeToCombo(tree.getRoot());

	}

	private static void loadTreeToCombo(TNode<Martyr> node) {
		if (node != null) {
			if (node.getLeft() != null)
				loadTreeToCombo(node.getLeft());
			martyrSc.getComboMartyrs().getItems().add(node.getData());
			if (node.getRight() != null)
				loadTreeToCombo(node.getRight());
		}

	}

	public static void saveAction() {
		try {
			PrintWriter pw = new PrintWriter("savedFile.csv");
			pw.print("Name");
			pw.print(",");
			pw.print("Event");
			pw.print(",");
			pw.print("Age");
			pw.print(",");
			pw.print("Location");
			pw.print(",");
			pw.print("District");
			pw.print(",");
			pw.print("Gender");
			pw.print("\n");
			for (int i = 0; i < dates.getArr().length; i++) {
				if (dates.getArr()[i].getFlag() == 'F') {
					printTreeToFile(dates.getArr()[i].getData().getMartyrs(), dates.getArr()[i].getData(), pw);
				}
			}
			pw.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void printTreeToFile(AVLTree<Martyr> tree, DateOfMartyrs dateOfMartyrs, PrintWriter pw) {
		printTreeToFile(tree.getRoot(), dateOfMartyrs, pw);
	}

	private static void printTreeToFile(TNode<Martyr> node, DateOfMartyrs dateOfMartyrs, PrintWriter pw) {
		if (node != null) {
			if (node.getLeft() != null)
				printTreeToFile(node.getLeft(), dateOfMartyrs, pw);
			// System.out.print(node.toString() + " ");
			pw.print(node.getData().getName());
			pw.print(",");
			pw.print(printDate(dateOfMartyrs.getDate()));
			pw.print(",");
			pw.print(node.getData().getAge());
			pw.print(",");
			pw.print(node.getData().getLocation());
			pw.print(",");
			pw.print(node.getData().getDistrict());
			pw.print(",");
			pw.print(node.getData().getGender());
			pw.print("\n");
			if (node.getRight() != null)
				printTreeToFile(node.getRight(), dateOfMartyrs, pw);
		}

	}
}

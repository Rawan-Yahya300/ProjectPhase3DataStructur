package application;

import java.time.LocalDate;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DateScreen {

	private GridPane grid;

	private Button insert;
	private DatePicker datePicker;
	private Label insertResult;

	private Button delete;
	private Label deleteLbl;
	private ComboBox<DateOfMartyrs> combo;
	private Label deleteResult;

	private Button update;
	private Label updateLbl;
	private DatePicker updateDatePicker;
	private Label updateResult;
	

	private Button print;
	private ComboBox<String> filter;

	private Button next;
	private Button prev;
	private Label info;

	private Button back;
	private Button load;

	public DateScreen() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(15);
		grid.setVgap(20);
		insert = new Button("insert date");
		datePicker = new DatePicker();
		datePicker.setEditable(false);
		insertResult = new Label();

		delete = new Button("delete date");
		deleteLbl = new Label("choose the date you want to delete\nfrom the follwing combo");
		combo = new ComboBox<>();
		combo.setPromptText("Choose date");
		deleteResult = new Label();

		update = new Button("update date");
		updateLbl = new Label("choose the date you want\n to update it from the above combo\nand enter the new date ");
		updateDatePicker = new DatePicker();
		updateDatePicker.setEditable(false);
		updateResult = new Label();
		

		print = new Button("print table");
		filter = new ComboBox<>();
		filter.getItems().add("including the empty spots");
		filter.getItems().add("excluding the empty spots");
		filter.setPromptText("including/excluding");

		next = new Button("down");
		prev = new Button("up");
		info = new Label();

		back = new Button("Back");
		load = new Button("Load Martyrs");

		grid.add(insert, 0, 0);
		grid.add(datePicker, 1, 0);
		grid.add(insertResult, 2, 0);

		grid.add(deleteLbl, 0, 1);
		grid.add(delete, 1, 1);
		grid.add(combo, 1, 2);
		grid.add(deleteResult, 2, 1);

		grid.add(update, 0, 3);
		grid.add(updateLbl, 1, 3);
		grid.add(updateDatePicker, 2, 3);
		grid.add(updateResult, 3, 3);

		grid.add(print, 0, 6);
		grid.add(filter, 1, 6);

		grid.add(next, 2, 4);
		grid.add(prev, 0, 4);
		grid.add(info, 1, 5);

		grid.add(back, 0, 15);
		grid.add(load, 4, 15);
	}

	public void insertAction() {
		LocalDate localDate = datePicker.getValue();
		Date currDate = new Date();
		if (localDate != null) {
			Date date = new Date(localDate.getYear() - 1900, localDate.getMonth().getValue() - 1,
					localDate.getDayOfMonth());
			if(currDate.compareTo(date) > 0) {
			DateOfMartyrs dateOfMartyrs = new DateOfMartyrs(date);
			if (Driver.dates.find(dateOfMartyrs) == null) {
				Driver.dates.insert(dateOfMartyrs);
				insertResult.setText("process done");
			} else {
				insertResult.setText("");
				Label label = new Label("The date already exist");
				StackPane stack = new StackPane();
				stack.getChildren().add(label);
				Scene scene = new Scene(stack, 500, 100);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			}
			}else {
				insertResult.setText("");
				Label label = new Label("The date is yet to come");
				StackPane stack = new StackPane();
				stack.getChildren().add(label);
				Scene scene = new Scene(stack, 500, 100);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			}
		} else {
			insertResult.setText("");
			Label label = new Label("You don`t insert the date");
			StackPane stack = new StackPane();
			stack.getChildren().add(label);
			Scene scene = new Scene(stack, 500, 100);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}
	}

	public DateOfMartyrs deleteAction() {
		if (combo.getValue() != null) {
			DateOfMartyrs dateOfMartyr = combo.getValue();
			DateOfMartyrs deleted = Driver.dates.delete(dateOfMartyr);
			deleteResult.setText("process done");
			return deleted;
		} else {
			deleteResult.setText("");
			Label label = new Label("You don`t choose a date");
			StackPane stack = new StackPane();
			stack.getChildren().add(label);
			Scene scene = new Scene(stack, 500, 100);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			return null;
		}
	}

	public void updateAction() {
		if (combo.getValue() != null) {
			DateOfMartyrs dateofMartyr = combo.getValue();
			LocalDate localDate = updateDatePicker.getValue();
			if (localDate != null) {

				Date date = new Date(localDate.getYear() - 1900, localDate.getMonth().getValue() - 1,
						localDate.getDayOfMonth());
				DateOfMartyrs searched = Driver.dates.find(new DateOfMartyrs(date));
				if (searched == null) {
					System.out.println(dateofMartyr);
					Driver.dates.delete(dateofMartyr);
					dateofMartyr.setDate(date);
					Driver.dates.insert(dateofMartyr);
					System.out.println(dateofMartyr);
					updateResult.setText("process done");
				} else {
					System.out.println(searched.toString());
					AVLTree<Martyr> old = searched.getMartyrs();
					AVLTree<Martyr> newAvl = dateofMartyr.getMartyrs();
					insertToTree(old, newAvl);
					Driver.dates.delete(dateofMartyr);
					System.out.println(searched.toString());
					updateResult.setText("process done");
				}

			} else {
				updateResult.setText("");
				Label label = new Label("You don`t enter the value of new date");
				StackPane stack = new StackPane();
				stack.getChildren().add(label);
				Scene scene = new Scene(stack, 500, 100);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			}
		} else {
			updateResult.setText("");
			Label label = new Label("You don`t choose a date to update it");
			StackPane stack = new StackPane();
			stack.getChildren().add(label);
			Scene scene = new Scene(stack, 500, 100);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}
	}

	private void insertToTree(TNode<Martyr> node, AVLTree<Martyr> old) {
		if (node != null) {
			if (node.getLeft() != null)
				insertToTree(node.getLeft(), old);
			// System.out.print(node.toString() + " ");
			old.insert(node.getData());
			if (node.getRight() != null)
				insertToTree(node.getRight(), old);
		}

	}

	public void print() {
		TextArea text = new TextArea();
		StackPane stack = new StackPane();
		Scene scene = new Scene(stack, 500, 500);
		Stage stage = new Stage();
		stage.setScene(scene);
		if (filter.getValue()!= null && filter.getValue().equals("including the empty spots")) {
			for (int i = 0; i < Driver.dates.getArr().length; i++) {
				//if (Driver.dates.getArr()[i].getFlag() == 'F' || Driver.dates.getArr()[i].getFlag() == 'E')
					text.setText(text.getText() + "\n "+i+":  " + Driver.dates.getArr()[i].getData() + "     "
							+ Driver.dates.getArr()[i].getFlag());

			}
			stack.getChildren().add(text);
			stage.show();
			
		} else if (filter.getValue()!= null &&filter.getValue().equals("excluding the empty spots")) {
			for (int i = 0; i < Driver.dates.getArr().length; i++) {
				if (Driver.dates.getArr()[i].getFlag() == 'F')
					text.setText(text.getText() + "\n " +i+":  "+ Driver.dates.getArr()[i].getData() + "     "
							+ Driver.dates.getArr()[i].getFlag());

			}
			stack.getChildren().add(text);
			stage.show();
		}else {
			
				
				Label label = new Label("select from the comboBox including/excluding");
				StackPane stack2 = new StackPane();
				stack2.getChildren().add(label);
				Scene sc = new Scene(stack2, 500, 100);
				Stage stage2 = new Stage();
				stage2.setScene(sc);
				stage2.show();
			
		}
	}
      public void displayInfo(DateOfMartyrs dateOfMartyrs) {
    	    if(dateOfMartyrs != null) {
    	    	info.setText("Date: "+Driver.printDate(dateOfMartyrs.getDate())+"\nTotal martyrs: "+dateOfMartyrs.numOfMartyr()+"\nMale martyrs: "+dateOfMartyrs.numOfMaleMartyrs()+""
    	    			+ "\nFemale martyrs: "+dateOfMartyrs.numOfFemaleMartyrs()+"\nAverage age: "+dateOfMartyrs.avgAge()+"\nMax district: "+dateOfMartyrs.districtHasMaxNumOfMartyrs()
    	    			+"\nMax location: "+dateOfMartyrs.locationHasMaxNumOfMartyrs());
    	    }
      }
      
      public void clear() {
    		insertResult.setText("");
    		deleteResult.setText("");;
    		updateResult.setText("");;
    		info.setText("");;
      }
	public void insertToTree(AVLTree<Martyr> old, AVLTree<Martyr> newAvl) {
		insertToTree(newAvl.getRoot(), old);

	}

	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	public Button getInsert() {
		return insert;
	}

	public void setInsert(Button insert) {
		this.insert = insert;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	public Label getInsertResult() {
		return insertResult;
	}

	public void setInsertResult(Label insertResult) {
		this.insertResult = insertResult;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	public ComboBox<DateOfMartyrs> getCombo() {
		return combo;
	}

	public void setCombo(ComboBox<DateOfMartyrs> combo) {
		this.combo = combo;
	}

	public Label getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(Label deleteResult) {
		this.deleteResult = deleteResult;
	}

	public Button getUpdate() {
		return update;
	}

	public void setUpdate(Button update) {
		this.update = update;
	}

	public Label getUpdateLbl() {
		return updateLbl;
	}

	public void setUpdateLbl(Label updateLbl) {
		this.updateLbl = updateLbl;
	}

	public DatePicker getUpdateDatePicker() {
		return updateDatePicker;
	}

	public void setUpdateDatePicker(DatePicker updateDatePicker) {
		this.updateDatePicker = updateDatePicker;
	}

	public Label getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(Label updateResult) {
		this.updateResult = updateResult;
	}

	public Button getPrint() {
		return print;
	}

	public void setPrint(Button print) {
		this.print = print;
	}

	public ComboBox<String> getFilter() {
		return filter;
	}

	public void setFilter(ComboBox<String> filter) {
		this.filter = filter;
	}

	public Button getNext() {
		return next;
	}

	public void setNext(Button next) {
		this.next = next;
	}

	public Button getPrev() {
		return prev;
	}

	public void setPrev(Button prev) {
		this.prev = prev;
	}

	public Label getInfo() {
		return info;
	}

	public void setInfo(Label info) {
		this.info = info;
	}

	public Button getBack() {
		return back;
	}

	public void setBack(Button back) {
		this.back = back;
	}

	public Button getLoad() {
		return load;
	}

	public void setLoad(Button load) {
		this.load = load;
	}
     
	
}

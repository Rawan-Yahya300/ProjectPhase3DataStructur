package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MartyrScreen {

	private GridPane grid;
	
	private Button insert;
	private Button update;
	private Label updateResult;
	private Button delete;
	private ComboBox<Martyr> comboMartyrs;
	private Label deleteResult;
	private Button sizeAndHeightBtn;
	private Label sizeAndHeightLbl;
	private Button printTree;
	private Label printTreeResult;
	private Button printMartyrs;
	private Label printMartyrsResult;
	private Button back;
	
	private GridPane insertGrid;
	private Button insertMartyr;
	private Label insertResult;
	private Label martyrNameLbl;
	private TextField martyrNameTxt;
	private Label martyrAgeLbl;
	private TextField martyrAgeTxt;
	private Label martyrDistrictLbl;
	private ComboBox<District> martyrDistrictCombo;
	private Label martyrLocationLbl;
	private ComboBox<Location> martyrLocationCombo;
	private RadioButton male;
	private RadioButton female;
	 private ToggleGroup toggleGroup;
	 private Button insertBack;
	
	private StackPane stackPane;
	private TextArea tree;
	 Scene sceneTree;  
	
	private GridPane updatedGrid;
	private Button updatedMartyr;
	private Label updatedResult;
	private Label updatedMartyrNameLbl;
	private TextField updatedMartyrNameTxt;
	private Label updatedMartyrAgeLbl;
	private TextField updatedMartyrAgeTxt;
	private Label updatedMartyrDistrictLbl;
	private ComboBox<District> updatedMartyrDistrictCombo;
	private Label updatedMartyrLocationLbl;
	private ComboBox<Location> updatedMartyrLocationCombo;
	private RadioButton updatedMale;
	private RadioButton updatedFemale;
	private ToggleGroup updatedtoggleGroup;
	private Button updateBack;
	
	public MartyrScreen() {
		
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		
		insert = new Button("Insert");
		update = new Button("Update");
		delete = new Button("Delete");
		comboMartyrs = new ComboBox<>();
		comboMartyrs.setPromptText("martyrs");
		deleteResult = new Label();
		sizeAndHeightBtn = new Button("Display size and height");
		sizeAndHeightLbl = new Label();
		printTree = new Button("Print Tree");
		printMartyrs = new Button("Print Martyrs");
		back = new Button("back");
		updateResult = new Label();
		printTreeResult = new Label();
		printMartyrsResult = new Label();
		 insertBack = new Button("back");
		

		
		updatedGrid = new GridPane();
		updatedGrid.setAlignment(Pos.CENTER);
		updatedGrid.setHgap(20);
		updatedGrid.setVgap(20);
		updatedMartyr = new Button("update");
		updatedResult = new Label();
		updatedMartyrNameLbl = new Label("Enter new Name");
		updatedMartyrNameTxt = new TextField();
		updatedMartyrAgeLbl = new Label("Enter new Age");
		updatedMartyrAgeTxt = new TextField();
		updatedMartyrDistrictLbl = new Label("Choose new district");
		updatedMartyrDistrictCombo = new ComboBox<>();
		updatedMartyrLocationLbl = new Label("choose new locartion");
		updatedMartyrLocationCombo = new ComboBox<>();
		updatedMale = new RadioButton("Male");
		updatedFemale = new RadioButton("Female");
		updatedtoggleGroup = new ToggleGroup();
		updatedMale.setToggleGroup(updatedtoggleGroup);
		updatedFemale.setToggleGroup(updatedtoggleGroup);
		updateBack = new Button("back");
		
		grid.add(insert, 0, 0);
		grid.add(update, 0, 1);
		grid.add(updateResult, 0, 2);
		grid.add(delete, 1, 1);
		grid.add(comboMartyrs, 2, 1);
		grid.add(deleteResult, 1, 2);
		grid.add(sizeAndHeightBtn, 0, 4);
		grid.add(sizeAndHeightLbl, 1, 4);
		grid.add(printTree, 0, 5);
		grid.add(printTreeResult, 1, 5);
		grid.add(printMartyrs, 0, 6);
		grid.add(printMartyrsResult, 1, 6);
		grid.add(back, 0, 7);
		
		
		insertGrid = new GridPane();
		insertGrid.setAlignment(Pos.CENTER);
		insertGrid.setHgap(20);
		insertGrid.setVgap(20);
		toggleGroup = new ToggleGroup();
		insertMartyr = new Button("insert");
		insertResult = new Label();
		martyrNameLbl = new Label("Enter name: ");
		martyrNameTxt = new TextField();
		martyrAgeLbl = new Label("Enter age: ");
		martyrAgeTxt = new TextField();
		martyrDistrictLbl = new Label("Enter district");
		martyrDistrictCombo = new ComboBox<>();
		martyrLocationLbl = new Label("Enter Location");
		martyrLocationCombo = new ComboBox<>();
		male = new RadioButton("Male");
		female = new RadioButton("Female");
		male.setToggleGroup(toggleGroup);
		female.setToggleGroup(toggleGroup);
		
		insertGrid.add(insertMartyr, 0, 5);
		insertGrid.add(insertResult, 1, 5);
		insertGrid.add(martyrNameLbl, 0, 0);
		insertGrid.add(martyrNameTxt, 1, 0);
		insertGrid.add(martyrAgeLbl, 0, 1);
		insertGrid.add(martyrAgeTxt, 1, 1);
		insertGrid.add(martyrDistrictLbl, 0, 2);
		insertGrid.add(martyrDistrictCombo, 1, 2);
		insertGrid.add(martyrLocationLbl, 0, 3);
		insertGrid.add(martyrLocationCombo, 1, 3);
		insertGrid.add(male, 0, 4);
		insertGrid.add(female, 1, 4);
		insertGrid.add(insertBack, 0, 6);
		
		updatedGrid.add(updatedMartyr, 0, 5);
		updatedGrid.add(updatedResult, 1, 5);
		updatedGrid.add(updatedMartyrNameLbl, 0, 0);
		updatedGrid.add(updatedMartyrNameTxt, 1, 0);
		updatedGrid.add(updatedMartyrAgeLbl, 0, 1);
		updatedGrid.add(updatedMartyrAgeTxt, 1, 1);
		updatedGrid.add(updatedMartyrDistrictLbl, 0, 2);
		updatedGrid.add(updatedMartyrDistrictCombo, 1, 2);
		updatedGrid.add(updatedMartyrLocationLbl, 0, 3);
		updatedGrid.add(updatedMartyrLocationCombo, 1, 3);
		updatedGrid.add(updatedMale, 0, 4);
		updatedGrid.add(updatedFemale, 1, 4);
		updatedGrid.add(updateBack, 0, 6);
		
		
		
		stackPane = new StackPane();
		tree = new TextArea();
		stackPane.getChildren().add(tree);
		  sceneTree = new Scene(stackPane);
		
	}
     public void insertAction(DateOfMartyrs date) {
    	 
    	 if(!martyrNameTxt.getText().isEmpty() && !martyrAgeTxt.getText().isEmpty() && martyrDistrictCombo.getValue() != null && martyrLocationCombo.getValue() != null) {
    		 
    		 try {
    			 char gender;
    			 int age = Integer.parseInt(martyrAgeTxt.getText());
    			 if(age >=0 && age < 150) {
    			 RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    	            if (selectedRadioButton != null) {
    	                gender = selectedRadioButton.getText().charAt(0);
    	                Martyr martyr = new Martyr(martyrNameTxt.getText(), age, martyrLocationCombo.getValue().getLocation(), martyrDistrictCombo.getValue().getDistrict(), gender);
    	                date.getMartyrs().insert(martyr);
    	                insertResult.setText("process done");
    	            } else {
    	            	insertResult.setText("");
    					Label label = new Label("You don`t choose the gender");
    					StackPane stack = new StackPane();
    					stack.getChildren().add(label);
    					Scene scene = new Scene(stack, 500, 100);
    					Stage stage = new Stage();
    					stage.setScene(scene);
    					stage.show();
    	            }
    			 }else {
    				 insertResult.setText("");
 					Label label = new Label("unaccepted age");
 					StackPane stack = new StackPane();
 					stack.getChildren().add(label);
 					Scene scene = new Scene(stack, 500, 100);
 					Stage stage = new Stage();
 					stage.setScene(scene);
 					stage.show();
    			 }
    		 }catch(Exception ex) {
    			 insertResult.setText("");
					Label label = new Label("The age should be Integer");
					StackPane stack = new StackPane();
					stack.getChildren().add(label);
					Scene scene = new Scene(stack, 500, 100);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
    		 }
    	 }else {
    		 insertResult.setText("");
				Label label = new Label("You don`t Enter Complete information");
				StackPane stack = new StackPane();
				stack.getChildren().add(label);
				Scene scene = new Scene(stack, 500, 100);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
    	 }
     }
public void updateAction(DateOfMartyrs date, Martyr old) {
    	 
    	 if(!updatedMartyrNameTxt.getText().isEmpty() && !updatedMartyrAgeTxt.getText().isEmpty() && updatedMartyrDistrictCombo.getValue() != null && updatedMartyrLocationCombo.getValue() != null) {
    		 
    		 try {
    			 char gender;
    			 int age = Integer.parseInt(updatedMartyrAgeTxt.getText());
    			 if(age >=0 && age < 150) {
    			 RadioButton selectedRadioButton = (RadioButton) updatedtoggleGroup.getSelectedToggle();
    	            if (selectedRadioButton != null) {
    	                gender = selectedRadioButton.getText().charAt(0);
    	                Martyr martyr = new Martyr(updatedMartyrNameTxt.getText(), age, updatedMartyrLocationCombo.getValue().getLocation(), updatedMartyrDistrictCombo.getValue().getDistrict(), gender);
    	                date.getMartyrs().delete(old);
    	                date.getMartyrs().insert(martyr);
    	                updatedResult.setText("process done");
    	            } else {
    	            	updatedResult.setText("");
    					Label label = new Label("You don`t choose the gender");
    					StackPane stack = new StackPane();
    					stack.getChildren().add(label);
    					Scene scene = new Scene(stack, 500, 100);
    					Stage stage = new Stage();
    					stage.setScene(scene);
    					stage.show();
    	            }
    			 }else {
    				 updatedResult.setText("");
 					Label label = new Label("unaccepted age");
 					StackPane stack = new StackPane();
 					stack.getChildren().add(label);
 					Scene scene = new Scene(stack, 500, 100);
 					Stage stage = new Stage();
 					stage.setScene(scene);
 					stage.show();
    			 }
    		 }catch(Exception ex) {
    			 System.out.println(ex.getMessage());
    			 updatedResult.setText("");
					Label label = new Label("The age should be Integer");
					StackPane stack = new StackPane();
					stack.getChildren().add(label);
					Scene scene = new Scene(stack, 500, 100);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
    		 }
    	 }else {
    		 updatedResult.setText("");
				Label label = new Label("You don`t Enter Complete information");
				StackPane stack = new StackPane();
				stack.getChildren().add(label);
				Scene scene = new Scene(stack, 500, 100);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
    	 }
     }
     
     public boolean deleteAction(DateOfMartyrs date) {

    	 if(comboMartyrs.getValue() != null) {
    	    
    		 date.getMartyrs().delete(comboMartyrs.getValue()); 
    			 deleteResult.setText("process done");
    			 return true;
    		
    		
    	 }else {
			 deleteResult.setText("process done");
			 return true;
		 }
     }
     public void displaySizeAndHeightAction(DateOfMartyrs date) {
    	 sizeAndHeightLbl.setText("Size: "+date.getMartyrs().size()+"\nheight: "+date.getMartyrs().height());
     }
     
     public void printTree(DateOfMartyrs date) {
    	 tree.setText(date.getMartyrs().printLevelOrder());
    	
    	 Stage stage = new Stage();
    	 stage.setScene(sceneTree);
    	 stage.show();
     }
     
     public void printMartyrs(DateOfMartyrs date) {
    	 Heap<Martyr> heap = new Heap<>(date.getMartyrs().size() + 1);
    	 LoadMartyrsToHeap(heap, date.getMartyrs());
    	 heap.heapSort();
    	 Martyr[] martyrs = new Martyr[heap.getSize()];
    	 for(int i = 1; i < martyrs.length; i++) {
    		 martyrs[i] = heap.getData(i);
    	 }
    	 Scene scene = new Scene(new MartyrTable(martyrs).getRoot());
    	 Stage stage = new Stage();
    	 stage.setScene(scene);
    	 stage.show();
    	 heap.print();
     }
     public void LoadMartyrsToHeap(Heap<Martyr> heap, AVLTree<Martyr> tree) {
    	 LoadMartyrsToHeap(tree.getRoot(),heap);
 		System.out.println();
 	}

 	private void LoadMartyrsToHeap(TNode<Martyr> node, Heap<Martyr> heap) {
 		if (node != null) {
 			if (node.getLeft() != null)
 				LoadMartyrsToHeap(node.getLeft(),heap);
 			
 			heap.add(node.getData());
 			if (node.getRight() != null)
 				LoadMartyrsToHeap(node.getRight(),heap);
 		}

 	}

 	public void clear() {
 		
 		comboMartyrs.getItems().clear();
 		deleteResult.setText("");;
 		sizeAndHeightLbl.setText("");;
 		
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

	public Button getUpdate() {
		return update;
	}

	public void setUpdate(Button update) {
		this.update = update;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	public Button getSizeAndHeightBtn() {
		return sizeAndHeightBtn;
	}

	public void setSizeAndHeightBtn(Button sizeAndHeightBtn) {
		this.sizeAndHeightBtn = sizeAndHeightBtn;
	}

	public Label getSizeAndHeightLbl() {
		return sizeAndHeightLbl;
	}

	public void setSizeAndHeightLbl(Label sizeAndHeightLbl) {
		this.sizeAndHeightLbl = sizeAndHeightLbl;
	}

	public Button getPrintTree() {
		return printTree;
	}

	public void setPrintTree(Button printTree) {
		this.printTree = printTree;
	}

	public Button getPrintMartyrs() {
		return printMartyrs;
	}

	public void setPrintMartyrs(Button printMartyrs) {
		this.printMartyrs = printMartyrs;
	}

	public GridPane getInsertGrid() {
		return insertGrid;
	}

	public void setInsertGrid(GridPane insertGrid) {
		this.insertGrid = insertGrid;
	}

	public Button getInsertMartyr() {
		return insertMartyr;
	}

	public void setInsertMartyr(Button insertMartyr) {
		this.insertMartyr = insertMartyr;
	}

	public Label getMartyrNameLbl() {
		return martyrNameLbl;
	}

	public void setMartyrNameLbl(Label martyrNameLbl) {
		this.martyrNameLbl = martyrNameLbl;
	}

	public TextField getMartyrNameTxt() {
		return martyrNameTxt;
	}

	public void setMartyrNameTxt(TextField martyrNameTxt) {
		this.martyrNameTxt = martyrNameTxt;
	}

	public Label getMartyrAgeLbl() {
		return martyrAgeLbl;
	}

	public void setMartyrAgeLbl(Label martyrAgeLbl) {
		this.martyrAgeLbl = martyrAgeLbl;
	}

	public TextField getMartyrAgeTxt() {
		return martyrAgeTxt;
	}

	public void setMartyrAgeTxt(TextField martyrAgeTxt) {
		this.martyrAgeTxt = martyrAgeTxt;
	}

	public Label getMartyrDistrictLbl() {
		return martyrDistrictLbl;
	}

	public void setMartyrDistrictLbl(Label martyrDistrictLbl) {
		this.martyrDistrictLbl = martyrDistrictLbl;
	}

	public ComboBox<District> getMartyrDistrictCombo() {
		return martyrDistrictCombo;
	}

	

	public Label getMartyrLocationLbl() {
		return martyrLocationLbl;
	}

	public void setMartyrLocationLbl(Label martyrLocationLbl) {
		this.martyrLocationLbl = martyrLocationLbl;
	}

	public ComboBox<Location>  getMartyrLocationCombo() {
		return martyrLocationCombo;
	}

	

	public RadioButton getMale() {
		return male;
	}

	public void setMale(RadioButton male) {
		this.male = male;
	}

	public RadioButton getFemale() {
		return female;
	}

	public void setFemale(RadioButton female) {
		this.female = female;
	}
	public ComboBox<Martyr> getComboMartyrs() {
		return comboMartyrs;
	}
	public void setComboMartyrs(ComboBox<Martyr> comboMartyrs) {
		this.comboMartyrs = comboMartyrs;
	}
	public Label getDeleteResult() {
		return deleteResult;
	}
	public void setDeleteResult(Label deleteResult) {
		this.deleteResult = deleteResult;
	}
	public Label getInsertResult() {
		return insertResult;
	}
	public void setInsertResult(Label insertResult) {
		this.insertResult = insertResult;
	}
	public ToggleGroup getToggleGroup() {
		return toggleGroup;
	}
	public void setToggleGroup(ToggleGroup toggleGroup) {
		this.toggleGroup = toggleGroup;
	}
	public StackPane getStackPane() {
		return stackPane;
	}
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	public TextArea getTree() {
		return tree;
	}
	public void setTree(TextArea tree) {
		this.tree = tree;
	}
	public GridPane getUpdatedGrid() {
		return updatedGrid;
	}
	public void setUpdatedGrid(GridPane updatedGrid) {
		this.updatedGrid = updatedGrid;
	}
	public Button getUpdatedMartyr() {
		return updatedMartyr;
	}
	public void setUpdatedMartyr(Button updatedMartyr) {
		this.updatedMartyr = updatedMartyr;
	}
	public Label getUpdatedResult() {
		return updatedResult;
	}
	public void setUpdatedResult(Label updatedResult) {
		this.updatedResult = updatedResult;
	}
	public Label getUpdatedMartyrNameLbl() {
		return updatedMartyrNameLbl;
	}
	
	public TextField getUpdatedMartyrNameTxt() {
		return updatedMartyrNameTxt;
	}
	
	public Label getUpdatedMartyrAgeLbl() {
		return updatedMartyrAgeLbl;
	}
	
	public TextField getUpdatedMartyrAgeTxt() {
		return updatedMartyrAgeTxt;
	}
	
	public Label getUpdatedMartyrDistrictLbl() {
		return updatedMartyrDistrictLbl;
	}

	public ComboBox<District> getUpdatedMartyrDistrictCombo() {
		return updatedMartyrDistrictCombo;
	}
	
	public Label getUpdatedMartyrLocationLbl() {
		return updatedMartyrLocationLbl;
	}

	public ComboBox<Location> getUpdatedMartyrLocationCombo() {
		return updatedMartyrLocationCombo;
	}
	
	public RadioButton getUpdatedMale() {
		return updatedMale;
	}
	
	public RadioButton getUpdatedFemale() {
		return updatedFemale;
	}
	
	public ToggleGroup getUpdatedtoggleGroup() {
		return updatedtoggleGroup;
	}
	
	
	
	public Label getUpdateResult() {
		return updateResult;
	}
	public Label getPrintTreeResult() {
		return printTreeResult;
	}
	public Label getPrintMartyrsResult() {
		return printMartyrsResult;
	}
	public Button getBack() {
		return back;
	}
	public Button getInsertBack() {
		return insertBack;
	}
	public void setInsertBack(Button insertBack) {
		this.insertBack = insertBack;
	}
	public Scene getSceneTree() {
		return sceneTree;
	}
	public Button getUpdateBack() {
		return updateBack;
	}
	
}

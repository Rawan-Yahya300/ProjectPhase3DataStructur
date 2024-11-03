package application;



import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class MartyrTable {
	private ObservableList<Martyr> martyrs;
	private BorderPane root = new BorderPane();
	private Martyr[] list;
	
	public MartyrTable(Martyr[] list){
		this.list = list;
		 TableView<Martyr> table = new TableView<>();

         TableColumn<Martyr, String> nameCol = new TableColumn<>("Name");
         nameCol.setMinWidth(300);
         nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

         TableColumn<Martyr, Integer> ageCol = new TableColumn<>("Age");
         ageCol.setMinWidth(100);
         ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

         TableColumn<Martyr, Character> genderCol = new TableColumn<>("Gender");
         genderCol.setMinWidth(50);
         genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
         
         TableColumn<Martyr, String> districtCol = new TableColumn<>("Disrict");
         districtCol.setMinWidth(50);
         districtCol.setCellValueFactory(new PropertyValueFactory<>("district"));
         
         TableColumn<Martyr, String> locationCol = new TableColumn<>("Location");
         locationCol.setMinWidth(50);
         locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

         table.setItems(getMartyrs());
         table.getColumns().addAll(nameCol, ageCol, genderCol, districtCol, locationCol);
         root.setCenter(table);
	}
	 private ObservableList<Martyr> getMartyrs() {
	        martyrs = FXCollections.observableArrayList();

            for(int i = 1; i < list.length; i++) {
            	 martyrs.add(list[i]);
            }
	        return martyrs;
	    }
	public BorderPane getRoot() {
		return root;
	}
	public Martyr[] getList() {
		return list;
	}
	 
}


package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PartsTableView {
    private TableView<Part> list;

    public PartsTableView(ObservableList<Part> partList) {
        this.list = new TableView<>();
        this.list.setItems(partList);
        this.list.setMinWidth(410);
        this.list.setMaxWidth(410);
        this.list.setMinHeight(150);
        this.list.setPrefHeight(100);

        //Here we are creating the columns
        TableColumn<Part, String> idCol = new TableColumn<Part, String>("Part ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> nameCol = new TableColumn<Part, String>("Part Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, String> invLevel = new TableColumn<Part, String>("Inventory Level");
        invLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, String> priceCol = new TableColumn<Part, String>("Price/Cost per unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        invLevel.setMinWidth(100);

        //Here we set the columns and add the tableView to the gridPane
        this.list.getColumns().setAll(idCol, nameCol, invLevel, priceCol);
    }

    public TableView<Part> getPartsTableView() { return this.list; }
}

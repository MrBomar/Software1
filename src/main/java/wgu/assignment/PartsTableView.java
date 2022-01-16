package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PartsTableView {
    private ObservableList<Part> partsList;
    private TableView<Part> list;

    public PartsTableView(ObservableList<Part> partList) {
        this.partsList = partList;
        this.list = new TableView<>();
        this.list.setItems(this.partsList);
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

    public void filterList(String input) {
        if(input.isEmpty() || (input == " ")) {
            this.list.setItems(partsList);
        }
        else {
            FilteredList<Part> filtered = new FilteredList<>(partsList, p -> {
                try {
                    int partId = Integer.parseInt(input);
                    return (partId == p.getId())?true:false;
                }
                catch (Exception e) {
                    return p.getName().toLowerCase().contains(input.toLowerCase());
                }
            });
            this.list.setItems(filtered);
        }
    }
}

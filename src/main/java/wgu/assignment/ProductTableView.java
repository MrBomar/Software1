package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductTableView {
    private  ObservableList<Product> productsList;
    private TableView<Product> list;

    public ProductTableView(ObservableList<Product> productsList) {
        this.productsList = productsList;
        this.list = new TableView<>();
        this.list.setItems(this.productsList);
        this.list.setMinWidth(410);
        this.list.setMaxWidth(410);
        this.list.setMinHeight(150);
        this.list.setPrefHeight(100);

        //Here we are creating the columns
        TableColumn<Product, String> idCol = new TableColumn<Product, String>("Part ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> nameCol = new TableColumn<Product, String>("Part Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, String> invLevel = new TableColumn<Product, String>("Inventory Level");
        invLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Product, String> priceCol = new TableColumn<Product, String>("Price/Cost per unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        invLevel.setMinWidth(100);

        //Here we set the columns and add the tableView to the gridPane
        this.list.getColumns().setAll(idCol, nameCol, invLevel, priceCol);
    }

    public TableView<Product> getProductsTableView() {
        return this.list;
    }

    public void filterList(String input) {
        if(input.isEmpty() || (input ==" ")) {
            this.list.setItems(productsList);
        }
        else {
            FilteredList<Product> filtered = new FilteredList<>(productsList, p -> {
                try {
                    int productId = Integer.parseInt(input);
                    return (productId == p.getId())?true:false;
                }
                catch (Exception e){
                    return p.getName().toLowerCase().contains(input.toLowerCase());
                }
            });
            this.list.setItems(filtered);
        }
    }
}

package wgu.assignment;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductTableView {
    private static Inventory inventory = new Inventory();
    private TableView<Product> list;

    public ProductTableView() {
        this.list = new TableView<>();
        this.list.setItems(inventory.getAllProducts());
        this.list.setMinWidth(400);
        this.list.setMaxWidth(400);
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

        //Here we set the columns and add the tableView to the gridPane
        this.list.getColumns().setAll(idCol, nameCol, invLevel, priceCol);
    }

    public TableView<Product> getProductsTableView() {
        return this.list;
    }
}

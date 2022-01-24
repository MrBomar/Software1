package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class ProductsInventorySubForm extends InventorySubForm {
    private final TableView<Product> productsTableView = new TableView<>();

    //FIXME - Need to fix this so that the Products list is sorted at all times.

    ProductsInventorySubForm() {
        super("Product");
        this.setTableView(Inventory.getAllProducts());

        this.appendAddButton(2, 2);
        this.appendModifyButton(3,2);
        this.appendDeleteButton(4,2);
        this.appendSearchBox("Product");

        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
    }

    protected boolean checkSelection() {
        if(this.productsTableView.getItems().isEmpty()) {
            new Modal("Empty List", "There are no products. Consider adding a product.");
            return false;
        }
        else if(this.productsTableView.getSelectionModel().isEmpty()) {
            new Modal("Missing Selection", "Please select a product.");
        }
        return true;
    }

    protected void setTableView(ObservableList<Product> list) {
        this.productsTableView.setItems(list);
        this.productsTableView.setMinWidth(410);
        this.productsTableView.setMaxWidth(410);
        this.productsTableView.setMinHeight(150);
        this.productsTableView.setPrefHeight(100);

        //Here we are creating the columns
        TableColumn<Product, String> idCol = new TableColumn<>("Part ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> nameCol = new TableColumn<>("Part Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, String> invLevel = new TableColumn<>("Inventory Level");
        invLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Product, String> priceCol = new TableColumn<>("Price/Cost per unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        invLevel.setMinWidth(100);

        //Here we set the columns and add the tableView to the gridPane
        this.productsTableView.getColumns().setAll(idCol, nameCol, invLevel, priceCol);
        this.gridPane.add(this.productsTableView, 0, 1, 5, 1);
    }

    protected void onAddButtonClick() {
        InventoryControlApplication.changeView(Views.PRODUCT);
    }

    protected void onDeleteButtonClick() {
        if(this.checkSelection()) {
            if(!Inventory.deleteProduct(this.productsTableView.getSelectionModel().getSelectedItem())) { //Attempt part deletion
                new Modal("Deletion Error", "Unknown error, product could not be deleted.");
            }
        }
    }

    protected void onModifyButtonClick() {
        if(this.checkSelection()) {
            //Open the product form and pass the selected product
            Product selectedItem = this.productsTableView.getSelectionModel().getSelectedItem();
            int selectedItemIndex = Inventory.getAllProducts().indexOf(selectedItem);
            InventoryControlApplication.changeView(selectedItemIndex, selectedItem);
        }
    }

    protected void onSearchBoxEntered() {
        if (this.productsTableView.getItems().isEmpty()) {
            new Modal("Empty List", "There are no products in the list.");
        }
    }

    protected void onSearchBoxInputChange(Event event) {
        String input = ((TextField) event.getTarget()).getText();

        if (input.isEmpty() || (input.equals(" "))) {
            this.productsTableView.setItems(Inventory.getAllProducts());
        } else {
            FilteredList<Product> filtered = new FilteredList<>(Inventory.getAllProducts(), p -> {
                try {
                    int partId = Integer.parseInt(input);
                    return partId == p.getId();
                } catch (Exception e) {
                    return p.getName().toLowerCase().contains(input.toLowerCase());
                }
            });
            this.productsTableView.setItems(filtered);
        }
    }
}

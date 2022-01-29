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
import static wgu.assignment.ModalMode.*;

/**
 * Renders the products inventory sub form view.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class ProductsInventorySubForm extends InventorySubForm {
    private final TableView<Product> productsTableView = new TableView<>();

    /** Creates an instance of the products inventory sub form. */
    ProductsInventorySubForm() {
        super("Product");
        this.setTableView(Inventory.getAllProducts());

        this.appendAddButton(2);
        this.appendModifyButton();
        this.appendDeleteButton();
        this.appendSearchBox("Product");

        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
    }

    /**
     * Verifies that a selection has been made, displays message if there is an empty list or no selection.
     * <p>RUNTIME ERROR</p>
     * <p>In this method I had forgot to return false after the statement on line 51. This caused the application to
     * throw a NullPointerException error when attempting to load the ProductsTableView without a Product being selected.
     * To fix this I had to insert 'return false;' on line 52.</p>
     * @return Returns false if the list is empty or there is no selection, returns true if selection is made.
     */
    protected boolean checkSelection() {
        if(this.productsTableView.getItems().isEmpty()) {
            new Modal(EMPTY_LIST_PRODUCTS);
            return false;
        }
        else if(this.productsTableView.getSelectionModel().isEmpty()) {
            new Modal(NO_SELECTION_PRODUCT);
            return false;
        }
        return true;
    }

    private void setTableView(ObservableList<Product> list) {
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
        this.productsTableView.getColumns().add(idCol);
        this.productsTableView.getColumns().add(nameCol);
        this.productsTableView.getColumns().add(invLevel);
        this.productsTableView.getColumns().add(priceCol);
        this.gridPane.add(this.productsTableView, 0, 1, 5, 1);
    }

    /** Opens the ProductView in ADD_PRODUCT mode. */
    protected void onAddButtonClick() {
        InventoryControlApplication.changeView(Views.PRODUCT);
    }

    /** Removes selected product from inventory, displays error message if product cannot be deleted. */
    protected void onDeleteButtonClick() {
        if(this.checkSelection()) {
            new Modal(CONFIRM_DELETE_PRODUCT, this.productsTableView.getSelectionModel().getSelectedItem());
        }
    }

    /** Opens the ProductView in MODIFY_PRODUCT mode. */
    protected void onModifyButtonClick() {
        if(this.checkSelection()) {
            //Open the product form and pass the selected product
            Product selectedItem = this.productsTableView.getSelectionModel().getSelectedItem();
            int selectedItemIndex = Inventory.getAllProducts().indexOf(selectedItem);
            InventoryControlApplication.changeView(selectedItemIndex, selectedItem);
        }
    }

    /** Displays a message if there are no products to search. */
    protected void onSearchBoxEntered() {
        if (this.productsTableView.getItems().isEmpty()) {
            new Modal(EMPTY_LIST_PRODUCTS);
        }
    }

    /**
     * Updates the list results based on search string.
     * @param event The event triggered by the user changing the search box input.
     */
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

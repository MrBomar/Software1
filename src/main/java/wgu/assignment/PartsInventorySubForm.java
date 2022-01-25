package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This class renders a JavaFX SubForm view with an embedded TableView view.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class PartsInventorySubForm extends InventorySubForm {
    /** A blank parts list which store modifications until changes are committed. */
    private final TableView<Part> partsTableView = new TableView<>();
    /** The referenced inventory list unaltered until a save has taken place */
    private ObservableList<Part> origList;
    /** A reference to the product being edited. */
    private Product product;

    /**
     * Creates a subform view including a TableView.
     * @param partsList A reference to the inventory to be displayed.
     */
    PartsInventorySubForm(ObservableList<Part> partsList) {
        //Default view
        super("Part");
        this.origList = partsList;
        this.setTableView(partsList);

        this.appendAddButton(2, 2);
        this.appendModifyButton(3,2);
        this.appendDeleteButton(4,2);
        this.appendSearchBox(this.type);

        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
    }

    /**
     * Creates a subform view including a TableView, specific to the associated part of a product.
     * @param list The list of associated item from the product.
     * @param product A reference to the product this list applies to.
     * @param type Indicates the type of form to render.
     */
    PartsInventorySubForm(ObservableList<Part> list, Product product, String type) {
        super(type);
        this.setTableView(list);
        this.product = product;

        if(this.type.equals("Associated Part")) {
            this.appendRemoveButton(2,2,3,1);
        } else if(this.type.equals("All Part")) {
            this.appendAddButton(4, 2);
        }
    }

    /**
     * Appends the "Remove" button to the SubForm view.
     * @param col The column where the button should be located.
     * @param row The row where the button should be located.
     * @param cSpan The number of columns which the button should span.
     * @param rSpan The number of rows which the button should span.
     */
    protected void appendRemoveButton(int col, int row, int cSpan, int rSpan) {
        Button btn = new Button("Remove Associated Part");
        btn.setOnAction(e -> onRemoveButtonClick());
        this.gridPane.add(btn, col, row, cSpan, rSpan);
    }

    /**
     * This method verifies that the list isn't empty and that an item has been selected.
     * If nothing is selected or if the list is empty a message is displayed.
     * @return Method returns true is an item is selected.
     */
    protected boolean checkSelection() {
        if(this.partsTableView.getItems().isEmpty()) {
            new Modal("Empty List", "There are no parts. Consider adding a part.");
            return false;
        }
        else if(this.partsTableView.getSelectionModel().isEmpty()) {
            new Modal("Missing Selection", "Please select a part.");
            return false;
        }
        return true;
    }

    /**
     * This method changes the data for the TableView to list provided.
     * @param list The list to be reflected in the table view.
     */
    protected void setTableView(ObservableList<Part> list) {
        this.partsTableView.setItems(list);
        this.partsTableView.setMinWidth(410);
        this.partsTableView.setMaxWidth(410);
        this.partsTableView.setMinHeight(150);
        this.partsTableView.setPrefHeight(100);

        //Here we are creating the columns
        TableColumn<Part, String> idCol = new TableColumn<>("Part ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> nameCol = new TableColumn<>("Part Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, String> invLevel = new TableColumn<>("Inventory Level");
        invLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, String> priceCol = new TableColumn<>("Price/Cost per unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        invLevel.setMinWidth(100);

        //Here we set the columns and add the tableView to the gridPane
        this.partsTableView.getColumns().setAll(idCol, nameCol, invLevel, priceCol);
        this.gridPane.add(this.partsTableView, 0, 1, 5, 1);
    }

    /**
     * The method executed upon clicking the "Add" button.
     * If the type == "All Part" then this button will add the selected part to the
     * Product's list of associated parts.
     */
    protected void onAddButtonClick() {
        if(this.type.equals("All Part")) {
            if(this.checkSelection()) {
                product.addAssociatedPart(this.partsTableView.getSelectionModel().getSelectedItem());
            }
        }
        else {
            InventoryControlApplication.changeView(Views.PART);
        }
    }

    /**
     * Deletes the part from the Part inventory.
     * If the selected part cannot be deleted then an error message is displayed.
     */
    protected void onDeleteButtonClick() {
        if(this.checkSelection()) {
            if(!Inventory.deletePart(this.partsTableView.getSelectionModel().getSelectedItem())) { //Attempt part deletion
                new Modal("Deletion Error", "Unable to delete part. Check to ensure the part is not associated with any products.");
            }
        }
    }

    /** Open's the Part editing form and loads the part information for modification. */
    protected void onModifyButtonClick() {
        if(this.checkSelection()) {
            //Open the part form and pass the selected part
            Part selectedItem = this.partsTableView.getSelectionModel().getSelectedItem();
            int selectedItemIndex = Inventory.getAllParts().indexOf(selectedItem);
            InventoryControlApplication.changeView(selectedItemIndex, selectedItem);
        }
    }

    /** Removes the selected Part from the Product's associated Parts list. */
    private void onRemoveButtonClick() {
        if(this.checkSelection()) {
            this.product.deleteAssociatedPart(this.partsTableView.getSelectionModel().getSelectedItem());
        }
    }

    /** Displays an error message if the Parts list is empty. */
    protected void onSearchBoxEntered() {
        if(this.partsTableView.getItems().isEmpty()) {
            new Modal("Empty List", "There are no parts in the list.");
        }
    }

    /**
     * This method takes the user input and updates the TableView to reflect the results.
     * @param event Takes the vent trigger by the user changing the search box entry.
     */
    protected void onSearchBoxInputChange(Event event) {
        String input = ((TextField) event.getTarget()).getText();

        if(input.isEmpty() || (input.equals(" "))) {
            this.partsTableView.setItems(origList);
        }
        else {
            FilteredList<Part> filtered = new FilteredList<>(origList, p -> {
                try {
                    int partId = Integer.parseInt(input);
                    return partId == p.getId();
                }
                catch (Exception e) {
                    return p.getName().toLowerCase().contains(input.toLowerCase());
                }
            });
            this.partsTableView.setItems(filtered);
        }
    }
}

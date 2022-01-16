package wgu.assignment;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static wgu.assignment.InventoryControlApplication.*;

public class InventorySubForm {
    private final GridPane gridPane = new GridPane();
    private final Group root = new Group(gridPane);
    private PartsTableView partsTableView;
    private ProductTableView productsTableView;
    private String type;
    private Button addButton;
    private Button deleteButton;
    private Button modifyButton;

    public InventorySubForm(PartsTableView partsTableView, String type) {
        this.type = type;
        setGridPane(this.type);
        this.partsTableView = partsTableView;
        this.gridPane.add(this.partsTableView.getPartsTableView(), 0, 1, 5, 1);
    }

    public InventorySubForm(ProductTableView productTableView, String type) {
        this.type = type;
        setGridPane(this.type);
        this.productsTableView = productTableView;
        this.gridPane.add(this.productsTableView.getProductsTableView(), 0, 1, 5, 1);
    }

    private void setGridPane(String mode) {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.setMaxWidth(430.0);
        this.gridPane.setPrefWidth(430.0);
        this.gridPane.setMinWidth(430.0);

        //Set gridPane column widths
        gridPane.setPadding(new Insets(20, 5, 10, 10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //Add title label
        Label titleLabel = new Label(mode + "s");
        titleLabel.setMinWidth(185);
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 1, 0);

        //Add buttons
        if(this.type.equals("Associated Part")) {
            Button removeAssociatedPartButton = new Button("Remove Associated Part");
            this.gridPane.add(removeAssociatedPartButton, 2, 2, 3, 1);
        }
        else if(this.type.equals("Available Part")) {
            Button addButton = new Button("Add");
            gridPane.add(addButton, 4, 2);
            addButton.setOnAction(e -> addAssociatedPart());
            this.addSearchBox();
        }
        else {
            gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

            this.addButton = new Button("Add");
            this.modifyButton = new Button("Modify");
            this.deleteButton = new Button("Delete");
            gridPane.add(this.addButton, 2, 2);
            gridPane.add(this.modifyButton, 3, 2);
            gridPane.add(this.deleteButton, 4, 2);

            //Button Methods
            this.addButton.setOnAction(e -> onAddButtonClick());
            this.modifyButton.setOnMouseClicked(e -> onModifyButtonClick());
            this.deleteButton.setOnAction(e -> onDeleteButtonClick());

            this.addSearchBox();
        }
    }

    public Group getView() {
        return this.root;
    }

    private void addSearchBox() {
        //Add searchBox
        TextField searchBox = new TextField();
        searchBox.setMinWidth(185);
        searchBox.setPromptText("Search by " + this.type + " ID or Name");

        //Add listeners for searchBox
        searchBox.setOnMouseClicked(e -> this.onSearchBoxEntered());
        searchBox.setOnKeyTyped(e -> this.onSearchBoxInputChange(e));

        this.gridPane.add(searchBox, 2,0, 3, 1);
    }

    /*
        Below are the listener methods
    */

    private void onAddButtonClick() {
        if(this.type.equals("Part")) {
            stage.setScene(new PartForm().getScene());
        }
        else {
            stage.setScene(new ProductForm().getScene());
        }
        stage.show();
    }

    private void onModifyButtonClick() {
        if(this.type.equals("Part")) {
            if(this.partsTableView.getPartsTableView().getItems().isEmpty()) { //Verify there are parts in the list
                appModal.displayMessage("Empty List", "There are no parts to modify. Consider adding a part.");
            }
            else {
                if(this.partsTableView.getPartsTableView().getSelectionModel().isEmpty()) { //Verify a part is selected.
                    appModal.displayMessage("Selection Needed", "Please select a part to modify.");
                }
                else {
                    //Open the part form and pass the selected part
                    Part selectedItem = this.partsTableView.getPartsTableView().getSelectionModel().getSelectedItem();
                    int selectedItemIndex = inventory.getAllParts().indexOf(selectedItem);
                    stage.setScene(new PartForm(selectedItemIndex, selectedItem).getScene());
                }
            }
        }
        else {
            if(this.productsTableView.getProductsTableView().getItems().isEmpty()) { //Verify there are products in the list
                appModal.displayMessage("Empty List", "There are no products to modify. Consider adding a product.");
            }
            else {
                if(this.productsTableView.getProductsTableView().getSelectionModel().isEmpty()) { //Verify a product is selected.
                    appModal.displayMessage("Selection Needed", "Please select a product to modify.");
                }
                else {
                    //Open the product form and pass the selected product
                    Product selectedItem = this.productsTableView.getProductsTableView().getSelectionModel().getSelectedItem();
                    int selectedItemIndex = inventory.getAllProducts().indexOf(selectedItem);
                    stage.setScene(new ProductForm(selectedItemIndex, selectedItem).getScene());
                }
            }
        }
    }

    private void onDeleteButtonClick() {
        if(this.type.equals("Part")) {
            if(this.partsTableView.getPartsTableView().getItems().isEmpty()) { //Verify there are parts in the list
                appModal.displayMessage("Empty List", "There are no parts to delete.");
            }
            else {
                if(this.partsTableView.getPartsTableView().getSelectionModel().isEmpty()) { //Verify a part is selected
                    appModal.displayMessage("Selection Needed.", "Please select a part to delete.");
                }
                else if(!inventory.deletePart(this.partsTableView.getPartsTableView().getSelectionModel().getSelectedItem())) { //Attempt part deletion
                    appModal.displayMessage("Deletion Error", "Unable to delete part. Check to ensure the part is not associated with any products.");
                }
            }
        }
        else {
            if(!this.productsTableView.getProductsTableView().getItems().isEmpty()) { //Verify there are parts in the list
                appModal.displayMessage("Empty List", "There are no products to delete.");
            }
            else {
                if(this.productsTableView.getProductsTableView().getSelectionModel().isEmpty()) { //Verify a part is selected
                    appModal.displayMessage("Selection Needed.", "Please select a product to delete.");
                }
                else if(!inventory.deleteProduct(this.productsTableView.getProductsTableView().getSelectionModel().getSelectedItem())) { //Attempt part deletion
                    appModal.displayMessage("Deletion Error", "Unknown error, product could not be deleted.");
                }
            }
        }
    }

    private void addAssociatedPart() {
        //FIXME - Add method to add associated part to the product associated parts list

    }

    private void onSearchBoxEntered() {
        if(this.type.equals("Part")) {
            if(this.partsTableView.getPartsTableView().getItems().isEmpty()) {
                appModal.displayMessage("Empty List", "There are no parts to search. Consider adding a part.");
                this.addButton.requestFocus();
            }
        }
        else {
            if(this.productsTableView.getProductsTableView().getItems().isEmpty()) {
                appModal.displayMessage("Empty List", "There are no products to search. Consider adding a product.");
            }
        }
    }

    private void onSearchBoxInputChange(Event e) {
        TextField targetField = (TextField) e.getTarget();
        System.out.println(targetField.getText());

        //If the TextField is empty then set the data table to allParts.
        //If the TextField is populated, then filter results.

        switch(this.type) {
            case "Part":
            case "Associated Part":
            case "Available Part":
                this.partsTableView.filterList(targetField.getText());
                break;
            default:
                this.productsTableView.filterList(targetField.getText());
        }
    }
}

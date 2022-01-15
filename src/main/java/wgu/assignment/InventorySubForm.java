package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static wgu.assignment.InventoryControlApplication.inventory;
import static wgu.assignment.InventoryControlApplication.stage;

public class InventorySubForm {
    private final GridPane gridPane = new GridPane();
    private final Group root = new Group(gridPane);
    private TableView<Part> partsTableView;
    private TableView<Product> productsTableView;
    private String type;
    int selectionId;

    public InventorySubForm(PartsTableView partsTableView, String type) {
        this.type = type;
        setGridPane(this.type);
        this.partsTableView = partsTableView.getPartsTableView();
        this.gridPane.add(this.partsTableView, 0, 1, 5, 1);
    }

    public InventorySubForm(ProductTableView productTableView, String type) {
        this.type = type;
        setGridPane(this.type);
        this.productsTableView = productTableView.getProductsTableView();
        this.gridPane.add(this.productsTableView, 0, 1, 5, 1);
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
        Label titleLabel = new Label(mode);
        titleLabel.setMinWidth(185);
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 1, 0);

        //Add buttons
        if(this.type.equals("Associated Parts")) {
            Button removeAssociatedPartButton = new Button("Remove Associated Part");
            this.gridPane.add(removeAssociatedPartButton, 2, 2, 3, 1);
//            this.gridPane.setConstraints(this.partsTableView, 0, 1, 1, 1);
        }
        else if(this.type.equals("Available Parts")) {
            Button addButton = new Button("Add");
            gridPane.add(addButton, 4, 2);
            addButton.setOnAction(e -> addAssociatedPart());
            this.addSearchBox();
        }
        else {
            gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

            Button addButton = new Button("Add");
            Button modifyButton = new Button("Modify");
            Button deleteButton = new Button("Delete");
            gridPane.add(addButton, 2, 2);
            gridPane.add(modifyButton, 3, 2);
            gridPane.add(deleteButton, 4, 2);

            //Button Methods
            addButton.setOnAction(e -> onAddButtonClick());
            modifyButton.setOnAction(e -> onModifyButtonClick());
            deleteButton.setOnAction(e -> onDeleteButtonClick());

            this.addSearchBox();
        }
    }

    private void setSelectionId() {
        if(this.type.equals("Parts")) {
            this.selectionId = partsTableView.
                    getSelectionModel().getSelectedItem().getId();
        }
        else {
            this.selectionId = productsTableView.
                    getSelectionModel().getSelectedItem().getId();
        }
    }

    public Group getView() {
        return this.root;
    }

    private void onAddButtonClick() {
        if(this.type.equals("Parts")) {
            stage.setScene(new PartForm().getScene());
        }
        else {
            stage.setScene(new ProductForm().getScene());
        }
        stage.show();
    }

    private void onModifyButtonClick() {
        if(this.type.equals("Parts")) {
            stage.setScene(new PartForm(this.partsTableView.
                    getSelectionModel().getSelectedItem()).getScene());
        }
        else {
            //FIXME - Add method to open the productForm
        }
    }

    private void onDeleteButtonClick() {
        if(this.type == "Parts") {
            if(!this.partsTableView.getSelectionModel().isEmpty()) {
                Part selectedPart = this.partsTableView.getSelectionModel().getSelectedItem();
                if(!inventory.deletePart(selectedPart)) {
                    InventoryControlApplication.appModal.displayMessage("Part Deletion Error", "Unknown Error");
                }
            } else {
                //No part is selected.
                InventoryControlApplication.appModal.displayMessage("Part Deletion Error", "Nothing has been selected.");
            }
        }
        else {
            //FIXME - Add deletion method for products
        }
    }

    private void addAssociatedPart() {
        //FIXME - Add method to add associated part to the product associated parts list

    }

    private void addSearchBox() {
        //Add searchBox
        TextField searchBox = new TextField();
        searchBox.setMinWidth(185);
        searchBox.setPromptText("Search by Part ID or Number");
        this.gridPane.add(searchBox, 2,0, 3, 1);
    }
}

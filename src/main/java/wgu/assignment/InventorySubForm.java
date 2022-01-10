package wgu.assignment;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static wgu.assignment.InventoryControlApplication.stage;

public class InventorySubForm {
    private GridPane gridPane = new GridPane();
    private Group root = new Group(gridPane);
    private PartsTableView partsTableView;
    private ProductTableView productsTableView;
    private String type;
    int selectionId;

    public InventorySubForm(PartsTableView partsTableView) {
        this.type = "Parts";
        setGridPane(this.type);
        this.partsTableView = partsTableView;
        gridPane.add(this.partsTableView.getPartsTableView(), 0, 1, 5, 1);
    }

    public InventorySubForm(ProductTableView productTableView) {
        this.type = "Products";
        setGridPane(this.type);
        this.productsTableView = productTableView;
        gridPane.add(this.productsTableView.getProductsTableView(), 0, 1, 5, 1);
    }

    private void setGridPane(String mode) {
        //Set gridPane column widths
        gridPane.setPadding(new Insets(20, 5, 10, 10));
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //Add title label
        Label titleLabel = new Label(mode);
        titleLabel.setMinWidth(175);
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 1, 0);

        //Add searchBox
        TextField searchBox = new TextField();
        searchBox.setPromptText("Search by Part ID or Number");
        gridPane.add(searchBox, 2,0, 3, 1);

        //Add buttons
        Button addButton = new Button("Add");
        Button modifyButton = new Button("Modify");
        Button deleteButton = new Button("Delete");
        gridPane.add(addButton, 2, 2);
        gridPane.add(modifyButton, 3, 2);
        gridPane.add(deleteButton, 4, 2);

        //Button Methods
        addButton.setOnAction(e -> onOpenButtonClick());
        modifyButton.setOnAction(e -> onModifyButtonClick());
        deleteButton.setOnAction(e -> onDeleteButtonClick());
    }

    private void setSelectionId() {
        if(this.type == "Parts") {
            this.selectionId = partsTableView.getPartsTableView().
                    getSelectionModel().getSelectedItem().getId();
        }
        else {
            this.selectionId = productsTableView.getProductsTableView().
                    getSelectionModel().getSelectedItem().getId();
        }
    }

    public Group getView() {
        return this.root;
    }

    private void onOpenButtonClick() {
        if(this.type == "Parts") {
            stage.setScene(new PartForm().getScene());
        }
        else {
            //FIXME - Add method to open the productForm
        }
    }

    private void onModifyButtonClick() {
        if(this.type == "Parts") {
            stage.setScene(new PartForm(this.partsTableView.getPartsTableView().
                    getSelectionModel().getSelectedItem()).getScene());
        }
        else {
            //FIXME - Add method to open the productForm
        }
    }

    private void onDeleteButtonClick() {
        if(this.type == "Parts") {
            //FIXME - Add deletion method for parts
        }
        else {
            //FIXME - Add deletion method for products
        }
    }

}

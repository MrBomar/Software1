package wgu.assignment;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainForm {
    private final GridPane gridPane = new GridPane();
    private final Scene scene = new Scene(this.gridPane);

    public MainForm() {
        //Temporarily set grid to visible
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.setMinWidth(950);

        //Add title label
        Label titleLabel = new Label("Inventory Management System");
        titleLabel.setStyle("-fx-font-weight: bold");
        this.gridPane.add(titleLabel, 0, 0, 2, 1);

        //Format the gridPane
        this.gridPane.setPadding(new Insets(20, 0, 15, 20));
        this.gridPane.setVgap(20);
        this.gridPane.setHgap(10);

        //Add the inventory panels
        final InventorySubForm partsSubForm = new InventorySubForm(
                new PartsTableView(InventoryControlApplication.inventory.getAllParts()), "Part");
        final InventorySubForm productsSubForm = new InventorySubForm(
                new ProductTableView(InventoryControlApplication.inventory.getAllProducts()), "Product");
        this.gridPane.add(partsSubForm.getView(), 1, 1);
        this.gridPane.add(productsSubForm.getView(), 2,1);

        //Let's add a Pane to the grid pane?
        HBox exitBox = new HBox();
        Pane spacer = new Pane();
        spacer.setMinSize(350,1);
        Button exitButton = new Button("Exit");
        exitBox.getChildren().addAll(spacer, exitButton);
        this.gridPane.add(exitBox, 2,2);

        //Exit method
        exitButton.setOnAction(e -> Platform.exit());
    }

    public Scene getScene() {
        return this.scene;
    }
}

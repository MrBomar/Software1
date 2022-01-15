package wgu.assignment;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainForm {
    private InventorySubForm partsSubForm = new InventorySubForm(new PartsTableView(InventoryControlApplication.inventory.getAllParts()), "Parts");
    private InventorySubForm productsSubForm = new InventorySubForm(new ProductTableView(), "Products");

    public MainForm() {

    }

    public Scene getScene() {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);

        //Temporarily set grid to visible
        gridPane.setGridLinesVisible(false);
        gridPane.setMinWidth(950);

        //Add title label
        Label titleLabel = new Label("Inventory Management System");
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 0, 0, 2, 1);

        //Format the gridPane
        gridPane.setPadding(new Insets(20, 0, 15, 20));
        gridPane.setVgap(20);
        gridPane.setHgap(10);

        //Add the inventory panels
        gridPane.add(partsSubForm.getView(), 1, 1);
        gridPane.add(productsSubForm.getView(), 2,1);

        //Let's add a Pane to the grid pane?
        HBox exitBox = new HBox();
        Pane spacer = new Pane();
        spacer.setMinSize(350,1);
        Button exitButton = new Button("Exit");
        exitBox.getChildren().addAll(spacer, exitButton);
        gridPane.add(exitBox, 2,2);

        //Exit method
        exitButton.setOnAction(e -> Platform.exit());

        return scene;
    }
}

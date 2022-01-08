package wgu.assignment;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class MainForm {
    private static Inventory inventory;
    private static Stage stage;

    public MainForm() {
        this.inventory = InventoryControlApplication.inventory;
        this.stage = InventoryControlApplication.stage;
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
        gridPane.add(buildInventorySubScene("parts"), 1, 1);
        gridPane.add(buildInventorySubScene("products"), 2,1);

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

    private static SubScene buildInventorySubScene(String type) {
        GridPane gridPane = new GridPane();
        Group root = new Group(gridPane);

        //Set gridPane column widths
        gridPane.setPadding(new Insets(20, 5, 10, 10));
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //Add title label
        Label titleLabel = new Label((type == "parts")?"Parts":"Products");
        titleLabel.setMinWidth(175);
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 1, 0);

        //Add searchBox
        TextField searchBox = new TextField();
        searchBox.setPromptText("Search by Part ID or Number");
        gridPane.add(searchBox, 2,0, 3, 1);

        //Add Inventory List
        if(type == "parts") {
            PartsTableView partsTableView = new PartsTableView();
            gridPane.add(partsTableView.getPartsTableView(), 0, 1, 5, 1);
        }
        else {
            ProductTableView productTableView = new ProductTableView();
            gridPane.add(productTableView.getProductsTableView(), 0 ,1 ,5,1);
        }

        //Add buttons
        Button addButton = new Button("Add");
        Button modifyButton = new Button("Modify");
        Button deleteButton = new Button("Delete");
        gridPane.add(addButton, 2, 2);
        gridPane.add(modifyButton, 3, 2);
        gridPane.add(deleteButton, 4, 2);

        //Add button method
        addButton.setOnAction(e -> addPartForm(type));

        //Modify button method
        modifyButton.setOnAction((e -> modifyPartForm(type)));

        //Delete button method
        deleteButton.setOnAction(e -> deletePart(type));

        SubScene subScene = new SubScene(root, 425, 242);
        return subScene;
    }

    private static void addPartForm(String type) {
        PartForm partForm = new PartForm();
        stage.setScene(partForm.getScene());
    }

    private static void modifyPartForm(String type) {
        //FIXME - modify
    }

    private static void deletePart(String type) {
        //FIXME - need to add logic to remove part from Inventory.partsList
    }
}

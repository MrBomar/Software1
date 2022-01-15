package wgu.assignment;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Scene;

public abstract class ItemForm {
    protected static Inventory inventory = InventoryControlApplication.inventory;
    protected static Stage stage = InventoryControlApplication.stage;
    protected String mode;
    protected GridPane gridPane = new GridPane();
    protected Scene scene = new Scene(this.gridPane);
    protected GridPane gridForm = new GridPane();
    protected final Label titleLabel = new Label();
    protected final Label idLabel = new Label("ID");
    protected final Label nameLabel = new Label("Name");
    protected final Label stockLabel = new Label("Inv");
    protected final Label priceLabel = new Label("Price/Cost");
    protected final Label maxLabel = new Label("Max");
    protected final Label minLabel = new Label("Min");
    protected TextField idField = new TextField();
    protected TextField nameField = new TextField();
    protected TextField stockField = new TextField();
    protected TextField priceField = new TextField();
    protected TextField maxField = new TextField();
    protected TextField minField = new TextField();
    protected Button saveButton = new Button("Save");
    protected Button cancelButton = new Button("Cancel");

    public ItemForm(String mode, String titleLabelText, String priceLabelText) {
        this.mode = mode;
        this.titleLabel.setText(titleLabelText);
        this.priceLabel.setText(priceLabelText);

        //Format gridPane
        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setPadding(new Insets(50,50,50,50));
        this.gridPane.setHgap(45);

        //Format gridForm
        this.gridForm.setGridLinesVisible(false);
        this.gridForm.setPadding(new Insets(40,20,20,20));
        this.gridForm.setHgap(25);
        this.gridForm.setVgap(20);

        //Format title label
        this.titleLabel.setStyle("-fx-font-weight: bold");

        //Add Labels to the grid
        this.gridPane.add(this.titleLabel, 0, 0);
        this.gridForm.add(this.idLabel, 0, 0);
        this.gridForm.add(this.nameLabel, 0, 1);
        this.gridForm.add(this.stockLabel, 0, 2);
        this.gridForm.add(this.priceLabel, 0, 3);
        this.gridForm.add(this.maxLabel, 0, 4);
        this.gridForm.add(this.minLabel, 2, 4);

        //Add TextFields to the grid
        this.gridForm.add(this.idField, 1, 0);
        this.gridForm.add(this.nameField, 1, 1);
        this.gridForm.add(this.stockField, 1, 2);
        this.gridForm.add(this.priceField, 1, 3);
        this.gridForm.add(this.maxField, 1, 4);
        this.gridForm.add(this.minField, 3, 4);

        //Set button methods
        this.cancelButton.setOnAction(e -> this.cancelButtonMethod());
        this.saveButton.setOnAction((e -> this.saveButtonMethod()));

        //Add Buttons to GridPane
        this.gridForm.add(this.saveButton, 2, 6);
        this.gridForm.add(this.cancelButton, 3, 6);

        //Add gridForm to gridPane
        this.gridPane.add(this.gridForm, 0, 1, 3, 1);
    }

    public Scene getScene() {
        return this.scene;
    }

    abstract protected void saveButtonMethod();
    abstract protected void cancelButtonMethod();

    protected void closeForm() {
        InventoryControlApplication.stage.setScene(InventoryControlApplication.mainForm.getScene());
    }
}

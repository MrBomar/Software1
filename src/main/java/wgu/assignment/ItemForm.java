package wgu.assignment;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    protected Pane pane = new Pane(this.gridPane);
    protected Scene scene = new Scene(this.pane);
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
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.setPadding(new Insets(50,50,50,50));
        this.gridPane.setHgap(45);

        //Format gridForm
        this.gridForm.setGridLinesVisible(false);
        this.gridForm.setPadding(new Insets(40,20,20,20));
        this.gridForm.setHgap(25);
        this.gridForm.setVgap(20);

        //Format title label
        this.titleLabel.setStyle("-fx-font-weight: bold");

        //Set idField prompt text and disable
        this.idField.setPromptText("Auto Gen- Disabled");
        this.idField.setDisable(true);

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

    protected void addButtonsToGridPane() {
        /*
            This method should be called last when building the scene
            doing this will ensure the correct tab order is maintained.
         */
        this.gridForm.add(this.saveButton, 2, 6);
        this.gridForm.add(this.cancelButton, 3, 6);
    }

    protected void idFieldClicked() {
        Modal modal = new Modal();
        modal.displayMessage("Modification Denied", "The part ID is auto-assigned and cannot be modified by the user.");
    }

    /*
    Below are the user input validation methods
     */

    private boolean validInt(String string) {
        try {
            int test = Integer.parseInt(string);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean validDouble(String string) {
        try {
            double test = Double.parseDouble(string);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private int getStockInt() {
        return Integer.parseInt(this.stockField.getText());
    }

    private double getPriceDouble() {
        return Double.parseDouble(this.priceField.getText());
    }

    private int getMaxInt() {
        return Integer.parseInt(this.maxField.getText());
    }

    private int getMinInt() {
        return Integer.parseInt(this.minField.getText());
    }

    protected boolean validateBeforeSave() {
        if(!this.validInt(this.stockField.getText())) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Inv' field is invalid. /n Please enter an non-decimal numeric value.");
            return false;
        }
        else if(!this.validDouble(this.priceField.getText())) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Price' field is invalid. /n Please enter a valid dollar amount.");
            return false;
        }
        else if(!this.validInt(this.maxField.getText())) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Max' field is invalid. /n Please enter a non-decimal numeric value.");
            return false;
        }
        else if(!this.validInt(this.minField.getText())) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Min' field is invalid. /n Please enter a non-decimal numeric value.");
            return false;
        }
        else if(this.getStockInt() < 0) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Inv' field is invalid. /n Number cannot be negative.");
            return false;
        }
        else if(this.getPriceDouble() < 0) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Price' field is invalid. /n Number cannot be negative.");
            return false;
        }
        else if(this.getMaxInt() < 0) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Max' field is invalid. /n Number cannot be negative.");
            return false;
        }
        else if(this.getMinInt() < 0) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value entered in the 'Min' field is invalid. /n Number cannot be negative.");
            return false;
        }
        else if(this.getMaxInt() < this.getMinInt()) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                    "The value in 'Max' cannot be less than the value in 'Min'.");
            return false;
        }
        else if((this.getStockInt() < this.getMinInt())||(this.getStockInt() > this.getMaxInt())) {
            InventoryControlApplication.appModal.displayMessage("Invalid Entry", "" +
                    "The value is 'Inv' cannot be less than 'Min' or less than 'Max'.");
            return false;
        }
        else {
            return true;
        }
    }
}

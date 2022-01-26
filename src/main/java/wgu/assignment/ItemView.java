package wgu.assignment;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import static wgu.assignment.Views.*;

/**
 * This is an abstract class for the Part and Product editing view.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public abstract class ItemView {
    /** Specifies how the view is to be rendered. */
    protected ViewMode mode;
    /** Used to lay out the form within the view. */
    protected GridPane gridPane = new GridPane();
    /** Used to lay out the form next to the sub forms. */
    protected Pane pane = new Pane(this.gridPane);
    /** The element passed to the changeView to render a new view. */
    protected Scene scene = new Scene(this.pane);
    /** The inner grid for the form. */
    protected GridPane gridForm = new GridPane();
    /** The title label. */
    protected final Label titleLabel = new Label();
    /** The 'ID' field label. */
    protected final Label idLabel = new Label("ID");
    /** The 'Name' field label. */
    protected final Label nameLabel = new Label("Name");
    /** The 'Stock' field label. */
    protected final Label stockLabel = new Label("Inv");
    /** The 'Price' field label. */
    protected final Label priceLabel = new Label("Price/Cost");
    /** The 'Max' field label. */
    protected final Label maxLabel = new Label("Max");
    /** The 'Min' field label. */
    protected final Label minLabel = new Label("Min");
    /** The 'ID' input field. */
    protected TextField idField = new TextField();
    /** The 'Name' input field. */
    protected TextField nameField = new TextField();
    /** The 'Stock' input field. */
    protected TextField stockField = new TextField();
    /** The 'Price' input field. */
    protected TextField priceField = new TextField();
    /** The 'Max' input field. */
    protected TextField maxField = new TextField();
    /** The 'Min' input field. */
    protected TextField minField = new TextField();
    /** The 'Save' button. */
    protected Button saveButton = new Button("Save");
    /** The 'Cancel' button. */
    protected Button cancelButton = new Button("Cancel");

    /**
     * Instantiates ItemForm and renders initial view.
     * @param mode Indicates how the form should be rendered and what elements to include.
     */
    public ItemView(ViewMode mode) {
        this.mode = mode;
        this.titleLabel.setText(mode.label);
        this.priceLabel.setText(mode.priceLabel);

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

    /**
     * Returns the rendered view.
     * @return Returns the rendered view as a Scene object.
     */
    public Scene getScene() {
        return this.scene;
    }

    /** Abstract method sets the layout of the form. */
    protected abstract void setStage();

    /** Abstract method executed when the "Save" button is clicked. */
    abstract protected void saveButtonMethod();

    /** Closes the current view and displays the Main form, without committing any changes. */
    protected void cancelButtonMethod() {
        InventoryControlApplication.changeView(Views.MAIN);
    }

    /** Changes the view to the MainForm. */
    protected void closeForm() {
        InventoryControlApplication.changeView(MAIN);
    }

    /** Appends the buttons as a group to preserve tab order. */
    protected void addButtonsToGridPane() {
        /*
            This method should be called last when building the scene
            doing this will ensure the correct tab order is maintained.
         */
        this.gridForm.add(this.saveButton, 2, 6);
        this.gridForm.add(this.cancelButton, 3, 6);
    }

    /**
     * Evaluates a string and returns true if it can be parsed as an integer.
     * @param string Any string.
     * @return If the provided string can be evaluated as an Integer then function will return 'true'.
     */
    protected boolean validInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Evaluates a string and returns true if it can be parsed as a double.
     * @param string Any string.
     * @return Returns 'true' if the string can be an integer, 'false.
     */
    private boolean validDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the integer value from the stock field on the form.
     * @return The integer value of the "Stock" field.
     */
    protected int getStockInt() {
        return Integer.parseInt(this.stockField.getText());
    }

    /**
     * Returns the double value from the price field on the form.
     * @return Returns the double value of the "Price" field.
     */
    protected double getPriceDouble() {
        return Double.parseDouble(this.priceField.getText());
    }

    /**
     * Returns the integer value from the max field on the form.
     * @return Returns the integer value of the 'Max' field.
     */
    protected int getMaxInt() {
        return Integer.parseInt(this.maxField.getText());
    }

    /**
     * Returns the integer value from the min field on the form.
     * @return Returns the integer value of the 'Min' field.
     */
    protected int getMinInt() {
        return Integer.parseInt(this.minField.getText());
    }

    /**
     * Evaluates that the form entries meet criteria and displays a message if criteria isn't met.
     * @return Returns 'true' if the user input fields meet data entry criteria.
     */
    protected boolean validateBeforeSave() {
        if(!this.validInt(this.stockField.getText())) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Inv' field is invalid. Please enter an non-decimal numeric value.");
            return false;
        }
        else if(!this.validDouble(this.priceField.getText())) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Price' field is invalid. Please enter a valid dollar amount.");
            return false;
        }
        else if(!this.validInt(this.maxField.getText())) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Max' field is invalid. Please enter a non-decimal numeric value.");
            return false;
        }
        else if(!this.validInt(this.minField.getText())) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Min' field is invalid. Please enter a non-decimal numeric value.");
            return false;
        }
        else if(this.getStockInt() < 0) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Inv' field is invalid. Number cannot be negative.");
            return false;
        }
        else if(this.getPriceDouble() < 0) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Price' field is invalid. Number cannot be negative.");
            return false;
        }
        else if(this.getMaxInt() < 0) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Max' field is invalid. Number cannot be negative.");
            return false;
        }
        else if(this.getMinInt() < 0) {
            new Modal("Invalid Entry",
                    "The value entered in the 'Min' field is invalid. Number cannot be negative.");
            return false;
        }
        else if(this.getMaxInt() < this.getMinInt()) {
            new Modal("Invalid Entry",
                    "The value in 'Max' cannot be less than the value in 'Min'.");
            return false;
        }
        else if((this.getStockInt() < this.getMinInt())||(this.getStockInt() > this.getMaxInt())) {
            new Modal("Invalid Entry", "" +
                    "The value is 'Inv' cannot be less than 'Min' or less than 'Max'.");
            return false;
        }
        else {
            return true;
        }
    }
}

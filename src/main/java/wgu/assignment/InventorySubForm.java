package wgu.assignment;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * This is an abstract class for the embedded inventory forms on the MainForm.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public abstract class InventorySubForm {
    /** The grid used to format the view. */
    protected GridPane gridPane = new GridPane();
    private final Group root = new Group(gridPane);
    /** Indicates the type of form to render. */
    protected String type;

    /**
     * This method sets the basic formatting of the inventory sub-form view.
     * @param type This string value sets the title of the view and can be used by child classes for function specific purposes.
     */
    protected InventorySubForm(String type) {
        this.type = type;
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.setMaxWidth(430.0);
        this.gridPane.setPrefWidth(430.0);
        this.gridPane.setMinWidth(430.0);

        //Set gridPane column widths
        gridPane.setPadding(new Insets(20, 5, 10, 10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //Add title label
        Label titleLabel = new Label(this.type + "s");
        titleLabel.setMinWidth(185);
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 1, 0);
    }

    /**
     * This method returns the rendered view of the inventory sub-form.
     * @return Returns the entire view as a Group object so that it can be appended to another view.
     */
    public Group getView() {
        return this.root;
    }

    /**
     * This method appends an "Add" button to the sub-form view.
     * @param col The column value where the button should be placed.
     */
    protected void appendAddButton(int col) {
        Button btn = new Button("Add");
        btn.setOnAction(e -> onAddButtonClick());
        this.gridPane.add(btn, col, 2);
    }

    /** This method appends a "Delete" button to the sub-form view. */
    protected void appendDeleteButton() {
        Button btn = new Button("Delete");
        btn.setOnAction(e -> onDeleteButtonClick());
        this.gridPane.add(btn, 4, 2);
    }

    /** This method appends a "Modify" button to the sub-form view. */
    protected void appendModifyButton() {
        Button btn = new Button("Modify");
        btn.setOnAction(e -> onModifyButtonClick());
        this.gridPane.add(btn, 3,2);
    }

    /**
     * This method appends an input box onto the sub-form.
     * @param type Specifies the type of item to search for (Part or Product).
     */
    protected void appendSearchBox(String type) {
        //Add searchBox
        TextField searchBox = new TextField();
        searchBox.setMinWidth(185);
        searchBox.setPromptText("Search by " + type + " ID or Name");

        //Add listeners for searchBox
        searchBox.setOnMouseClicked(e -> this.onSearchBoxEntered());
        searchBox.setOnKeyTyped(this::onSearchBoxInputChange);

        this.gridPane.add(searchBox, 2,0, 3, 1);
    }

    /** Abstract method to be executed when the "Add" button is clicked. */
    protected abstract void onAddButtonClick();

    /** Abstract method to be executed when the "Delete" button is clicked. */
    protected abstract void onDeleteButtonClick();

    /** Abstract method to be executed when the "Modify" button is clicked. */
    protected abstract void onModifyButtonClick();

    /** Abstract method to be executed the user's cursor enters the search box. */
    protected abstract void onSearchBoxEntered();

    /**
     * Abstract method to be executed when the user changes the input of the search box.
     * @param e The event triggered by the user action.
     */
    protected abstract void onSearchBoxInputChange(Event e);

    /**
     * Abstract method used to verify that a selection has been made.
     * @return False if a selection is not made or if list is empty, true if selection is made.
     */
    protected abstract boolean checkSelection();
}

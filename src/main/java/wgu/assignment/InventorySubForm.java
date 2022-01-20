package wgu.assignment;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public abstract class InventorySubForm {
    protected GridPane gridPane = new GridPane();
    private final Group root = new Group(gridPane);
    protected String type;

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

    public Group getView() {
        return this.root;
    }

    protected void appendAddButton(int col, int row) {
        Button btn = new Button("Add");
        btn.setOnAction(e -> onAddButtonClick());
        this.gridPane.add(btn, col, row);
    }

    protected void appendDeleteButton(int col, int row) {
        Button btn = new Button("Delete");
        btn.setOnAction(e -> onDeleteButtonClick());
        this.gridPane.add(btn, col, row);
    }

    protected void appendModifyButton(int col, int row) {
        Button btn = new Button("Modify");
        btn.setOnAction(e -> onModifyButtonClick());
        this.gridPane.add(btn, col, row);
    }

    protected void appendSearchBox(String type) {
        //Add searchBox
        TextField searchBox = new TextField();
        searchBox.setMinWidth(185);
        searchBox.setPromptText("Search by " + type + " ID or Name");

        //Add listeners for searchBox
        searchBox.setOnMouseClicked(e -> this.onSearchBoxEntered());
        searchBox.setOnKeyTyped(e -> this.onSearchBoxInputChange(e));

        this.gridPane.add(searchBox, 2,0, 3, 1);
    }

    protected abstract void onAddButtonClick();

    protected abstract void onDeleteButtonClick();

    protected abstract void onModifyButtonClick();

    protected abstract void onSearchBoxEntered();

    protected abstract void onSearchBoxInputChange(Event e);

    protected abstract boolean checkSelection();
}

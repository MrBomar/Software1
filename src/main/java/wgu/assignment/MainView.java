package wgu.assignment;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * This class uses JavaFX to render the main form view
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class MainView {
    private final GridPane gridPane = new GridPane();
    private final Scene scene = new Scene(this.gridPane);

    /** Instantiates the class and formats the basic form view. */
    public MainView() {
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
        final PartsInventorySubForm partsSubForm = new PartsInventorySubForm(
                Inventory.getAllParts()
        );
        final ProductsInventorySubForm productsSubForm = new ProductsInventorySubForm();
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

    /**
     * This method returns a reference to the Scene object which can be rendered as a view.
     * @return Returns a JavaFX Scene view of the MainForm.
     */
    public Scene getScene() {
        return this.scene;
    }
}

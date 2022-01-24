package wgu.assignment;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class creates and renders a JavaFX modal to display important messages.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class Modal {

    /**
     * This constructor will create and render a message modal.
     * @param title The title to be displayed at the top of the modal.
     * @param message The message to be displayed in the body of the modal.
     */
    public Modal(String title, String message) {
        final Stage modalStage = new Stage();
        final GridPane gridPane = new GridPane();
        final Label messageLabel = new Label();
        final Button okButton = new Button("OK");

        modalStage.setTitle(title);
        messageLabel.setText(message);

        //Stage properties
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UTILITY);

        //GridPane and properties
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(false);

        //Add label
        gridPane.add(messageLabel,0,0, 1, 1);

        //Add buttons
        gridPane.add(okButton, 0, 1);
        GridPane.setHalignment(okButton, HPos.CENTER);

        //Assigning okButton method
        okButton.setOnMouseClicked(e -> modalStage.hide());

        //Create Scene and add to Stage
        modalStage.setScene(new Scene(gridPane));
        modalStage.show();
    }
}

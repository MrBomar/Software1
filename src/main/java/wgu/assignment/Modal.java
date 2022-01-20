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

public class Modal {
    private final Stage modalStage = new Stage();
    private final GridPane gridPane = new GridPane();
    private final Label message = new Label();
    private final Button okButton = new Button("OK");

    public Modal() {
        //Stage properties
        this.modalStage.initModality(Modality.APPLICATION_MODAL);
        this.modalStage.initStyle(StageStyle.UTILITY);

        //GridPane and properties
        this.gridPane.setPadding(new Insets(20,20,20,20));
        this.gridPane.setVgap(10);
        this.gridPane.setGridLinesVisible(false);

        //Add label
        this.gridPane.add(this.message,0,0, 1, 1);

        //Add buttons
        this.gridPane.add(this.okButton, 0, 1);
        GridPane.setHalignment(this.okButton, HPos.CENTER);

        //Assigning okButton method
        this.okButton.setOnMouseClicked(e -> modalStage.hide());

        //Create Scene and add to Stage
        this.modalStage.setScene(new Scene(this.gridPane));
    }

    public void displayMessage(String title, String message) {
        this.modalStage.setTitle(title);
        this.message.setText(message);
        this.modalStage.show();
    }
}

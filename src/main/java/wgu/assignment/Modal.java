package wgu.assignment;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
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
    private final Stage modalStage = new Stage();
    private final GridPane gridPane = new GridPane();
    private final Label messageLabel = new Label();
    private final Button yesButton = new Button ("Yes");
    private final Button noButton = new Button("No");

    /**
     * This constructor will create and render a message modal.
     * @param mode Indicates the message and title to display.
     */
    public Modal(ModalMode mode) {
        this.formatModal(mode);

        if(mode.equals(ModalMode.CONFIRM_EXIT)) {
            this.appendYesNoButtons();
            this.yesButton.setOnMouseClicked(e -> Platform.exit());
        } else {
            //Add "ok" button
            final Button okButton = new Button("OK");
            gridPane.add(okButton, 0, 1,2,1);
            GridPane.setHalignment(okButton, HPos.CENTER);
            okButton.setOnMouseClicked(e -> modalStage.close());
        }
        modalStage.show();
    }

    /**
     * This constructor will create confirmation modal related to the specified Part.
     * @param mode Indicates the message and title to display.
     * @param part Reference to the instance of the specified Part.
     */
    public Modal(ModalMode mode, Part part) {
        this.formatModal(mode);
        this.appendYesNoButtons();

        this.yesButton.setOnMouseClicked(e -> {
            if(!Inventory.deletePart(part)) {
                new Modal(ModalMode.DELETION_ERROR_ASSOCIATED_PARTS);
            }
            this.modalStage.close();
        });
        modalStage.show();
    }

    /**
     * This constructor will create a confirmation modal related to the specified Product.
     * @param mode Indicates the message and title to display.
     * @param product Reference to the instance of the specified Product.
     */
    public Modal(ModalMode mode, Product product) {
        this.formatModal(mode);
        this.appendYesNoButtons();

        this.yesButton.setOnMouseClicked(e -> {
            if(!Inventory.deleteProduct(product)){
                new Modal(ModalMode.DELETION_ERROR_UNKNOWN);
            }
            this.modalStage.close();
        });
        modalStage.show();
    }

    private void formatModal(ModalMode mode) {
        modalStage.setTitle(mode.getTitle());
        messageLabel.setText(mode.getMessage());

        //Stage properties
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UTILITY);

        //GridPane and properties
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(false);

        //Add label
        gridPane.add(messageLabel,0,0, 2, 1);

        //Create Scene and add to Stage
        modalStage.setScene(new Scene(gridPane));
    }

    private void appendYesNoButtons() {
        gridPane.add(yesButton, 0, 1);
        gridPane.add(noButton,1,1);
        GridPane.setHalignment(yesButton, HPos.CENTER);
        GridPane.setHalignment(noButton, HPos.CENTER);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col2);


        this.noButton.setOnMouseClicked(e -> this.modalStage.close());
    }
}

package wgu.assignment;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PartForm {
    private static Inventory inventory;
    private static Stage stage;
    private Part part;

    public PartForm() {
        this.inventory = InventoryControlApplication.inventory;
        this.stage = InventoryControlApplication.stage;
    }

    public PartForm(Part part) {
        this.part = part;
    }

    public Scene getScene() {
        return (this.part != null)? buildForm("modify") : buildForm("add");
    }

    private Scene buildForm(String method) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);

        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(50,50,50,50));
        gridPane.setHgap(50);

        //Create label
        Label titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(titleLabel, 0, 0);

        //Create radio buttons
        RadioButton inHouseRadio = new RadioButton("In-House");
        RadioButton outsourcedRadio = new RadioButton("Outsourced");

        //Create toggle group
        ToggleGroup radioGroup = new ToggleGroup();

        //Assign radio button to toggle group
        inHouseRadio.setToggleGroup(radioGroup);
        outsourcedRadio.setToggleGroup(radioGroup);

        //Add radio buttons to gridPane
        gridPane.add(inHouseRadio, 1, 0);
        gridPane.add(outsourcedRadio, 2, 0);

        //Build the actual form
        GridPane gridForm = new GridPane();
        gridForm.setGridLinesVisible(false);
        gridForm.setPadding(new Insets(40,20,20,20));
        gridForm.setHgap(20);
        gridForm.setVgap(20);

        //Add Labels
        gridForm.add(new Label("ID"), 0, 0);
        gridForm.add(new Label("Name"), 0, 1);
        gridForm.add(new Label("Inv"), 0, 2);
        gridForm.add(new Label("Price/Cost"), 0, 3);
        gridForm.add(new Label("Max"), 0, 4);
        gridForm.add(new Label("Min"), 2, 4);
        Label lastFieldLabel = new Label("Machine ID");
        lastFieldLabel.setMinWidth(100);
        gridForm.add(lastFieldLabel, 0, 5);

        //Create TextFields
        TextField idField = new TextField();
        idField.setPromptText("Auto- Gen - Disabled");
        TextField nameField = new TextField();
        TextField invField = new TextField();
        TextField priceField = new TextField();
        TextField maxField = new TextField();
        TextField minField = new TextField();
        TextField machineIdField = new TextField();

        //Add TextFields to GridPane
        gridForm.add(idField, 1, 0);
        gridForm.add(nameField, 1, 1);
        gridForm.add(invField, 1, 2);
        gridForm.add(priceField, 1, 3);
        gridForm.add(maxField, 1, 4);
        gridForm.add(minField, 3, 4);
        gridForm.add(machineIdField, 1, 5);

        //If for is in "modify" mode then we will populate the form with part data
        if(method == "modify") {
            titleLabel.setText("Modify Part");
        }
        else {
            titleLabel.setText("Add Part");
            inHouseRadio.setSelected(true);
        }

        //Create Buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        //cancelButton method
        MainForm mainForm = new MainForm();
        cancelButton.setOnAction(e -> stage.setScene(mainForm.getScene()));

        //saveButton method
        saveButton.setOnAction(e -> {
            int newItemId = 1;

            if(!inventory.getAllParts().isEmpty()) {
                newItemId = inventory.getAllParts().size() + 1;
            }

            if(radioGroup.getSelectedToggle() == inHouseRadio) {

                InHouse newPart = new InHouse(
                        newItemId, nameField.getText(), Double.parseDouble(priceField.getText()),
                        Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()),
                        Integer.parseInt(maxField.getText()), Integer.parseInt(machineIdField.getText()));

                inventory.addPart(newPart);
            }
            else {

                Outsourced newPart = new Outsourced(
                        newItemId, nameField.getText(), Double.parseDouble(priceField.getText()),
                        Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()),
                        Integer.parseInt(maxField.getText()), machineIdField.getText());

                inventory.addPart(newPart);

            }

            stage.setScene(mainForm.getScene());

        });

        //Add Buttons to GridPane
        gridForm.add(saveButton, 2, 6);
        gridForm.add(cancelButton, 3, 6);

        //Add gridForm to gridPane
        gridPane.add(gridForm, 0, 1, 3, 1);

        //Add listeners to the radio button group
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (t1 == inHouseRadio) {
                    lastFieldLabel.setText("Machine ID");
                }
                else {
                    lastFieldLabel.setText("Company Name");
                }
            }
        });

        return scene;
    }
}

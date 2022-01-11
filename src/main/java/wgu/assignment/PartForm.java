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
    private String mode;

    //Form elements
    private Label titleLabel = new Label();
    private Label partIdLabel = new Label("ID");
    private Label partNameLabel = new Label("Name");
    private Label partStockLabel = new Label("Inv");
    private Label partPriceLabel = new Label("Price/Cost");
    private Label partMaxLabel = new Label("Max");
    private Label partMinLabel = new Label("Min");
    private Label partMiscLabel = new Label();
    private TextField partIdField = new TextField();
    private TextField partNameField = new TextField();
    private TextField partStockField = new TextField();
    private TextField partPriceField = new TextField();
    private TextField partMaxField = new TextField();
    private TextField partMinField = new TextField();
    private TextField partMiscField = new TextField();
    private ToggleGroup partTypeToggleGroup = new ToggleGroup();
    private RadioButton inHouseRadioButton = new RadioButton("In-House");
    private RadioButton outsourcedRadioButton = new RadioButton("Outsourced");
    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public PartForm() {
        this.mode = "Add Part";
        this.setStage();
    }

    public PartForm(Part part) {
        this.mode = "Modify Part";
        this.part = part;
        this.setStage();
    }

    private void setStage() {
        this.inventory = InventoryControlApplication.inventory;
        this.stage = InventoryControlApplication.stage;
        this.titleLabel.setText(this.mode);
    }

    public Scene getScene() {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);

        //Format gridPane
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(50,50,50,50));
        gridPane.setHgap(50);

        //Format gridForm
        GridPane gridForm = new GridPane();
        gridForm.setGridLinesVisible(false);
        gridForm.setPadding(new Insets(40,20,20,20));
        gridForm.setHgap(20);
        gridForm.setVgap(20);

        //Create label
        this.titleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(this.titleLabel, 0, 0);

        //Assign radio button to toggle group
        this.inHouseRadioButton.setToggleGroup(this.partTypeToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(this.partTypeToggleGroup);

        //Add radio buttons to gridPane
        gridPane.add(this.inHouseRadioButton, 1, 0);
        gridPane.add(this.outsourcedRadioButton, 2, 0);

        //Add labels to the grid
        gridForm.add(this.partIdLabel, 0, 0);
        gridForm.add(this.partNameLabel, 0, 1);
        gridForm.add(this.partStockLabel, 0, 2);
        gridForm.add(this.partPriceLabel, 0, 3);
        gridForm.add(this.partMaxLabel, 0, 4);
        gridForm.add(this.partMinLabel, 2, 4);
        this.partMiscLabel.setMinWidth(100);
        gridForm.add(this.partMiscLabel, 0, 5);

        //Add TextFields to GridPane
        this.partIdField.setPromptText("Auto- Gen - Disabled");
        gridForm.add(this.partIdField, 1, 0);
        gridForm.add(this.partNameField, 1, 1);
        gridForm.add(this.partStockField, 1, 2);
        gridForm.add(this.partPriceField, 1, 3);
        gridForm.add(this.partMaxField, 1, 4);
        gridForm.add(this.partMinField, 3, 4);
        gridForm.add(this.partMiscField, 1, 5);

        //Assign button methods
        this.cancelButton.setOnAction(e -> this.cancelButtonMethod());
        this.saveButton.setOnAction((e -> this.saveButtonMethod()));

        //Add Buttons to GridPane
        gridForm.add(this.saveButton, 2, 6);
        gridForm.add(this.cancelButton, 3, 6);

        //Add gridForm to gridPane
        gridPane.add(gridForm, 0, 1, 3, 1);

        //Add listeners to the radio button group
        this.partTypeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                partMiscLabel.setText((t1 == inHouseRadioButton)? "Machine ID" : "Company Name");
            }
        });

        if(this.mode == "Modify Part") { this.insertPartData(); }
        else { this.inHouseRadioButton.setSelected(true); }

        return scene;
    }

    private void insertPartData() {
        this.partIdField.setText(Integer.toString(this.part.getId()));
        this.partNameField.setText(this.part.getName());
        this.partStockField.setText(Integer.toString(this.part.getStock()));
        this.partPriceField.setText(Double.toString((this.part.getPrice())));
        this.partMaxField.setText(Integer.toString(this.part.getMax()));
        this.partMinField.setText(Integer.toString(this.part.getMin()));

        //Determine part type, set radio button, change label, populate value
        if(this.part instanceof InHouse) {
            System.out.println("InHouse");
            this.inHouseRadioButton.setSelected(true);
            this.partMiscLabel.setText("Machine ID");
            this.partMiscField.setText(Integer.toString(((InHouse) this.part).getMachineId()));
        }
        else {
            this.outsourcedRadioButton.setSelected(true);
            this.partMiscLabel.setText("Company Name");
            this.partMiscField.setText(((Outsourced) this.part).getCompanyName());
        }
    }

    private void saveButtonMethod() {
        if(this.mode == "Add Part") {
            this.inventory.addPart(this.createPart());
        }
        else {
            this.inventory.updatePart(inventory.getAllParts().indexOf(this.part), this.createPart());
        }
        this.closeForm();
    }

    private void cancelButtonMethod() {
        this.closeForm();
    }

    private Part createPart() {
        int partId = (this.mode == "Add Part")? inventory.getAllParts().size() + 1 : this.part.getId();
        if(this.inHouseRadioButton.isSelected()) {
            return new InHouse(
                    partId,
                    this.partNameField.getText(),
                    Double.parseDouble(this.partPriceField.getText()),
                    Integer.parseInt(this.partStockField.getText()),
                    Integer.parseInt(this.partMinField.getText()),
                    Integer.parseInt(this.partMaxField.getText()),
                    Integer.parseInt(this.partMiscField.getText())
            );
        }
        else {
            return new Outsourced(
                    partId,
                    this.partNameField.getText(),
                    Double.parseDouble(this.partPriceField.getText()),
                    Integer.parseInt(this.partStockField.getText()),
                    Integer.parseInt(this.partMinField.getText()),
                    Integer.parseInt(this.partMaxField.getText()),
                    this.partMiscField.getText()
            );
        }
    }

    private void closeForm() {
        MainForm mainForm = new MainForm();
        stage.setScene(mainForm.getScene());
    }
}
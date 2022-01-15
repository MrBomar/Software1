package wgu.assignment;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

public class PartForm extends ItemForm {
    private Part part;

    //Form elements
    private final Label partMiscLabel = new Label();
    private final TextField partMiscField = new TextField();
    private final ToggleGroup partTypeToggleGroup = new ToggleGroup();
    private final RadioButton inHouseRadioButton = new RadioButton("In-House");
    private final RadioButton outsourcedRadioButton = new RadioButton("Outsourced");

    public PartForm() {
        super("Add Part", "Add Part", "Price/Cost");
        this.setStage();
    }

    public PartForm(Part part) {
        super("Modify Part", "Modify Part", "Price/Cost");
        this.part = part;
        this.setStage();
    }

    private void setStage() {
        //Assign radio button to toggle group
        this.inHouseRadioButton.setToggleGroup(this.partTypeToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(this.partTypeToggleGroup);

        //Add radio buttons to gridPane
        this.gridPane.add(this.inHouseRadioButton, 1, 0);
        this.gridPane.add(this.outsourcedRadioButton, 2, 0);

        //Add miscLabel and miscField
        this.partMiscLabel.setMinWidth(100);
        this.gridForm.add(this.partMiscLabel, 0, 5);
        this.gridForm.add(this.partMiscField, 1, 5);

        //Set idField prompt text
        this.idField.setPromptText("Auto- Gen - Disabled"); //Part specific element

        //Add listeners to the radio button group
        this.partTypeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                partMiscLabel.setText((t1 == inHouseRadioButton)? "Machine ID" : "Company Name");
            }
        });

        //Part specific element
        if(this.mode.equals("Modify Part")) { this.insertPartData(); }
        else { this.inHouseRadioButton.setSelected(true); }
    }

    private void insertPartData() {
        this.idField.setText(Integer.toString(this.part.getId()));
        this.nameField.setText(this.part.getName());
        this.stockField.setText(Integer.toString(this.part.getStock()));
        this.priceField.setText(Double.toString((this.part.getPrice())));
        this.maxField.setText(Integer.toString(this.part.getMax()));
        this.minField.setText(Integer.toString(this.part.getMin()));

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

    protected void saveButtonMethod() {
        if(this.mode.equals("Add Part")) {
            InventoryControlApplication.inventory.addPart(this.createPart());
        }
        else {
            this.inventory.updatePart(inventory.getAllParts().indexOf(this.part), this.createPart());
        }
        this.closeForm();
    }

    protected void cancelButtonMethod() {
        this.closeForm();
    }

    private Part createPart() {
        int partId = (this.mode.equals("Add Part"))? inventory.getAllParts().size() + 1 : this.part.getId();
        if(this.inHouseRadioButton.isSelected()) {
            return new InHouse(
                    partId,
                    this.nameField.getText(),
                    Double.parseDouble(this.priceField.getText()),
                    Integer.parseInt(this.stockField.getText()),
                    Integer.parseInt(this.minField.getText()),
                    Integer.parseInt(this.maxField.getText()),
                    Integer.parseInt(this.partMiscField.getText())
            );
        }
        else {
            return new Outsourced(
                    partId,
                    this.nameField.getText(),
                    Double.parseDouble(this.priceField.getText()),
                    Integer.parseInt(this.stockField.getText()),
                    Integer.parseInt(this.minField.getText()),
                    Integer.parseInt(this.maxField.getText()),
                    this.partMiscField.getText()
            );
        }
    }

    protected void closeForm() {
        MainForm mainForm = new MainForm();
        stage.setScene(mainForm.getScene());
    }
}
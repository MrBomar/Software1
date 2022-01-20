package wgu.assignment;

import javafx.scene.control.*;

public class PartForm extends ItemForm {
    private Part part;
    private int index;

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

    public PartForm(int index, Part part) {
        super("Modify Part", "Modify Part", "Price/Cost");
        this.part = part;
        this.index = index;
        this.setStage();
    }

    private void setStage() {
        //Assign radio button to toggle group
        this.inHouseRadioButton.setToggleGroup(this.partTypeToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(this.partTypeToggleGroup);

        //Add radio buttons to gridPane
        this.gridPane.add(this.inHouseRadioButton, 1, 0);
        this.gridPane.add(this.outsourcedRadioButton, 2, 0);

        //Add listeners to the radio button group
        this.partTypeToggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            partMiscLabel.setText((t1 == inHouseRadioButton)? "Machine ID" : "Company Name");
            if(part instanceof InHouse && t1 == inHouseRadioButton) {
                InHouse tempPart = (InHouse) part;
                partMiscField.setText("" + tempPart.getMachineId());
            } else if(part instanceof Outsourced && t1 == outsourcedRadioButton) {
                Outsourced tempPart = (Outsourced) part;
                partMiscField.setText(tempPart.getCompanyName());
            } else {
                partMiscField.setText("");
            }
        });

        //Detect mode of operation and load part data if required.
        if(this.mode.equals("Modify Part")) { this.insertPartData(); }
        else { this.inHouseRadioButton.setSelected(true); }

        //Add miscLabel and miscField
        this.partMiscLabel.setMinWidth(100);
        this.gridForm.add(this.partMiscLabel, 0, 5);
        this.gridForm.add(this.partMiscField, 1, 5);

        //Add Buttons to GridPane
        this.addButtonsToGridPane(); //Adds the default buttons to the pane.
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
        if(this.validatePartForm()) {
            if(this.mode.equals("Add Part")) {
                Inventory.addPart(this.createPart());
            }
            else {
                Inventory.updatePart(this.index, this.createPart());
            }
            this.closeForm();
        }
    }

    protected void cancelButtonMethod() {
        this.closeForm();
    }

    private Part createPart() {
        int partId = (this.mode.equals("Add Part"))? this.getNewPartId() : this.part.getId();
        if(this.inHouseRadioButton.isSelected()) {
            return new InHouse(
                    partId,
                    this.nameField.getText(),
                    this.getPriceDouble(),
                    this.getStockInt(),
                    this.getMinInt(),
                    this.getMaxInt(),
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

    protected int getNewPartId() {
        if(Inventory.getAllParts().isEmpty()) {
            return 1;
        }
        else {
            int listSize = Inventory.getAllParts().size();
            int lastId = Inventory.getAllParts().get(listSize - 1).getId();
            return lastId + 1;
        }
    }

    private boolean validatePartForm() {
        if(validateBeforeSave()) { //Basic validation passes
            if(this.inHouseRadioButton.isSelected()) {
                if(this.validInt(this.partMiscField.getText())) {
                   return true;
                }
                else {
                    InventoryControlApplication.appModal.displayMessage("Invalid Entry",
                            "The value entered in the 'Machine ID' field is invalid. Please enter an non-decimal numeric value.");
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
}
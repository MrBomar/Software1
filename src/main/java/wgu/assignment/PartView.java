package wgu.assignment;

import javafx.scene.control.*;
import static wgu.assignment.ViewMode.*;

/**
 * This class renders the JavaFX PartForm view.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class PartView extends ItemView {
    private Part part;
    private int index;
    private final Label partMiscLabel = new Label();
    private final TextField partMiscField = new TextField();
    private final ToggleGroup partTypeToggleGroup = new ToggleGroup();
    private final RadioButton inHouseRadioButton = new RadioButton("In-House");
    private final RadioButton outsourcedRadioButton = new RadioButton("Outsourced");

    /** Creates a blank Part form to create a new Part. */
    public PartView() {
        super(ADD_PART);
        this.setStage();
    }

    /**
     * Loads a Part form and the Part for modification.
     * @param index The index of the Part as stored in inventory.
     * @param part The reference to the Part in inventory.
     */
    public PartView(int index, Part part) {
        super(MODIFY_PART);
        this.part = part;
        this.index = index;
        this.setStage();
    }

    /** Sets the layout of the Part view. */
    protected void setStage() {
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
        if(this.mode.equals(MODIFY_PART)) { this.insertPartData(); }
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

    /**
     * This method saves the data from the Part form to the Part instance, and closes the form.
     * If this is a new part, this method will create a new Part populated with the data in the form.
     * If this is an existing part, then the Part instance data will be updated.
     */
    protected void saveButtonMethod() {
        if(this.validatePartForm()) {
            if(this.mode.equals(ADD_PART)) {
                Inventory.addPart(this.createPart());
            }
            else {
                Inventory.updatePart(this.index, this.createPart());
            }
            this.closeForm();
        }
    }

    private Part createPart() {
        int partId = (this.mode.equals(ADD_PART))? this.getNewPartId() : this.part.getId();
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

    private int getNewPartId() {
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
                    new Modal(ModalMode.INVALID_MACHINE_ID);
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
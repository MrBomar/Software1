package wgu.assignment;

import javafx.scene.layout.*;

public class ProductForm extends ItemForm {
    private Product product = null;
    private int index;
    private InventorySubForm partsSubForm;
    private InventorySubForm associatedPartSubForm;

    public ProductForm() {
        super("Add Product", "Add Product", "Price");
        this.product = new Product(this.getNewProductID(), "", 0, 0, 0, 0);
        this.setStage();
    }

    public ProductForm(int index, Product product) {
        super("Modify Product", "Modify Product", "Price");
        this.index = index;
        this.product = product;
        this.setStage();
        this.populateFields();
    }

    private void setStage() {
        this.pane.setMaxHeight(580);

        //Instantiate SubForms
        partsSubForm = new InventorySubForm(
                new PartsTableView(InventoryControlApplication.inventory.getAllParts()),
                "Available Part"
        );
        associatedPartSubForm = new InventorySubForm(
                new PartsTableView(product.getAllAssociatedParts()),
                "Associated Part"
        );

        //Adjust gridForm Position
        GridPane.setConstraints(this.gridForm, 0,1, 1, 2);

        //Add InventorySubForm - Parts
        this.gridPane.add(partsSubForm.getView(), 1,0, 7, 2);

        //Add InventorySubForm - Product parts list
        this.gridPane.add(associatedPartSubForm.getView(), 1, 2, 7,2);

        //Move the buttons from gridForm to gridPane
        this.gridForm.getChildren().remove(this.saveButton);
        this.gridForm.getChildren().remove(this.cancelButton);
        this.gridPane.add(this.saveButton, 6, 4, 1, 1);
        this.gridPane.add(this.cancelButton, 7,4,1, 1);
    }

    protected void saveButtonMethod() {
        if(this.validateBeforeSave()) {
            if(this.mode.equals("Add Product")) {
                inventory.addProduct(this.product);
            } else {
                inventory.updateProduct(this.index, this.product);
            }
            this.closeForm();
        }
    }

    protected void cancelButtonMethod() {
        //FIXME - This method needs to undo any changes made to the product then close out.
        InventoryControlApplication.stage.setScene(InventoryControlApplication.mainForm.getScene());
    }

    private int getNewProductID() {
        if(InventoryControlApplication.inventory.getAllProducts().isEmpty()){
            return 1;
        }
        else {
            int listSize = InventoryControlApplication.inventory.getAllProducts().size();
            int lastId = InventoryControlApplication.inventory.getAllProducts().get(listSize - 1).getId();
            return lastId + 1;
        }
    }

    private void populateFields() {
        this.idField.setText(Integer.toString(this.product.getId()));
        this.nameField.setText(this.product.getName());
        this.stockField.setText(Integer.toString(this.product.getStock()));
        this.priceField.setText(Double.toString(this.product.getPrice()));
        this.maxField.setText(Integer.toString(this.product.getMax()));
        this.minField.setText(Integer.toString(this.product.getMin()));
    }

    private boolean populateProduct() {
        //Data has already been validated
        this.product.setName(this.nameField.getText());
        this.product.setMax(this.getMaxInt());
        this.product.setMin(this.getMinInt());
        this.product.setStock(this.getStockInt());
        this.product.setPrice(this.getPriceDouble());
        //FIXME - Need to save associated parts to the product.
    }
}

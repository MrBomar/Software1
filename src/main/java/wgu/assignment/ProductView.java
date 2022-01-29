package wgu.assignment;

import javafx.scene.layout.*;
import static wgu.assignment.ViewMode.*;

/**
 * Renders the product view for product addition or modification.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class ProductView extends ItemView {
    private final Product newProduct = new Product(0, "", 0, 0, 0, 0);
    private Product oldProduct = null;
    private int index;

    /** Creates a blank Product form used to create a new Product. */
    public ProductView() {
        super(ADD_PRODUCT);
        this.newProduct.setId(this.getNewProductID());
        this.setStage();
    }

    /**
     * Loads a Product form and the Product for modification.
     * @param index The index of the Part as stored in inventory.
     * @param product The reference to the Product in inventory.
     */
    public ProductView(int index, Product product) {
        super(MODIFY_PRODUCT);
        this.newProduct.setId(product.getId());
        this.newProduct.setName(product.getName());
        this.newProduct.setPrice(product.getPrice());
        this.newProduct.setStock(product.getStock());
        this.newProduct.setMin(product.getMin());
        this.newProduct.setMax(product.getMax());
        this.index = index;
        this.oldProduct = product;
        this.setStage();
        this.populateFields();
    }

    /** Sets the layout of the Product view. */
    protected void setStage() {
        this.pane.setMaxHeight(580);

        //Instantiate SubForms
        InventorySubForm partsSubForm = new PartsInventorySubForm(
                Inventory.getAllParts(),
                this.newProduct,
                "All Part"
        );

        InventorySubForm associatedPartSubForm = new PartsInventorySubForm(
                this.newProduct.getAllAssociatedParts(),
                this.newProduct,
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

    /**
     * This method saves the data from the Product form to the Product instance, and closes the form.
     * If this is a new product, this method will create a new Product populated with the data in the form.
     * If this is an existing product, then the Product instance data will be updated.
     */
    protected void saveButtonMethod() {
        if(this.validateBeforeSave()) {
            this.updateProductInfo();
            if(this.mode.equals(ADD_PRODUCT)) {
                Inventory.addProduct(this.newProduct);
            } else {
                Inventory.updateProduct(this.index, this.newProduct);
            }
            this.closeForm();
        }
    }

    private int getNewProductID() {
        if(Inventory.getAllProducts().isEmpty()){
            return 1;
        }
        else {
            int listSize = Inventory.getAllProducts().size();
            int lastId = Inventory.getAllProducts().get(listSize - 1).getId();
            int newID = lastId + 1;
            System.out.println(newID);
            return newID;
        }
    }

    private void populateFields() {
        this.idField.setText(Integer.toString(this.oldProduct.getId()));
        this.nameField.setText(this.oldProduct.getName());
        this.stockField.setText(Integer.toString(this.oldProduct.getStock()));
        this.priceField.setText(Double.toString(this.oldProduct.getPrice()));
        this.maxField.setText(Integer.toString(this.oldProduct.getMax()));
        this.minField.setText(Integer.toString(this.oldProduct.getMin()));

        //Copy associated parts from oldProduct to newProduct
        for(Part part: this.oldProduct.getAllAssociatedParts()) {
            this.newProduct.addAssociatedPart(part);
        }
    }

    private void updateProductInfo() {
        if(this.validateBeforeSave()) {
            this.newProduct.setStock(this.getStockInt());
            this.newProduct.setPrice(this.getPriceDouble());
            this.newProduct.setName(this.nameField.getText());
            this.newProduct.setMin(this.getMinInt());
            this.newProduct.setMax(this.getMaxInt());
        }
    }

}

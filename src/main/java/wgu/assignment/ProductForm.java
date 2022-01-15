package wgu.assignment;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ProductForm extends ItemForm {
    private Product product;
    private InventorySubForm partsSubForm;
    private InventorySubForm associatedPartSubForm;

    public ProductForm() {
        super("Add Product", "Add Product", "Price");
        this.product = new Product(this.getNewProductID(), "", 0, 0, 0, 0);
        this.setStage();
    }

    public ProductForm(Product product) {
        super("Modify Product", "Modify Product", "Price");
        this.product = product;
        this.setStage();
    }

    private void setStage() {
        //Instantiate SubForms
        partsSubForm = new InventorySubForm(
                new PartsTableView(InventoryControlApplication.inventory.getAllParts()),
                "Available Parts"
        );
        associatedPartSubForm = new InventorySubForm(
                new PartsTableView(product.getAllAssociatedParts()),
                "Associated Parts"
        );

        //Adjust gridForm Position
        GridPane.setConstraints(this.gridForm, 0,1, 1, 2);

        //Add InventorySubForm - Parts
        //FIXME - This "Add" button needs to add the part to the associatedParts observable list.
        this.gridPane.add(partsSubForm.getView(), 1,0, 7, 2);

        //Add InventorySubForm - Product parts list
        //FIXME - This needs to be tied to the associatedParts observable list
        this.gridPane.add(associatedPartSubForm.getView(), 1, 2, 7,2);

        //Move the buttons from gridForm to gridPane
        this.gridForm.getChildren().remove(this.saveButton);
        this.gridForm.getChildren().remove(this.cancelButton);
        this.gridPane.add(this.saveButton, 6, 4, 1, 1);
        this.gridPane.add(this.cancelButton, 7,4,1, 1);

        //Additional formatting
        this.gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        this.gridPane.setPadding(new Insets(20,20,20,20));
    }

    protected void saveButtonMethod() {

    }

    protected void cancelButtonMethod() {
        //FIXME - This method needs to undo any changes made to the product then close out.
        InventoryControlApplication.stage.setScene(InventoryControlApplication.mainForm.getScene());
    }

    private int getNewProductID() {
        ObservableList<Product> allProducts = this.inventory.getAllProducts();
        if(allProducts.isEmpty()) {
            return 1;
        }
        else {
            int i = allProducts.size();
            return allProducts.get(i).getId() + 1;
        }
    }
}

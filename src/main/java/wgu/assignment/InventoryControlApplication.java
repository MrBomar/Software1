package wgu.assignment;

import javafx.application.Application;
import javafx.stage.Stage;

public class InventoryControlApplication extends Application {
    public static Modal appModal;
    public static Stage stage;
    public static MainForm mainForm;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.appModal = new Modal();

        //Set the first view
        this.mainForm = new MainForm();
        stage.setScene(this.mainForm.getScene());
        stage.show();

        if(true) {this.addTestInventory();} //Add inventory to the app for testing
    }

    public void addTestInventory() {
        Inventory.addPart(new Outsourced(1,"First Part", 1.99,5,2,10,"Acme Company"));
        Inventory.addPart(new Outsourced(2,"Second Part",2.99,2,1,5,"Part Inc."));
        Inventory.addPart(new InHouse(3,"Third Part",3.99,20,10,50,123));
        Inventory.addPart(new InHouse(4,"Fourth Part",4.99,100,50,110,456));
        Inventory.addProduct(new Product(1,"First Product",1.99,7,3,8));
        Inventory.addProduct(new Product(2,"Second Product",2.99,80,50,100));
        Inventory.getAllProducts().get(1).addAssociatedPart(Inventory.getAllParts().get(1));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
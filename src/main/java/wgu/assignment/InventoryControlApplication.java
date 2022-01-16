package wgu.assignment;

import javafx.application.Application;
import javafx.stage.Stage;

public class InventoryControlApplication extends Application {
    public static Inventory inventory = new Inventory();
    public static Modal appModal;
    public static Stage stage;
    public static MainForm mainForm;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.appModal = new Modal();

        //Set the first view
        this.mainForm = new MainForm();
        stage.setScene(mainForm.getScene());
        stage.show();

        if(true) {this.addTestInventory();} //Add inventory to the app for testing
    }

    public void addTestInventory() {
        this.inventory.addPart(new Outsourced(1,"First Part", 1.99,5,2,10,"Acme Company"));
        this.inventory.addPart(new Outsourced(2,"Second Part",2.99,2,1,5,"Part Inc."));
        this.inventory.addPart(new InHouse(3,"Third Part",3.99,20,10,50,123));
        this.inventory.addPart(new InHouse(4,"Fourth Part",4.99,100,50,110,456));
        this.inventory.addProduct(new Product(1,"First Product",1.99,7,3,8));
        this.inventory.addProduct(new Product(2,"Second Product",2.99,80,50,100));
        this.inventory.getAllProducts().get(1).addAssociatedPart(this.inventory.getAllParts().get(1));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
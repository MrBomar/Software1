package wgu.assignment;

import javafx.application.Application;
import javafx.stage.Stage;
import static wgu.assignment.Views.*;

/**
 * This class contains the entry point of the Inventory Control application.
 * <p>FUTURE ENHANCEMENT</p>
 * <p>I would give each part and product a location field. This would allow the user to more easily locate their inventory.</p>
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class InventoryControlApplication extends Application {
    /** The initial stage on which the views are rendered. */
    public static Stage stage;

    /**
     * This method sets the initial stage/view of the application.
     * @param appStage Supplied by the JavaFX framework.
     */
    @Override
    public void start(Stage appStage) {
        stage = appStage;
        changeView(MAIN);
    }

    /**
     * This method adds test InHouse and Outsources Parts, Products, and Inventory to the application.
     * To enable, add the word "test" to the command line when launching the application.
     */
    public static void addTestInventory() {
        Inventory.addPart(new Outsourced(1,"First Part", 1.99,5,2,10,"Acme Company"));
        Inventory.addPart(new Outsourced(2,"Second Part",2.99,2,1,5,"Part Inc."));
        Inventory.addPart(new InHouse(3,"Third Part",3.99,20,10,50,123));
        Inventory.addPart(new InHouse(4,"Fourth Part",4.99,100,50,110,456));
        Inventory.addProduct(new Product(1,"First Product",1.99,7,3,8));
        Inventory.addProduct(new Product(2,"Second Product",2.99,80,50,100));
        Inventory.getAllProducts().get(1).addAssociatedPart(Inventory.getAllParts().get(1));
        Inventory.getAllProducts().get(1).addAssociatedPart(Inventory.getAllParts().get(2));
    }

    /**
     * This method switches the view of the application.
     * This method only displays the default views.
     * @param view Indicates which view to display using the Enum Views
     */
    public static void changeView(Views view){
        switch(view) {
            case PRODUCT:
                ProductView productView = new ProductView();
                stage.setScene(productView.getScene());
                break;
            case PART:
                PartView partView = new PartView();
                stage.setScene(partView.getScene());
                break;
            case MAIN:
                MainView mainView = new MainView();
                stage.setScene(mainView.getScene());
                break;
            default:

                stage.close();
        }
        stage.show();
    }

    /**
     * This method loads the Part editing view.
     * @param partIndex The index value of the Part in inventory.
     * @param part The original instance of the Part to be modified.
     */
    public static void changeView(int partIndex, Part part) {
        PartView partView = new PartView(partIndex, part);
        stage.setScene(partView.getScene());
    }

    /**
     * This method loads the Product editing view.
     * @param productIndex The index value of the Product in inventory.
     * @param product The original instance of the Product to be modified.
     */
    public static void changeView(int productIndex, Product product) {
        ProductView productView = new ProductView(productIndex, product);
        stage.setScene(productView.getScene());
    }

    /**
     * This method is the entry point of the application.
     * To load test data, launch this application with the parameter 'test'
     * @param args This is a string array created from parameters passed at the command line.
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            if(args[0].equals("test")) { addTestInventory();}
        }
        launch(args);
    }
}
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
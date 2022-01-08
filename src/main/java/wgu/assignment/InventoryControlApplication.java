package wgu.assignment;

import javafx.application.Application;
import javafx.stage.Stage;

public class InventoryControlApplication extends Application {
    public static Inventory inventory = new Inventory();
    public static Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        //Set the first view
        MainForm mainForm = new MainForm();
        stage.setScene(mainForm.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
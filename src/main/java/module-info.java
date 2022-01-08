module wgu.assignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens wgu.assignment to javafx.fxml;
    exports wgu.assignment;
}
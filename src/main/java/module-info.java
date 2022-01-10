module wgu.assignment {
    requires javafx.fxml;
    requires  javafx.controls;

    opens wgu.assignment to javafx.graphics;

    exports wgu.assignment;
}
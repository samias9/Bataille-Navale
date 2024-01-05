module com.example.bataillenavalevf {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    opens controller to javafx.fxml;
    exports controller;

}
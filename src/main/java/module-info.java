module morpion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens morpion to javafx.fxml;
    exports morpion;
    exports morpion.controller;
    opens morpion.controller to javafx.fxml;
}
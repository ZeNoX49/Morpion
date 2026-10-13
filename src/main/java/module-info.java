module morpion {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens morpion to javafx.fxml;
    opens morpion.controller to javafx.fxml;

    exports morpion;
    exports morpion.controller;
}
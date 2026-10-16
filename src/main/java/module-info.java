module morpion {
    requires javafx.fxml;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    opens morpion to javafx.fxml;
    opens morpion.controller to javafx.fxml;
    opens morpion.player to javafx.fxml;

    exports morpion;
    exports morpion.controller;
    exports morpion.player;
}
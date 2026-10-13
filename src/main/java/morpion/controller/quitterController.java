package morpion.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class quitterController {

    @FXML
    void leave(ActionEvent event) {
        Platform.exit(); // Ferme toute l'application
    }

    @FXML
    void stay(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
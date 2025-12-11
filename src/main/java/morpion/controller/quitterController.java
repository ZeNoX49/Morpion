package morpion.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class quitterController {

    @FXML
    void Leave(ActionEvent event) {
        Platform.exit(); // Ferme toute l'application
    }

    @FXML
    void Stay(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
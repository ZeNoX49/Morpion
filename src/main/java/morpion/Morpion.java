package morpion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import morpion.controller.MorpionController;

public class Morpion extends Application {

    private static Stage commonStage;

    @Override
    public void start(Stage stage) {
        Morpion.commonStage = stage;

        // Intercepter la fermeture de la fenêtre
        stage.setOnCloseRequest(e -> {
            e.consume(); // Empêche la fermeture automatique
            Morpion.launchQuitter();
        });

        Morpion.launchParametre();
    }

    public static void launchParametre() {
        Morpion.showStage(null, "fxml/parametre.fxml", 1067, 600, Morpion.commonStage, "Paramètre");
    }

    public static void launchMorpion(MorpionController mc) {
        Morpion.showStage(mc, "fxml/morpion.fxml", 1067, 600, Morpion.commonStage, "Morpion");
    }

    public static void launchRegle() {
        Morpion.showStage(null, "fxml/regle.fxml", 600, 400, new Stage(), "Règles du jeu");
    }

    public static void launchQuitter() {
        Morpion.showStage(null, "fxml/quitter.fxml", 400, 250, new Stage(), "Quitter ?");
    }

    private static <T> void showStage(T controller, String file, int sizeX, int sizeY, Stage stage, String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource(file));
            Scene scene = new Scene(fxmlLoader.load(), sizeX, sizeY);
            if (controller != null) {
                fxmlLoader.setController(controller);
            }
            stage.setTitle(name);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Morpion.launch();
    }

}

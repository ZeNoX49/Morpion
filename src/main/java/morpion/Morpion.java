package morpion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import morpion.controller.morpionController;
import morpion.controller.parametreController;

public class Morpion extends Application {
    private static Stage commonStage;
    private static parametreController pc;
    private static morpionController mc;

    @Override
    public void start(Stage stage) throws IOException {
        pc = new parametreController();
        mc = new morpionController();
        commonStage = stage;

        // Intercepter la fermeture de la fenêtre
        stage.setOnCloseRequest(event -> {
            event.consume(); // Empêche la fermeture automatique
            try {
                launchQuitter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        launchParametre();
    }

    public static morpionController getMc() {
        return mc;
    }

    public static void launchParametre() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("fxml/parametre.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1067, 600);
        commonStage.setTitle("Paramètre");
        commonStage.setScene(scene);
        commonStage.show();
        pc.initialize();
    }

    public static void launchMorpion() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("fxml/morpion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1067, 600);
        commonStage.setTitle("Morpion");
        commonStage.setScene(scene);
        commonStage.show();
        mc.initialize();
    }

    public static void launchRegle() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("fxml/regle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Règles du jeu");
        stage.setScene(scene);
        stage.show();
    }

    public static void launchQuitter() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("fxml/quitter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);
        Stage stage = new Stage();
        stage.setTitle("Quitter ?");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}
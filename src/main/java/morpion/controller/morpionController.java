package morpion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.animation.PauseTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import morpion.Joueur;
import morpion.Morpion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class  morpionController {

    @FXML private Label Label_Resultat = new Label();
    @FXML private Label Player1_Score = new Label();
    @FXML private Label Player1_TextScore = new Label();
    @FXML private Label Player2_Score = new Label();
    @FXML private Label Player2_TextScore = new Label();
    @FXML private Label Player_Turn = new Label();

    @FXML private Button button0 = new Button();
    @FXML private Button button1 = new Button();
    @FXML private Button button2 = new Button();
    @FXML private Button button3 = new Button();
    @FXML private Button button4 = new Button();
    @FXML private Button button5 = new Button();
    @FXML private Button button6 = new Button();
    @FXML private Button button7 = new Button();
    @FXML private Button button8 = new Button();
    private List<Button> allButton;

    @FXML private ImageView image0 = new ImageView();
    @FXML private ImageView image1 = new ImageView();
    @FXML private ImageView image2 = new ImageView();
    @FXML private ImageView image3 = new ImageView();
    @FXML private ImageView image4 = new ImageView();
    @FXML private ImageView image5 = new ImageView();
    @FXML private ImageView image6 = new ImageView();
    @FXML private ImageView image7 = new ImageView();
    @FXML private ImageView image8 = new ImageView();
    private HashMap<Button, ImageView> imageOfbutton;
;
    private static Joueur joueur1;
    private static Joueur joueur2;
    private static Joueur joueurActuel;

    private static boolean playAlone;
    private static boolean IALevelNormal;

    private HashMap<Joueur, ArrayList<Button>> buttonOfPlayer;
    private int nbTour;

    // pour créer une instance
    public morpionController() {
    }

    public void initialize() throws InterruptedException {
        nbTour = 0;
        Label_Resultat.setText("");

        allButton = new ArrayList<>();
        allButton.add(button0);
        allButton.add(button1);
        allButton.add(button2);
        allButton.add(button3);
        allButton.add(button4);
        allButton.add(button5);
        allButton.add(button6);
        allButton.add(button7);
        allButton.add(button8);

        for (Button b : allButton) {
            b.setStyle(
                "-fx-opacity: 1;" +
                "-fx-background-color: transparent;" +
                "-fx-effect: none;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-border-color: transparent;"
            );
        }

        imageOfbutton = new HashMap<>();
        imageOfbutton.put(button0, image0);
        imageOfbutton.put(button1, image1);
        imageOfbutton.put(button2, image2);
        imageOfbutton.put(button3, image3);
        imageOfbutton.put(button4, image4);
        imageOfbutton.put(button5, image5);
        imageOfbutton.put(button6, image6);
        imageOfbutton.put(button7, image7);
        imageOfbutton.put(button8, image8);

        buttonOfPlayer = new HashMap<>();
        buttonOfPlayer.put(joueur1, new ArrayList<>());
        buttonOfPlayer.put(joueur2, new ArrayList<>());

        Player1_TextScore.setText("Score de " + joueur1.getNom());
        Player2_TextScore.setText("Score de " + joueur2.getNom());
        Player_Turn.setText(joueurActuel.getNom());

        if (joueurActuel.equals(joueur2) && isPlayingAlone()) {
            jouer(IAPlay());
        }
    }

    public void setJoueur1(Joueur joueur) { joueur1 = joueur; }
    public void setJoueur2(Joueur joueur) { joueur2 = joueur; }
    public void setJoueurActuel(Joueur joueur) { joueurActuel = joueur; }
    public void setPlayAlone(boolean b) { playAlone = b; }
    public void setIALevelNormal(boolean b) { IALevelNormal = b; }

    public boolean isPlayingAlone() { return playAlone; }
    public boolean isIALevelNormal() { return IALevelNormal; }

    @FXML
    void cliqued(ActionEvent event) throws InterruptedException {
        if (joueurActuel.equals(joueur1) || !isPlayingAlone()) {
            Button button = (Button) event.getSource();
            jouer(button);
        }
    }

    private void jouer(Button button) throws InterruptedException {
        buttonOfPlayer.get(joueurActuel).add(button);
        button.setDisable(true);

        if (joueurActuel.isChoixCaractere()) {
            button.setText(joueurActuel.getCaractere());
            button.setFont(joueurActuel.getFont());
            button.setStyle(joueurActuel.getColor());
        }
        else {
            button.setGraphic(joueurActuel.getImage());
        }
        // Créer un effet de désaturation
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
        imageOfbutton.get(button).setEffect(monochrome);

        finDePartie();

        changePlayer();
    }

    private void changePlayer() throws InterruptedException {
        joueurActuel = joueurActuel.equals(joueur1) ? joueur2 : joueur1;
        Player_Turn.setText(joueurActuel.getNom());
    }

    private Button IAPlay() {
        Button b = null;

        if (isIALevelNormal()) {
            // si l'IA à un niveau normal, on l'a fait jouer aléatoirement
            while (true) {
                Random r = new Random();
                b = allButton.get(r.nextInt(9));
                if (!b.isDisable()) {
                    return b;
                }
            }
        }
        else {
            // on vérifie si l'IA peut gagner
            b = canWin(joueur2);
            if (b != null) {
                return b;
            }

            // si l'IA ne peut pas gagner, on vérifie si le joueur peut gagner
            b = canWin(joueur1);
            if (b != null) {
                return b;
            }

            // si personne ne peut gagner l'IA joue
            while (true) {
                Random r = new Random();
                b = allButton.get(r.nextInt(9));
                if (!b.isDisable()) {
                    return b;
                }
            }
        }
    }

    private Button canWin(Joueur j) {
        ArrayList<Button> lButton = buttonOfPlayer.get(j);
        // on vérifie si l'IA peut gagner
        if (!button0.isDisable() && (
                        (lButton.contains(button1) && lButton.contains(button2)) ||
                        (lButton.contains(button3) && lButton.contains(button6)) ||
                        (lButton.contains(button4) && lButton.contains(button8)))) {
            return button0;
        }
        else if (!button1.isDisable() && (
                        (lButton.contains(button0) && lButton.contains(button2)) ||
                        (lButton.contains(button4) && lButton.contains(button7)))) {
            return button1;
        }
        else if (!button2.isDisable() && (
                        (lButton.contains(button0) && lButton.contains(button1)) ||
                        (lButton.contains(button5) && lButton.contains(button8)) ||
                        (lButton.contains(button4) && lButton.contains(button6)))) {
            return button2;
        }
        else if (!button3.isDisable() && (
                        (lButton.contains(button0) && lButton.contains(button6)) ||
                        (lButton.contains(button4) && lButton.contains(button5)))) {
            return button3;
        }
        else if (!button4.isDisable() && (
                        (lButton.contains(button0) && lButton.contains(button8)) ||
                        (lButton.contains(button1) && lButton.contains(button7)) ||
                        (lButton.contains(button2) && lButton.contains(button6)) ||
                        (lButton.contains(button3) && lButton.contains(button5)))) {
            return button4;
        }
        else if (!button5.isDisable() && (
                        (lButton.contains(button2) && lButton.contains(button8)) ||
                        (lButton.contains(button3) && lButton.contains(button4)))) {
            return button5;
        }
        else if (!button6.isDisable() && (
                        (lButton.contains(button0) && lButton.contains(button3)) ||
                        (lButton.contains(button2) && lButton.contains(button4)) ||
                        (lButton.contains(button7) && lButton.contains(button8)))) {
            return button6;
        }
        else if (!button7.isDisable() && (
                        (lButton.contains(button1) && lButton.contains(button4)) ||
                        (lButton.contains(button6) && lButton.contains(button8)))) {
            return button7;
        }
        else if (!button8.isDisable() && (
                        (lButton.contains(button0) && lButton.contains(button4)) ||
                        (lButton.contains(button2) && lButton.contains(button5)) ||
                        (lButton.contains(button6) && lButton.contains(button7)))) {
            return button8;
        }
        return null;
    }

    public boolean partieGagnee() {
        ArrayList<Button> lButton = buttonOfPlayer.get(joueurActuel);

        // Ligne
        if (lButton.contains(button0) && lButton.contains(button1) && lButton.contains(button2)) {
            boutonVictoire(new Button[] {button0, button1, button2});
            return true;
        }
        else if (lButton.contains(button3) && lButton.contains(button4) && lButton.contains(button5)) {
            boutonVictoire(new Button[] {button3, button4, button5});
            return true;
        }
        else if (lButton.contains(button6) && lButton.contains(button7) && lButton.contains(button8)) {
            boutonVictoire(new Button[] {button6, button7, button8});
            return true;
        }

        // Colone
        else if (lButton.contains(button0) && lButton.contains(button3) && lButton.contains(button6)) {
            boutonVictoire(new Button[] {button0, button3, button6});
            return true;
        }
        else if (lButton.contains(button1) && lButton.contains(button4) && lButton.contains(button7)) {
            boutonVictoire(new Button[] {button1, button4, button7});
            return true;
        }
        else if (lButton.contains(button2) && lButton.contains(button5) && lButton.contains(button8)) {
            boutonVictoire(new Button[] {button2, button5, button8});
            return true;
        }

        // Diagonales
        else if (lButton.contains(button0) && lButton.contains(button4) && lButton.contains(button8)) {
            boutonVictoire(new Button[] {button0, button4, button8});
            return true;
        }
        else if (lButton.contains(button2) && lButton.contains(button4) && lButton.contains(button6)) {
            boutonVictoire(new Button[] {button2, button4, button6});
            return true;
        }

        return false;
    }

    private void boutonVictoire(Button[] lButton) {
        for (Button b : allButton) {
            b.setDisable(true);
            // Créer un effet de désaturation
            ColorAdjust monochrome = new ColorAdjust();
            monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
            imageOfbutton.get(b).setEffect(monochrome);
        }
        for (int i=0; i<3; i++) {
            lButton[i].setStyle("-fx-background-color: #efc905;");
        }
        Label_Resultat.setText("Victoire de " + joueurActuel.getNom());
    }

    public void resetGame(int time) throws InterruptedException {
        nbTour = 0;

        PauseTransition pause = new PauseTransition(Duration.seconds(time));
        pause.setOnFinished(event -> {
            // pour éviter les bugs avec la fin de partie
            for (Button button : allButton) {
                button.setDisable(false);
                button.setText("");
                button.setGraphic(null);
                button.setStyle(
                    "-fx-opacity: 1;" +
                    "-fx-background-color: transparent;" +
                    "-fx-effect: none;" +
                    "-fx-focus-color: transparent;" +
                    "-fx-faint-focus-color: transparent;" +
                    "-fx-border-color: transparent;"
                );

                // enlève l'effet de désaturation
                imageOfbutton.get(button).setEffect(null);
            }
            buttonOfPlayer.get(joueur1).clear();
            buttonOfPlayer.get(joueur2).clear();
            Label_Resultat.setText("");
        });
        pause.play();
    }

    public void finDePartie() throws InterruptedException {
        nbTour++;

        if (partieGagnee()) {
            joueurActuel.incScore();

            Player1_Score.setText(String.valueOf(joueur1.getScore()));
            Player2_Score.setText(String.valueOf(joueur2.getScore()));

            resetGame(3);
        }

        // vérifie les égalités
        else {
            if (nbTour == 9) {
                Label_Resultat.setText("Egalite");
                resetGame(1);
            }
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            // pour éviter les bugs avec la fin de partie
            // si on est au tour du joueur 2, on vérifie si est une IA
            if (joueurActuel.equals(joueur2) && isPlayingAlone()) {
                try {
                    jouer(IAPlay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pause.play();
    }

    @FXML
    void MB_Leave(ActionEvent event) throws IOException {
        Morpion.launchQuitter();
    }

    @FXML
    void MB_Parameter(ActionEvent event) throws IOException {
        Morpion.launchParametre();
    }

    @FXML
    void MB_Reset(ActionEvent event) throws InterruptedException {
        resetGame(0);
        Player1_Score.setText("0");
        Player2_Score.setText("0");
        joueur1.resetScore();
        joueur2.resetScore();
    }

    @FXML
    void MB_Rules(ActionEvent event) throws IOException {
        Morpion.launchRegle();
    }

}
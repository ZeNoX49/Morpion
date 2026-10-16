package morpion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.animation.PauseTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import morpion.Morpion;
import morpion.player.APlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MorpionController {

    private static final String[][] WINNING_LINES = {
        {"00", "01", "02"},
        {"10", "11", "12"},
        {"20", "21", "22"},
        {"00", "10", "20"},
        {"01", "11", "21"},
        {"02", "12", "22"},
        {"00", "11", "22"},
        {"02", "11", "20"}
    };

    @FXML private Label player1TextScore;
    @FXML private Label player1Score;
    @FXML private Label playerTurn;
    @FXML private Label player2TextScore;
    @FXML private Label player2Score;
    @FXML private Label labelResultat;

    // 00 10 20
    // 01 11 21
    // 02 12 22
    @FXML private ImageView image00;
    @FXML private Button button00;
    @FXML private ImageView image10;
    @FXML private Button button10;
    @FXML private ImageView image20;
    @FXML private Button button20;
    @FXML private ImageView image01;
    @FXML private Button button01;
    @FXML private ImageView image11;
    @FXML private Button button11;
    @FXML private ImageView image21;
    @FXML private Button button21;
    @FXML private ImageView image02;
    @FXML private Button button02;
    @FXML private ImageView image12;
    @FXML private Button button12;
    @FXML private ImageView image22;
    @FXML private Button button22;

    private final Map<String, Button> buttons;
    private final Map<String, ImageView> images;
    private final List<String> playableBox;

    private final APlayer player1;
    private final APlayer player2;
    private APlayer currentPlayer;

    private final boolean isPlayingAlone;
    private final boolean isIaLevelNormal;

    private final HashMap<APlayer, List<String>> playerClickedBox;
    private int nbRound;

    public MorpionController(APlayer player1, APlayer player2, int currentPlayerNum, int nbPlayer, String iaLevel) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = currentPlayerNum == 1 ? player1 : player2;

        this.isPlayingAlone = nbPlayer == 1;
        this.isIaLevelNormal = iaLevel == "normal";

        this.buttons = new HashMap<>();
        this.images = new HashMap<>();
        this.playableBox = new ArrayList<>();

        this.playerClickedBox = new HashMap<>();
        this.playerClickedBox.put(player1, new ArrayList<>());
        this.playerClickedBox.put(player2, new ArrayList<>());

        this.nbRound = 0;
    }

    public void initialize() {
        this.labelResultat.setText("");

        this.player1TextScore.setText("Score de " + this.player1.getName());
        this.player2TextScore.setText("Score de " + this.player2.getName());
        this.playerTurn.setText(this.currentPlayer.getName());

        this.setupBox("00", this.button00, this.image00);
        this.setupBox("10", this.button10, this.image10);
        this.setupBox("20", this.button20, this.image20);
        this.setupBox("01", this.button01, this.image01);
        this.setupBox("11", this.button11, this.image11);
        this.setupBox("21", this.button21, this.image21);
        this.setupBox("02", this.button02, this.image02);
        this.setupBox("12", this.button12, this.image12);
        this.setupBox("22", this.button22, this.image22);

        if (this.currentPlayer.equals(this.player2) && this.isPlayingAlone) {
            this.jouer(this.getIaPlay());
        }
    }

    private void setupBox(String boxValue, Button button, ImageView iv) {
        this.buttons.put(boxValue, button);
        this.images.put(boxValue, iv);
        button.setOnAction(e -> this.play(boxValue));
        this.playableBox.add(boxValue);
    }

    private void play(String boxValue) {
        this.playableBox.remove(boxValue);
        this.playerClickedBox.get(this.currentPlayer).add(boxValue);

        Button button = this.buttons.get(boxValue);
        button.setDisable(true);
        this.currentPlayer.fillClickedButton(button);

        // Créer un effet de désaturation
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
        this.images.get(boxValue).setEffect(monochrome);

        this.nextRound();
    }

    public void nextRound() {
        this.nbTour++;

        List<String> winningBox = this.hasGameBeenWon();
        if (winningBox != null) {
            this.currentPlayer.incScore();
            this.boutonVictoire(winningBox);

            this.player1Score.setText(String.valueOf(this.player1.getScore()));
            this.player2Score.setText(String.valueOf(this.player2.getScore()));

            this.startNewGame();
        }  else if (nbTour == 9) {   // vérifie les égalités
            this.labelResultat.setText("Egalite");
            this.startNewGame();
        }

        this.updateCurrentPlayer();

        if (this.currentPlayer.equals(this.player2) && this.isPlayingAlone) {
            this.jouer(this.getIaPlay());
        }
    }

    private void updateCurrentPlayer() {
        this.currentPlayer = this.currentPlayer.equals(this.player1) ? this.player2 : this.player1;
        this.playerTurn.setText(this.currentPlayer.getName());
    }

    public List<String> hasGameBeenWon() {
        List<String> clickedBox = this.playerClickedBox.get(this.currentPlayer);
        List<String> winningBox = new ArrayList<>();

        for (String[] line : MorpionController.WINNING_LINES) {
            winningBox.clear();

            for (String box : line) {
                if (!clickedBox.contains(box)) {
                    break;
                }
                winningBox.add(box);
            }
    
            if (winningBox.size() == 3) {
                return winningBox;
            }
        }

        return null;
    }

    private String getIaPlay() {
        if (this.isIaLevelNormal) {
            // si l'IA à un niveau normal, on l'a fait jouer aléatoirement
            return this.playableBox.get(new Random().nextInt(this.playableBox.size()));
        }
        
        // on vérifie si l'IA peut gagner
        String winBox = this.canWin(this.player2);
        if (winBox != null) {
            return winBox;
        }

        // si l'IA ne peut pas gagner, on vérifie si le joueur peut gagner
        String loseBox = this.canWin(this.player1);
        if (loseBox != null) {
            return loseBox;
        }

        // si personne ne peut gagner l'IA joue
        return this.playableBox.get(new Random().nextInt(this.playableBox.size()));
    }

    private Button canWin(APlayer player) {
        List<String> clickedBox = this.playerClickedBox.get(player);
    
        for (String[] line : MorpionController.WINNING_LINES) {
            int count = 0;
            String missingBox = null;
    
            for (String box : line) {
                if (clickedBox.contains(box)) {
                    count++;
                } else {
                    missingBox = box;
                }
            }
    
            if (count == 2 && this.playableBox.contains(missingBox)) {
                return missingBox;
            }
        }

        return null;
    }

    private void boutonVictoire(List<String> winningBox) {
        for (String box : this.buttons.keySet()) {
            this.buttons.get(box).setDisable(true);

            // Créer un effet de désaturation
            ColorAdjust monochrome = new ColorAdjust();
            monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
            this.images.get(box).setEffect(monochrome);
        }

        for (String box : winningBox) {
            this.buttons.get(box).setStyle("-fx-background-color: #efc905;");
        }

        this.labelResultat.setText("Victoire de " + this.currentPlayer.getName());
    }

    public void startNewGame(int time) {
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {
            this.nbTour = 0;
            this.labelResultat.setText("");
    
            this.playerClickedBox.get(this.player1).clear();
            this.playerClickedBox.get(this.player2).clear();
            this.playableBox.clear();
            this.playableBox.addAll("00", "10", "20", "01", "11", "21", "02", "12", "22");
            
            for (String box : this.playableBox) {
                Button b = this.buttons.get(box);
                button.setDisable(false);
                button.setText("");
                button.setGraphic(null);
                button.setStyle("-fx-background-color: transparent;");

                // enlève l'effet de désaturation
                this.images.get(box).setEffect(null);
            }
        });
        pause.play();
    }

    @FXML
    void openParameterWindow(ActionEvent event) {
        Morpion.launchParametre();
    }

    @FXML
    void resetGame(ActionEvent event) {
        this.startNewGame();
        this.player1Score.setText("0");
        this.player2Score.setText("0");
        this.player1.resetScore();
        this.player2.resetScore();
    }

    @FXML
    void openRulesWindow(ActionEvent event) {
        Morpion.launchRegle();
    }

    @FXML
    public void openLeaveWindow(ActionEvent event) {
        Morpion.launchQuitter();
    }

}
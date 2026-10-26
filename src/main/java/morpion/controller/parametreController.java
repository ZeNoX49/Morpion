package morpion.controller;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import morpion.Morpion;
import morpion.player.APlayer;
import morpion.player.PlayerImage;
import morpion.player.PlayerText;

public class ParametreController {

    private static enum FirstTurn {
        J1, J2, ALEA;
    }

    // Joueur 1
    @FXML private TextField player1Name;
    @FXML private Button player1BtnCaractere;
    @FXML private Button player1BtnImage;
    @FXML private TextField player1Caractere;
    @FXML private ComboBox<String> player1Police;
    @FXML private TextField player1TaillePolice;
    @FXML private ColorPicker player1CouleurPolice;
    @FXML private TextField player1Image;

    // Joueur 2
    @FXML private TextField player2Name;
    @FXML private Button player2BtnCaractere;
    @FXML private Button player2BtnImage;
    @FXML private TextField player2Caractere;
    @FXML private ComboBox<String> player2Police;
    @FXML private TextField player2TaillePolice;
    @FXML private ColorPicker player2CouleurPolice;
    @FXML private TextField player2Image;

    // Nombre de joueur
    @FXML private Button btnNbPlayer1;
    @FXML private Button btnNbPlayer2;

    // Niveau de l'IA
    @FXML private Button btnIALevelNormal;
    @FXML private Button btnIALevelAdvanced;

    // Premier joueur a joué
    @FXML private Button btnFirstTurnPlayer1;
    @FXML private Button btnFirstTurnPlayer2;
    @FXML private Button btnFirstTurnRandom;

    // Image
    @FXML private ImageView imageNbPlayer1;
    @FXML private ImageView imageNbPlayer21;
    @FXML private ImageView imageNbPlayer22;
    @FXML private ImageView imageIALevelNormal;
    @FXML private ImageView imageIALevelAdvanced;
    @FXML private ImageView imageFirstTurnJ1;
    @FXML private ImageView imageFirstTurnAlea;
    @FXML private ImageView imageFirstTurnJ2;

    private int nbPlayer;
    private String iaLevel;
    private FirstTurn firstPlayer;
    private String lastPushDesignBtnPlayer2;

    public void initialize() {
        this.player1Police.getItems().addAll(Font.getFamilies());
        this.player2Police.getItems().addAll(Font.getFamilies());

        this.btnNbPlayer1.setOnAction(e -> this.updatePlayerNumber(1));
        this.btnNbPlayer2.setOnAction(e -> this.updatePlayerNumber(2));
        this.btnIALevelNormal.setOnAction(e -> this.updateIaLevel("normal"));
        this.btnIALevelAdvanced.setOnAction(e -> this.updateIaLevel("advanced"));
        this.btnFirstTurnPlayer1.setOnAction(e -> this.updateFirstTurn(FirstTurn.J1));
        this.btnFirstTurnPlayer2.setOnAction(e -> this.updateFirstTurn(FirstTurn.J2));
        this.btnFirstTurnRandom.setOnAction(e -> this.updateFirstTurn(FirstTurn.ALEA));
        this.player1BtnCaractere.setOnAction(e -> this.updatePlayer1Personnalization("caractere"));
        this.player1BtnImage.setOnAction(e -> this.updatePlayer1Personnalization("image"));
        this.player2BtnCaractere.setOnAction(e -> this.updatePlayer2Personnalization("caractere"));
        this.player2BtnImage.setOnAction(e -> this.updatePlayer2Personnalization("image"));
        
        this.lastPushDesignBtnPlayer2 = "image";
        this.updatePlayerNumber(2);
        this.updateIaLevel("normal");
        this.updateFirstTurn(FirstTurn.J1);
        this.updatePlayer1Personnalization("caractere");
        this.updatePlayer2Personnalization("image");
    }

    private void updatePlayerNumber(int nbPlayer) {
        this.nbPlayer = nbPlayer;
        
        if (nbPlayer == 1) {
            this.btnNbPlayer1.setDisable(true);
            this.btnNbPlayer2.setDisable(false);

            // désactiver tout les paramètres de player2
            this.player2Name.setDisable(true);
            this.player2BtnCaractere.setDisable(true);
            this.player2Caractere.setDisable(true);
            this.player2CouleurPolice.setDisable(true);
            this.player2Police.setDisable(true);
            this.player2TaillePolice.setDisable(true);
            this.player2BtnImage.setDisable(true);
            this.player2Image.setDisable(true);

            this.imageNbPlayer1.setEffect(null);
            this.setMonochrome(this.imageNbPlayer21);
            this.setMonochrome(this.imageNbPlayer22);

            this.setAiEnabled(true);
        } else {
            this.btnNbPlayer1.setDisable(false);
            this.btnNbPlayer2.setDisable(true);

            // Activer les paramètes de player 2
            this.player2Name.setDisable(false);
            this.updatePlayer2Personnalization(this.lastPushDesignBtnPlayer2);

            this.setMonochrome(this.imageNbPlayer1);
            this.imageNbPlayer21.setEffect(null);
            this.imageNbPlayer22.setEffect(null);

            this.setAiEnabled(false);
        }
    }

    private void setAiEnabled(boolean b) {
        if (b) {
            if (this.iaLevel.equals("normal")) {
                this.btnIALevelAdvanced.setDisable(false);
                this.imageIALevelNormal.setEffect(null);
            } else {
                this.btnIALevelNormal.setDisable(false);
                this.imageIALevelAdvanced.setEffect(null);
            }
        } else {
            this.btnIALevelNormal.setDisable(true);
            this.btnIALevelAdvanced.setDisable(true);
            this.setMonochrome(this.imageIALevelNormal);
            this.setMonochrome(this.imageIALevelAdvanced);
        }
    }

    private void updateIaLevel(String iaLevel) {
        if (this.nbPlayer == 2) {
            this.iaLevel = iaLevel;

            if (iaLevel.equals("normal")) {
                this.btnIALevelNormal.setDisable(true);
                this.imageIALevelNormal.setEffect(null);
                this.btnIALevelAdvanced.setDisable(false);
                this.setMonochrome(this.imageIALevelAdvanced);
            } else {
                this.btnIALevelNormal.setDisable(false);
                this.setMonochrome(this.imageIALevelNormal);
                this.btnIALevelAdvanced.setDisable(true);
                this.imageIALevelAdvanced.setEffect(null);
            }
        }
    }

    private void updateFirstTurn(FirstTurn firstTurn) {
        this.firstPlayer = firstTurn;

        switch (firstTurn) {
            case J1 -> {
                this.btnFirstTurnPlayer1.setDisable(true);
                this.btnFirstTurnPlayer2.setDisable(false);
                this.btnFirstTurnRandom.setDisable(false);
                this.imageFirstTurnJ1.setEffect(null);
                this.setMonochrome(this.imageFirstTurnJ2);
                this.setMonochrome(this.imageFirstTurnAlea);
            }
            case J2 -> {
                this.btnFirstTurnPlayer1.setDisable(false);
                this.btnFirstTurnPlayer2.setDisable(true);
                this.btnFirstTurnRandom.setDisable(false);
                this.setMonochrome(this.imageFirstTurnJ1);
                this.imageFirstTurnJ2.setEffect(null);
                this.setMonochrome(this.imageFirstTurnAlea);
            }
            case ALEA -> {
                this.btnFirstTurnPlayer1.setDisable(false);
                this.btnFirstTurnPlayer2.setDisable(false);
                this.btnFirstTurnRandom.setDisable(true);
                this.setMonochrome(this.imageFirstTurnJ1);
                this.setMonochrome(this.imageFirstTurnJ2);
                this.imageFirstTurnAlea.setEffect(null);
            }
            default -> {}
        }
    }

    private void setMonochrome(ImageView iv) {
        // Créer un effet de désaturation
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
        iv.setEffect(monochrome);
    }

    private void updatePlayer1Personnalization(String type) {
        boolean carPerso = type.equals("caractere");
        
        this.player1BtnCaractere.setDisable(carPerso);
        this.player1BtnImage.setDisable(!carPerso);

        this.player1Caractere.setDisable(!carPerso);
        this.player1CouleurPolice.setDisable(!carPerso);
        this.player1Police.setDisable(!carPerso);
        this.player1TaillePolice.setDisable(!carPerso);

        this.player1Image.setDisable(carPerso);
    }

    private void updatePlayer2Personnalization(String type) {
        this.lastPushDesignBtnPlayer2 = type;

        boolean carPerso = type.equals("caractere");
        
        this.player2BtnCaractere.setDisable(carPerso);
        this.player2BtnImage.setDisable(!carPerso);

        this.player2Caractere.setDisable(!carPerso);
        this.player2CouleurPolice.setDisable(!carPerso);
        this.player2Police.setDisable(!carPerso);
        this.player2TaillePolice.setDisable(!carPerso);

        this.player2Image.setDisable(carPerso);
    }

    @FXML
    void play(ActionEvent event) {
        APlayer player1;
        if (this.player1BtnCaractere.isDisable()) {
            player1 = new PlayerText(
                    this.player1Name.getText(),
                    this.player1Caractere.getText(),
                    this.player1Police.getValue(),
                    Integer.parseInt(this.player1TaillePolice.getText()),
                    this.player1CouleurPolice.getValue()
            );
        } else {
            player1 = new PlayerImage(
                    player1Name.getText(),
                    player1Image.getText()
            );
        }

        APlayer player2;
        if (this.nbPlayer == 1) {
            player2 = new PlayerText(
                "IA",
                this.player1Caractere.getText().equals("O") ? "X" : "O",
                "Calibri",
                50,
                Color.BLACK
            );
        } else {
            if (this.player2BtnCaractere.isDisable()) {
                player2 = new PlayerText(
                    this.player2Name.getText(),
                    this.player2Caractere.getText(),
                    this.player2Police.getValue(),
                    Integer.parseInt(this.player2TaillePolice.getText()),
                    this.player2CouleurPolice.getValue()
                );
            } else {
                player2 = new PlayerImage(
                    this.player2Name.getText(),
                    this.player2Image.getText()
                );
            }
        }

        int firstTurn = this.getFirstTurn() == FirstTurn.J1 ? 1 : 2;
        Morpion.launchMorpion(new MorpionController(player1, player2, firstTurn, this.nbPlayer, this.iaLevel));
    }

    private FirstTurn getFirstTurn() {
        if (this.firstPlayer == FirstTurn.ALEA) {
            Random random = new Random();
            return random.nextInt(2) == 0 ? FirstTurn.J1 : FirstTurn.J2;
        }
        return this.firstPlayer;
    }

    @FXML
    public void openRulesWindow(ActionEvent actionEvent) {
        Morpion.launchRegle();
    }

    @FXML
    public void openLeaveWindow(ActionEvent event) {
        Morpion.launchQuitter();
    }

}
package morpion.controller;

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
import morpion.Joueur;
import morpion.Morpion;

import java.io.IOException;
import java.util.Random;

public class parametreController {

    // Joueur 1
    @FXML private TextField player1Name;
    @FXML private Button player1BtnCaractere;
    @FXML private TextField player1Caractere;
    @FXML private ColorPicker player1CouleurPolice;
    @FXML private ComboBox<String> player1Police;
    @FXML private TextField player1TaillePolice;
    @FXML private Button player1BtnImage;
    @FXML private TextField player1Image;

    // Joueur 2
    @FXML private TextField player2Name;
    @FXML private Button player2BtnCaractere;
    @FXML private TextField player2Caractere;
    @FXML private ColorPicker player2CouleurPolice;
    @FXML private ComboBox<String> player2Police;
    @FXML private TextField player2TaillePolice;
    @FXML private Button player2BtnImage;
    @FXML private TextField player2Image;

    // Nombre de joueur
    @FXML private Button btnNbPlayer1;
    @FXML private Button btnNbPlayer2;

    // Niveau de l'IA
    @FXML private Button btnIALevelAdvanced;
    @FXML private Button btnIALevelNormal;

    // Premier joueur a joué
    @FXML private Button btnFirstTurnPlayer1;
    @FXML private Button btnFirstTurnPlayer2;
    @FXML private Button btnFirstTurnRandom;

    // Image
    @FXML private ImageView imageFirstTurnJ1;
    @FXML private ImageView imageFirstTurnAlea;
    @FXML private ImageView imageFirstTurnJ2;
    @FXML private ImageView imageIALevelNormal;
    @FXML private ImageView imageIALevelAdvanced;
    @FXML private ImageView imageNbPlayer1;
    @FXML private ImageView imageNbPlayer21;
    @FXML private ImageView imageNbPlayer22;

    private int nbPlayer;
    private String iaLevel;
    private String firstPlayer;
    private String lastPushDesignBtnPlayer2;

    // private Joueur joueur1;
    // private Joueur joueur2;

    public void initialize() {
        this.player1Police.getItems().addAll(Font.getFamilies());
        this.player2Police.getItems().addAll(Font.getFamilies());

        this.nbPlayer = 2;
        this.iaLevel = this.btnIALevelNormal.getText();
        this.firstPlayer = this.btnFirstTurnPlayer1.getText();
        this.lastPushDesignBtnPlayer2 = this.player2BtnImage.getText();
        this.setAiEnabled(this.nbPlayer == 1);

        this.setMonochrome(ImageNbPlayer1);
        this.setMonochrome(ImageFirstTurnAlea);
        this.setMonochrome(ImageFirstTurnJ2);
        this.setMonochrome(ImageIALevelAdvanced);
    }

    // private void setMonochrome(ImageView iv) {
    //     // Créer un effet de désaturation
    //     ColorAdjust monochrome = new ColorAdjust();
    //     monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
    //     iv.setEffect(monochrome);
    // }

    // @FXML
    // void IALevel(ActionEvent event) {
    //     if (this.nbPlayer == 2) {
    //         Button source = (Button) event.getSource();
    //         if (source.equals(this.btnIALevelNormal)) {
    //             IALastButtonPress = this.btnIALevelNormal;

    //             this.btnIALevelNormal.setDisable(true);
    //             ImageIALevelNormal.setEffect(null);
    //             this.btnIALevelAdvanced.setDisable(false);
    //             this.setMonochrome(ImageIALevelAdvanced);
    //         } else {
    //             IALastButtonPress = this.btnIALevelAdvanced;

    //             this.btnIALevelNormal.setDisable(false);
    //             this.setMonochrome(ImageIALevelNormal);
    //             this.btnIALevelAdvanced.setDisable(true);
    //             ImageIALevelAdvanced.setEffect(null);
    //         }
    //     }
    // }

    // @FXML
    // void player1Button(ActionEvent event) {
    //     Button source = (Button)event.getSource();
    //     // Caractère
    //     if (source.equals(Player1btnCaractere)) {
    //         player1btnCaractere.setDisable(true);
    //         player1btnImage.setDisable(false);

    //         player1Caractere.setDisable(false);
    //         player1CouleurPolice.setDisable(false);
    //         player1Police.setDisable(false);
    //         player1TaillePolice.setDisable(false);

    //         player1Image.setDisable(true);
    //     }
    //     // Image
    //     else {
    //         player1btnCaractere.setDisable(false);
    //         player1btnImage.setDisable(true);

    //         player1Caractere.setDisable(true);
    //         player1CouleurPolice.setDisable(true);
    //         player1Police.setDisable(true);
    //         player1TaillePolice.setDisable(true);

    //         player1Image.setDisable(false);
    //     }
    // }

    // @FXML
    // void player2Button(ActionEvent event) {
    //     Button source = (Button) event.getSource();
    //     player2Personnalization(source);
    // }

    // private void player2Personnalization(Button source) {
    //     // Caractère
    //     if (source.equals(Player2btnCaractere)) {
    //         player2LastbtnPress = player2btnCaractere;

    //         player2btnCaractere.setDisable(true);
    //         player2btnImage.setDisable(false);

    //         player2Caractere.setDisable(false);
    //         player2CouleurPolice.setDisable(false);
    //         player2Police.setDisable(false);
    //         player2TaillePolice.setDisable(false);

    //         player2Image.setDisable(true);
    //     }
    //     // Image
    //     else {
    //         player2LastbtnPress = player2btnImage;

    //         player2btnCaractere.setDisable(false);
    //         player2btnImage.setDisable(true);

    //         player2Caractere.setDisable(true);
    //         player2CouleurPolice.setDisable(true);
    //         player2Police.setDisable(true);
    //         player2TaillePolice.setDisable(true);

    //         player2Image.setDisable(false);
    //     }
    // }

    // @FXML
    // void firstTurn(ActionEvent event) {
    //     Button source = (Button) event.getSource();
    //     // TODO: source.getText()
    //     if (source.equals(this.btnFirstTurnPlayer1)) {
    //         this.btnFirstTurnPlayer1.setDisable(true);
    //         this.btnFirstTurnPlayer2.setDisable(false);
    //         this.btnFirstTurnRandom.setDisable(false);
    //         this.setMonochrome(ImageFirstTurnAlea);
    //         ImageFirstTurnJ1.setEffect(null);
    //         this.setMonochrome(ImageFirstTurnJ2);
    //     }
    //     else if (source.equals(this.btnFirstTurnPlayer2)) {
    //         this.btnFirstTurnPlayer1.setDisable(false);
    //         this.btnFirstTurnPlayer2.setDisable(true);
    //         this.btnFirstTurnRandom.setDisable(false);
    //         this.setMonochrome(ImageFirstTurnAlea);
    //         this.setMonochrome(ImageFirstTurnJ1);
    //         ImageFirstTurnJ2.setEffect(null);
    //     }
    //     else {
    //         this.btnFirstTurnPlayer1.setDisable(false);
    //         this.btnFirstTurnPlayer2.setDisable(false);
    //         this.btnFirstTurnRandom.setDisable(true);
    //         ImageFirstTurnAlea.setEffect(null);
    //         this.setMonochrome(ImageFirstTurnJ1);
    //         this.setMonochrome(ImageFirstTurnJ2);
    //     }
    // }

    // @FXML
    // void nbPlayer(ActionEvent event) {
    //     Button source = (Button)event.getSource();
    //     if (source.equals(this.btnNbPlayer1)) {
    //         this.btnNbPlayer1.setDisable(true);
    //         this.btnNbPlayer2.setDisable(false);

    //         // désactiver tout les paramètres de player2
    //         player2Name.setDisable(true);
    //         player2btnCaractere.setDisable(true);
    //         player2Caractere.setDisable(true);
    //         player2CouleurPolice.setDisable(true);
    //         player2Police.setDisable(true);
    //         player2TaillePolice.setDisable(true);
    //         player2btnImage.setDisable(true);
    //         player2Image.setDisable(true);

    //         ImageNbPlayer1.setEffect(null);
    //         this.setMonochrome(ImageNbPlayer21);
    //         this.setMonochrome(ImageNbPlayer22);

    //         this.setAiEnabled(true);
    //     }
    //     else {
    //         this.btnNbPlayer1.setDisable(false);
    //         this.btnNbPlayer2.setDisable(true);

    //         // Activer les paramètes de player 2
    //         player2Name.setDisable(false);
    //         if (Player2btnImage.isDisable()) {
    //            player2LastbtnPress = player2btnImage;
    //         }
    //         player2Personnalization(Player2LastbtnPress);

    //         this.setMonochrome(ImageNbPlayer1);
    //         ImageNbPlayer21.setEffect(null);
    //         ImageNbPlayer22.setEffect(null);

    //         this.setAiEnabled(false);
    //     }
    // }

    // private void setAiEnabled(boolean b) {
    //     if (b) {
    //         this.nbPlayer == 2 = true;
    //         if (IALastButtonPress.equals(this.btnIALevelNormal)) {
    //             this.btnIALevelAdvanced.setDisable(false);
    //             ImageIALevelNormal.setEffect(null);
    //         }
    //         else {
    //             this.btnIALevelNormal.setDisable(false);
    //             ImageIALevelAdvanced.setEffect(null);
    //         }
    //     }
    //     else {
    //         this.nbPlayer == 2 = false;
    //         this.btnIALevelNormal.setDisable(false);
    //         this.btnIALevelAdvanced.setDisable(false);
    //         this.setMonochrome(ImageIALevelNormal);
    //         this.setMonochrome(ImageIALevelAdvanced);
    //     }
    // }

    // @FXML
    // void jouer(ActionEvent event) {
    //     morpionController mc = Morpion.getMc();

    //     // Joueur 1
    //     // caractere
    //     if (Player1btnCaractere.isDisable()) {
    //         joueur1 = new Joueur(
    //                 player1Name.getText(),
    //                 player1Caractere.getText(),
    //                 player1Police.getValue(),
    //                 Integer.parseInt(Player1TaillePolice.getText()),
    //                 player1CouleurPolice.getValue()
    //         );
    //         mc.setJoueur1(joueur1);
    //     }
    //     // Image
    //     else {
    //         joueur1 = new Joueur(
    //                 player1Name.getText(),
    //                 player1Image.getText()
    //         );
    //         mc.setJoueur1(joueur1);
    //     }

    //     // Joueur 2
    //     // IA
    //     if (this.btnNbPlayer1.isDisable()) {
    //         joueur2 = new Joueur(
    //             "IA",
    //             player1Caractere.getText().equals("O") ? "X" : "O",
    //             "Calibri",
    //             50,
    //             Color.BLACK
    //         );
    //         mc.setJoueur2(joueur2);
    //     }
    //     else {
    //         // caractere
    //         if (Player2btnCaractere.isDisable()) {
    //             joueur2 = new Joueur(
    //                 player2Name.getText(),
    //                 player2Caractere.getText(),
    //                 player2Police.getValue(),
    //                 Integer.parseInt(Player2TaillePolice.getText()),
    //                 player2CouleurPolice.getValue()
    //             );
    //             mc.setJoueur2(joueur2);
    //         }
    //         // Image
    //         else {
    //             joueur2 = new Joueur(
    //                 player2Name.getText(),
    //                 player2Image.getText()
    //             );
    //             mc.setJoueur2(joueur2);
    //         }
    //     }

    //     mc.setJoueurActuel(getFirstTurn());
    //     mc.setPlayAlone(this.btnNbPlayer1.isDisable());
    //     if (this.btnNbPlayer1.isDisable()) { mc.setIALevelNormal(this.btnIALevelNormal.isDisable()); }

    //     Morpion.launchMorpion();
    // }

    // public Joueur getFirstTurn() {
    //     if (this.btnFirstTurnPlayer1.isDisable()) {
    //         return joueur1;
    //     }
    //     else if (this.btnFirstTurnPlayer2.isDisable()) {
    //         return joueur2;
    //     }
    //     else {
    //         Random random = new Random();
    //         return (random.nextInt(2) == 0 ? joueur1 : joueur2);
    //     }
    // }

    @FXML
    public void openRulesWindow(ActionEvent actionEvent) {
        Morpion.launchRegle();
    }

    @FXML
    public void openLeaveWindow(ActionEvent event) {
        Morpion.launchQuitter();
    }

}
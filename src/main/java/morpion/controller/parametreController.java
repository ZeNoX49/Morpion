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

    // Premier joueur a joué
    @FXML private Button Button_FirstTurn_Player1 = new Button();
    @FXML private Button Button_FirstTurn_Player2 = new Button();
    @FXML private Button Button_FirstTurn_Random = new Button();

    // Niveau de l'IA
    @FXML private Button Button_IALevel_Advanced = new Button();
    @FXML private Button Button_IALevel_Normal = new Button();

    // Nombre de joueur
    @FXML private Button Button_NbPlayer_1 = new Button();
    @FXML private Button Button_NbPlayer_2 = new Button();

    // Joueur 1
    @FXML private TextField Player1_Name = new TextField();
    @FXML private Button Player1_Button_Caractere = new Button();
    @FXML private TextField Player1_Caractere = new TextField();
    @FXML private ColorPicker Player1_CouleurPolice = new ColorPicker();
    @FXML private ComboBox<String> Player1_Police = new ComboBox<>();
    @FXML private TextField Player1_TaillePolice = new TextField();
    @FXML private Button Player1_Button_Image = new Button();
    @FXML private TextField Player1_Image = new TextField();

    // Joueur 2
    @FXML private TextField Player2_Name = new TextField();
    @FXML private Button Player2_Button_Caractere = new Button();
    @FXML private TextField Player2_Caractere = new TextField();
    @FXML private ColorPicker Player2_CouleurPolice = new ColorPicker();
    @FXML private ComboBox<String> Player2_Police = new ComboBox<>();
    @FXML private TextField Player2_TaillePolice = new TextField();
    @FXML private Button Player2_Button_Image = new Button();
    @FXML private TextField Player2_Image = new TextField();

    // Image
    @FXML private ImageView Image_FirstTurn_Alea = new ImageView();
    @FXML private ImageView Image_FirstTurn_J1 = new ImageView();
    @FXML private ImageView Image_FirstTurn_J2 = new ImageView();
    @FXML private ImageView Image_IALevel_Advanced = new ImageView();
    @FXML private ImageView Image_IALevel_Normal = new ImageView();
    @FXML private ImageView Image_NbPlayer_1 = new ImageView();
    @FXML private ImageView Image_NbPlayer_2_1 = new ImageView();
    @FXML private ImageView Image_NbPlayer_2_2 = new ImageView();

    private static Button Player2_LastButton_Press;
    private static Button IALastButtonPress;
    private static boolean IAEnabled;

    private Joueur joueur1;
    private Joueur joueur2;

    public void initialize() {
        Player1_Police.getItems().addAll(Font.getFamilies());
        Player2_Police.getItems().addAll(Font.getFamilies());

        Player2_LastButton_Press = Player2_Button_Image;
        IALastButtonPress = Button_IALevel_Normal;
        IA_isEnabled(false);

        setMonochrome(Image_NbPlayer_1);
        setMonochrome(Image_FirstTurn_Alea);
        setMonochrome(Image_FirstTurn_J2);
        setMonochrome(Image_IALevel_Advanced);
    }

    private void setMonochrome(ImageView iv) {
        // Créer un effet de désaturation
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1); // -1 = complètement désaturé (noir & blanc)
        iv.setEffect(monochrome);
    }

    @FXML
    void IALevel(ActionEvent event) {
        if (IAEnabled) {
            Button source = (Button) event.getSource();
            if (source.equals(Button_IALevel_Normal)) {
                IALastButtonPress = Button_IALevel_Normal;

                Button_IALevel_Normal.setDisable(true);
                Image_IALevel_Normal.setEffect(null);
                Button_IALevel_Advanced.setDisable(false);
                setMonochrome(Image_IALevel_Advanced);
            } else {
                IALastButtonPress = Button_IALevel_Advanced;

                Button_IALevel_Normal.setDisable(false);
                setMonochrome(Image_IALevel_Normal);
                Button_IALevel_Advanced.setDisable(true);
                Image_IALevel_Advanced.setEffect(null);
            }
        }
    }

    @FXML
    void Player1_Button(ActionEvent event) {
        Button source = (Button)event.getSource();
        // Caractère
        if (source.equals(Player1_Button_Caractere)) {
            Player1_Button_Caractere.setDisable(true);
            Player1_Button_Image.setDisable(false);

            Player1_Caractere.setDisable(false);
            Player1_CouleurPolice.setDisable(false);
            Player1_Police.setDisable(false);
            Player1_TaillePolice.setDisable(false);

            Player1_Image.setDisable(true);
        }
        // Image
        else {
            Player1_Button_Caractere.setDisable(false);
            Player1_Button_Image.setDisable(true);

            Player1_Caractere.setDisable(true);
            Player1_CouleurPolice.setDisable(true);
            Player1_Police.setDisable(true);
            Player1_TaillePolice.setDisable(true);

            Player1_Image.setDisable(false);
        }
    }

    @FXML
    void Player2_Button(ActionEvent event) {
        Button source = (Button) event.getSource();
        Player2_Personnalization(source);
    }

    private void Player2_Personnalization(Button source) {
        // Caractère
        if (source.equals(Player2_Button_Caractere)) {
            Player2_LastButton_Press = Player2_Button_Caractere;

            Player2_Button_Caractere.setDisable(true);
            Player2_Button_Image.setDisable(false);

            Player2_Caractere.setDisable(false);
            Player2_CouleurPolice.setDisable(false);
            Player2_Police.setDisable(false);
            Player2_TaillePolice.setDisable(false);

            Player2_Image.setDisable(true);
        }
        // Image
        else {
            Player2_LastButton_Press = Player2_Button_Image;

            Player2_Button_Caractere.setDisable(false);
            Player2_Button_Image.setDisable(true);

            Player2_Caractere.setDisable(true);
            Player2_CouleurPolice.setDisable(true);
            Player2_Police.setDisable(true);
            Player2_TaillePolice.setDisable(true);

            Player2_Image.setDisable(false);
        }
    }

    @FXML
    void firstTurn(ActionEvent event) {
        Button source = (Button)event.getSource();
        if (source.equals(Button_FirstTurn_Player1)) {
            Button_FirstTurn_Player1.setDisable(true);
            Button_FirstTurn_Player2.setDisable(false);
            Button_FirstTurn_Random.setDisable(false);
            setMonochrome(Image_FirstTurn_Alea);
            Image_FirstTurn_J1.setEffect(null);
            setMonochrome(Image_FirstTurn_J2);
        }
        else if (source.equals(Button_FirstTurn_Player2)) {
            Button_FirstTurn_Player1.setDisable(false);
            Button_FirstTurn_Player2.setDisable(true);
            Button_FirstTurn_Random.setDisable(false);
            setMonochrome(Image_FirstTurn_Alea);
            setMonochrome(Image_FirstTurn_J1);
            Image_FirstTurn_J2.setEffect(null);
        }
        else {
            Button_FirstTurn_Player1.setDisable(false);
            Button_FirstTurn_Player2.setDisable(false);
            Button_FirstTurn_Random.setDisable(true);
            Image_FirstTurn_Alea.setEffect(null);
            setMonochrome(Image_FirstTurn_J1);
            setMonochrome(Image_FirstTurn_J2);
        }
    }

    @FXML
    void nbPlayer(ActionEvent event) {
        Button source = (Button)event.getSource();
        if (source.equals(Button_NbPlayer_1)) {
            Button_NbPlayer_1.setDisable(true);
            Button_NbPlayer_2.setDisable(false);

            // désactiver tout les paramètres de Player2
            Player2_Name.setDisable(true);
            Player2_Button_Caractere.setDisable(true);
            Player2_Caractere.setDisable(true);
            Player2_CouleurPolice.setDisable(true);
            Player2_Police.setDisable(true);
            Player2_TaillePolice.setDisable(true);
            Player2_Button_Image.setDisable(true);
            Player2_Image.setDisable(true);

            Image_NbPlayer_1.setEffect(null);
            setMonochrome(Image_NbPlayer_2_1);
            setMonochrome(Image_NbPlayer_2_2);

            IA_isEnabled(true);
        }
        else {
            Button_NbPlayer_1.setDisable(false);
            Button_NbPlayer_2.setDisable(true);

            // Activer les paramètes de Player 2
            Player2_Name.setDisable(false);
            if (Player2_Button_Image.isDisable()) {
               Player2_LastButton_Press = Player2_Button_Image;
            }
            Player2_Personnalization(Player2_LastButton_Press);

            setMonochrome(Image_NbPlayer_1);
            Image_NbPlayer_2_1.setEffect(null);
            Image_NbPlayer_2_2.setEffect(null);

            IA_isEnabled(false);
        }
    }

    private void IA_isEnabled(boolean b) {
        if (b) {
            IAEnabled = true;
            if (IALastButtonPress.equals(Button_IALevel_Normal)) {
                Button_IALevel_Advanced.setDisable(false);
                Image_IALevel_Normal.setEffect(null);
            }
            else {
                Button_IALevel_Normal.setDisable(false);
                Image_IALevel_Advanced.setEffect(null);
            }
        }
        else {
            IAEnabled = false;
            Button_IALevel_Normal.setDisable(false);
            Button_IALevel_Advanced.setDisable(false);
            setMonochrome(Image_IALevel_Normal);
            setMonochrome(Image_IALevel_Advanced);
        }
    }

    @FXML
    void jouer(ActionEvent event) throws IOException, InterruptedException {
        morpionController mc = Morpion.getMc();

        // Joueur 1
        // caractere
        if (Player1_Button_Caractere.isDisable()) {
            joueur1 = new Joueur(
                    Player1_Name.getText(),
                    Player1_Caractere.getText(),
                    Player1_Police.getValue(),
                    Integer.parseInt(Player1_TaillePolice.getText()),
                    Player1_CouleurPolice.getValue()
            );
            mc.setJoueur1(joueur1);
        }
        // Image
        else {
            joueur1 = new Joueur(
                    Player1_Name.getText(),
                    Player1_Image.getText()
            );
            mc.setJoueur1(joueur1);
        }

        // Joueur 2
        // IA
        if (Button_NbPlayer_1.isDisable()) {
            joueur2 = new Joueur(
                "IA",
                Player1_Caractere.getText().equals("O") ? "X" : "O",
                "Calibri",
                50,
                Color.BLACK
            );
            mc.setJoueur2(joueur2);
        }
        else {
            // caractere
            if (Player2_Button_Caractere.isDisable()) {
                joueur2 = new Joueur(
                    Player2_Name.getText(),
                    Player2_Caractere.getText(),
                    Player2_Police.getValue(),
                    Integer.parseInt(Player2_TaillePolice.getText()),
                    Player2_CouleurPolice.getValue()
                );
                mc.setJoueur2(joueur2);
            }
            // Image
            else {
                joueur2 = new Joueur(
                    Player2_Name.getText(),
                    Player2_Image.getText()
                );
                mc.setJoueur2(joueur2);
            }
        }

        mc.setJoueurActuel(getFirstTurn());
        mc.setPlayAlone(Button_NbPlayer_1.isDisable());
        if (Button_NbPlayer_1.isDisable()) { mc.setIALevelNormal(Button_IALevel_Normal.isDisable()); }

        Morpion.launchMorpion();
    }

    public Joueur getFirstTurn() {
        if (Button_FirstTurn_Player1.isDisable()) {
            return joueur1;
        }
        else if (Button_FirstTurn_Player2.isDisable()) {
            return joueur2;
        }
        else {
            Random random = new Random();
            return (random.nextInt(2) == 0 ? joueur1 : joueur2);
        }
    }

    @FXML
    public void MB_Rules(ActionEvent actionEvent) throws IOException {
        Morpion.launchRegle();
    }

    @FXML
    void MB_Leave(ActionEvent event) throws IOException {
        Morpion.launchQuitter();
    }

}
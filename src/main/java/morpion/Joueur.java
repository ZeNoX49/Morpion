package morpion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Joueur {
    private String nom;
    private String choix;
    private String caractere;
    private Font font;
    private Color couleur;
    private String lienImg;
    private int score;

    // Constructeur
    public Joueur(String nom, String caractere, String police, int taille, Color couleur) {
        this.nom = nom;
        this.caractere = caractere;
        font = new Font(police, taille);
        this.couleur = couleur;
        choix = "caractere";
        score = 0;
    }

    public Joueur(String nom, String lienImg) {
        this.nom = nom;
        choix = "image";
        score = 0;
        this.lienImg = lienImg;
    }

    // Setter
    public void incScore() {
        score++;
    }

    // Getter
    public boolean isChoixCaractere() {
        return choix.equals("caractere");
    }

    public String getNom() {
        return nom;
    }
    public String getCaractere() { return caractere; }
    public Font getFont() { return font; }
    public String getColor() {
        // Convertir la couleur en format hexadécimal
        int r = (int) (couleur.getRed() * 255);
        int g = (int) (couleur.getGreen() * 255);
        int b = (int) (couleur.getBlue() * 255);
        return String.format("-fx-text-fill: #%02X%02X%02X;", r, g, b);
    }
    public int getScore() {
        return score;
    }
    public ImageView getImage() {
        ImageView imageView = new ImageView(new Image(lienImg));
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);
        imageView.setPreserveRatio(false);
        return imageView;
    }

    public void resetScore() {
        score = 0;
    }
}

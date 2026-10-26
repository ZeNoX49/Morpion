package morpion.player;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerText extends APlayer {
    private final String character;
    private final Font font;
    private final String colorStyle;

    public PlayerText(String name, String character, String font, int size, Color color) {
        super(name);
        this.character = character;
        this.font = new Font(font, size);

        // Convertir la couleur en format hexadécimal
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        this.colorStyle = String.format("-fx-text-fill: #%02X%02X%02X;", r, g, b);
    }

    @Override
    public void fillClickedButton(Button btn) {
        btn.setText(this.character);
        btn.setFont(this.font);
        btn.setStyle(this.colorStyle);
    }

}

package morpion.player;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerImage extends APlayer {
    private final Image image;

    public PlayerImage(String name, String lienImg) {
        super(name);
        this.image = new Image(lienImg);
    }

    @Override
    public void fillClickedButton(Button btn) {
        ImageView imageView = new ImageView(this.image);
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);
        imageView.setPreserveRatio(false);
        btn.setGraphic(imageView);
    }

}

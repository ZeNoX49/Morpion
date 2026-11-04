package morpion.player;

import javafx.scene.control.Button;

public abstract class APlayer {
    private final String name;
    private int score;

    public APlayer(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void incScore() {
        this.score++;
    }

    public void resetScore() {
        this.score = 0;
    }

    public abstract void fillClickedButton(Button btn);

}

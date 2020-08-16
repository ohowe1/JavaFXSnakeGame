package me.ohowe12.snakegame.game;

import javafx.scene.paint.Color;
import me.ohowe12.snakegame.gui.GamePane;

public class Food extends GameElement {

    public Food(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    @Override
    public void render(GamePane pane) {
        pane.setPixel(position, Color.DARKRED);
    }
}

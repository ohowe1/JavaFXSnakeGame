package me.ohowe12.snakegame.game;

import javafx.scene.paint.Color;
import me.ohowe12.snakegame.gui.GamePane;
import me.ohowe12.snakegame.util.Position;

public class SnakePiece extends GameElement {

    public SnakePiece(int x, int y) {
        setX(x);
        setY(y);
    }

    public void render(GamePane pane) {
        pane.setPixel(position, Color.WHITE);
    }

    public Position getPosition() {
        return position;
    }

    public void setPos(SnakePiece snakePiece) {
        setPos(snakePiece.getPosition());
    }

    public void setPos(Position pos) {
        setX(pos.getX());
        setY(pos.getY());
    }
}

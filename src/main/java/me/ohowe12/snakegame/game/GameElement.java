package me.ohowe12.snakegame.game;

import me.ohowe12.snakegame.gui.GamePane;
import me.ohowe12.snakegame.util.Direction;
import me.ohowe12.snakegame.util.Position;

public abstract class GameElement {

    protected Position position = new Position(0, 0);

    public abstract void render(GamePane pane);

    public void moveBy(Direction direction) {
        if (direction.getDirection().equalsIgnoreCase("y")) {
            changeYBy(direction.getAmount());
        } else {
            changeXBy(direction.getAmount());
        }
    }

    protected void changeYBy(int amount) {
        setY(position.getY() + amount);
    }

    protected void changeXBy(int amount) {
        setX(position.getX() + amount);
    }

    protected void setX(int x) {
        this.position.setX(x);
    }

    protected void setY(int y) {
        this.position.setY(y);
    }

    public Position getPosition() {
        return position;
    }

}

package me.ohowe12.snakegame.game;

import java.util.ArrayList;
import me.ohowe12.snakegame.gui.GamePane;
import me.ohowe12.snakegame.util.Direction;
import me.ohowe12.snakegame.util.Position;

public class Snake extends GameElement {

    private final ArrayList<SnakePiece> pieces;
    private Direction direction = Direction.RIGHT;

    public Snake() {
        this.pieces = new ArrayList<>();
        int startingY = 12;
        setX(8);
        setY(startingY);
        pieces.add(new SnakePiece(8, startingY));
        addXSnakePieces(4);
    }

    private void addXSnakePieces(int x) {
        for (int i = 0; i < x; i++) {
            addSnakePiece();
        }
    }

    public void addSnakePiece() {
        Position lastPos = pieces.get(pieces.size() - 1).getPosition();
        if (direction.getDirection().equals("y")) {
            pieces
                .add(new SnakePiece(lastPos.getX(), lastPos.getY() + (direction.getAmount() * -1)));
        } else {
            pieces
                .add(new SnakePiece(lastPos.getX() + (direction.getAmount() * -1), lastPos.getY()));
        }
    }

    public void setDirection(Direction direction) {
        if (this.direction.getDirection().equalsIgnoreCase(direction.getDirection())){
            return;
        }
        this.direction = direction;
    }

    public void render(GamePane pane) {
        pieces.forEach(snakePiece -> snakePiece.render(pane));
    }

    public void updatePosition() {
        if (direction == Direction.STOPPED) {
            return;
        }
        for (int i = pieces.size() - 1; i > 0; i--) {
            pieces.get(i).setPos(pieces.get(i - 1));
        }
        pieces.get(0).moveBy(direction);
        moveBy(direction);
    }

    private boolean checkCollisionWithSelf() {
        for (int i = pieces.size() - 1; i > 0; i--) {
            if (pieces.get(i).getPosition().equals(this.position)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionWithWall(int xWidth, int yWidth) {
        return position.getY() < 0 || position.getY() + 1 > yWidth || position.getX() < 0
            || position.getX() + 1 > xWidth;
    }

    public boolean gameOver(int xWidth, int yWidth) {
        return checkCollisionWithSelf() || checkCollisionWithWall(xWidth, yWidth);
    }

    public boolean isCollidingWithGameElement(GameElement element) {
        return element.getPosition().equals(position);
    }
}

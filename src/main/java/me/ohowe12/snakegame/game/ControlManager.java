package me.ohowe12.snakegame.game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.ohowe12.snakegame.SnakeGame;
import me.ohowe12.snakegame.util.Direction;

public class ControlManager {

    public ControlManager(SnakeGame snakeGame) {
        snakeGame.addKeyListener(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
                snakeGame.setSnakeDirection(Direction.UP);
                return;
            }
            if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
                snakeGame.setSnakeDirection(Direction.DOWN);
                return;
            }
            if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
                snakeGame.setSnakeDirection(Direction.LEFT);
                return;
            }
            if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
                snakeGame.setSnakeDirection(Direction.RIGHT);
            }
        });
    }

}

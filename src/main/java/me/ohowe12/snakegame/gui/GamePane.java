package me.ohowe12.snakegame.gui;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import me.ohowe12.snakegame.game.GameElement;
import me.ohowe12.snakegame.util.PixelArray;
import me.ohowe12.snakegame.util.Position;

public class GamePane extends Pane {

    private final PixelArray pixelArray;
    private final ArrayList<GameElement> gameElements = new ArrayList<>();
    private final Label scoreLabel = new Label("0");
    private final Text gameOverLabel = new Text("Game Over");
    private final int pixelSize = 20;

    public GamePane(int xSize, int ySize) {
        scoreLabel.setTextFill(Color.GREEN);
        scoreLabel.setFont(new Font(20));

        gameOverLabel.setFill(Color.GREEN);
        gameOverLabel.setFont(new Font(50));
        gameOverLabel.setVisible(false);

        gameOverLabel.setLayoutY(ySize / 2 - gameOverLabel.getLayoutBounds().getHeight() / 2);
        gameOverLabel.setLayoutX(xSize / 2 - gameOverLabel.getLayoutBounds().getWidth() / 2);

        pixelArray = new PixelArray(xSize / 20, ySize / 20, Color.BLACK);
        this.getChildren().add(scoreLabel);
        this.getChildren().add(gameOverLabel);
        update();
    }

    public void addGameElement(GameElement element) {
        gameElements.add(element);
    }

    public void removeGameElement(GameElement element) {
        gameElements.remove(element);
    }

    public void setPixel(int x, int y, Color color) {
        pixelArray.setPixel(x, y, color);
    }

    public void setPixel(Position position, Color color) {
        pixelArray.setPixel(position, color);
    }

    public void gameOver() {
        gameOverLabel.setVisible(true);
    }

    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public void update() {
        pixelArray.clearPixels();
        gameElements.forEach(gameElement -> gameElement.render(this));
        pixelArray.fillPixels(this, pixelSize);
        scoreLabel.toFront();
        gameOverLabel.toFront();
    }

}

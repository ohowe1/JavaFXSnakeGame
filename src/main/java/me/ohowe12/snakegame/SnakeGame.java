package me.ohowe12.snakegame;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.ohowe12.snakegame.game.ControlManager;
import me.ohowe12.snakegame.game.Food;
import me.ohowe12.snakegame.game.GameThread;
import me.ohowe12.snakegame.game.Snake;
import me.ohowe12.snakegame.gui.GamePane;
import me.ohowe12.snakegame.util.Direction;

public class SnakeGame extends Application {

    private Scene mainScene;
    private GamePane gamePane;
    private Snake snake;
    private Food food;
    private GameThread gameThread;
    private int score = 0;
    private boolean running = true;
    private boolean gameRunning = true;

    public static void main(String[] args) {
        launch(args);
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void start(Stage primaryStage) {
        int XSIZE = 500;
        int YSIZE = 500;
        gamePane = new GamePane(XSIZE, YSIZE);
        mainScene = new Scene(gamePane, XSIZE, YSIZE);

        initPrimaryStage(primaryStage);
        primaryStage.show();

        snake = new Snake();
        overrideFood();

        gamePane.addGameElement(snake);

        gamePane.update();

        gameThread = new GameThread(this);

        gameThread.start();

        new ControlManager(this);
    }

    private Food getNewFood() {
        Food food = new Food(new Random().nextInt(25), new Random().nextInt(25));
        while (snake.isCollidingWithGameElement(food)) {
            System.out.println("Hello");
            food = new Food(new Random().nextInt(25), new Random().nextInt(25));
        }
        return food;
    }

    private void overrideFood() {
        gamePane.removeGameElement(food);
        food = getNewFood();
        gamePane.addGameElement(food);
    }

    public void tick() {
        if (gameRunning) {
            snake.updatePosition();
            if (snake.isCollidingWithGameElement(food)) {
                overrideFood();
                score++;
                snake.addSnakePiece();
                Platform.runLater(() -> gamePane.updateScore(score));
            }
            if (snake.gameOver(25, 25)) {
                System.out.println("Game over");
                gameRunning = false;
                snake.setDirection(Direction.STOPPED);
                Platform.runLater(() -> gamePane.gameOver());
            }
        }
        Platform.runLater(() -> gamePane.update());
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public <T extends Event> void addKeyListener(
        EventType<T> eventType, EventHandler<? super T> handler) {
        mainScene.addEventHandler(eventType, handler);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        running = false;
    }

    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Snake Game");
    }
}

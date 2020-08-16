package me.ohowe12.snakegame.game;

import me.ohowe12.snakegame.SnakeGame;

public class GameThread extends Thread {

    private final SnakeGame snakeGame;

    public GameThread(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 4D;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (snakeGame.isRunning()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                delta--;
            }
        }
    }

    private void tick() {
        snakeGame.tick();
    }
}

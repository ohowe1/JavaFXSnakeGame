package me.ohowe12.snakegame.util;

public enum Direction {
    UP("y", -1), DOWN("y", 1), LEFT("x", -1), RIGHT("x", 1), STOPPED("x", 0);

    private final String direction;
    private final int amount;

    Direction(String direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public String getDirection() {
        return direction;
    }

    public int getAmount() {
        return amount;
    }
}

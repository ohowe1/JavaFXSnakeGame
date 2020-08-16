package me.ohowe12.snakegame.util;

import java.util.Arrays;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PixelArray {

    private final Color[][] pixels;
    private Color backgroundColor;

    public PixelArray(int xSize, int ySize, Color backgroundColor) {
        pixels = new Color[ySize][xSize];
        this.backgroundColor = backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setAll(Color color) {
        for (Color[] pixel : pixels) {
            Arrays.fill(pixel, color);
        }
    }

    public void debugMode() {
        for (int i = 0; i < pixels.length; i++) {
            Color[] pixel = pixels[i];
            if (i % 2 == 0) {
                for (int j = 0; j < pixel.length; j++) {
                    if (j % 2 == 0) {
                        pixel[j] = Color.ORANGE;
                    } else {
                        pixel[j] = Color.BLUE;
                    }
                }
            } else {
                for (int j = 0; j < pixel.length; j++) {
                    if (j % 2 == 0) {
                        pixel[j] = Color.BLUE;
                    } else {
                        pixel[j] = Color.ORANGE;
                    }
                }
            }
        }
    }

    public Color[][] getPixels() {
        return pixels;
    }

    public void setPixel(int x, int y, Color value) {
        if (y < 0) {
            return;
        }
        if (x < 0) {
            return;
        }
        if (y >= pixels.length) {
            return;
        }
        if (x >= pixels[y].length) {
            return;
        }

        pixels[y][x] = value;
    }

    public void setPixel(Position position, Color value) {
        setPixel(position.getX(), position.getY(), value);
    }

    public void fillPixels(Pane pane, int pixelSize) {
        pane.getChildren().removeIf(node -> node.getStyleClass().contains("pixel"));
        int x = 0;
        int y = 0;
        for (Color[] pixel : pixels) {
            for (Color color : pixel) {
                if (color == null) {
                    color = backgroundColor;
                }
                Rectangle rectangle = new Rectangle(x, y, pixelSize, pixelSize);
                rectangle.getStyleClass().add("pixel");
                rectangle.setFill(color);
                pane.getChildren().add(rectangle);
                x += pixelSize;
            }
            x = 0;
            y += pixelSize;
        }
    }

    public void clearPixels() {
        setAll(null);
    }
}

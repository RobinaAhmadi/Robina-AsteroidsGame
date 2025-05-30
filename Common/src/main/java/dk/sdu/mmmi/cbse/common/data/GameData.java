package dk.sdu.mmmi.cbse.common.data;

import java.util.HashSet;
import java.util.Set;

public class GameData {
    private int displayWidth;
    private int displayHeight;
    private double delta;
    private final Set<String> keys = new HashSet<>();
    private int score = 0;
    private boolean gameOver = false; // Game over flag

    // Screen dimensions
    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    // Time delta between frames
    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    // Score system
    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }

    // Input handling
    public void addKey(String key) {
        keys.add(key);
    }

    public void removeKey(String key) {
        keys.remove(key);
    }

    public boolean isDown(String key) {
        return keys.contains(key);
    }

    // Game over logic
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}

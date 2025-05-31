package dk.sdu.mmmi.cbse.core;

import javafx.animation.AnimationTimer;

public class GameLoopTimer extends AnimationTimer {
    private final Runnable gameLoop;
    private final long interval;
    private long lastUpdate = 0;

    public GameLoopTimer(int fps, Runnable gameLoop) {
        this.gameLoop = gameLoop;
        this.interval = 1_000_000_000 / fps; // convert fps to nanoseconds
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= interval) {
            gameLoop.run();
            lastUpdate = now;
        }
    }
}
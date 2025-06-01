package dk.sdu.mmmi.cbse.core;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class AsteroidsGame extends Application {

    private GameData gameData;
    private World world;
    private Collection<IGamePluginService> gamePluginServices;
    private Collection<IEntityProcessingService> entityProcessingServices;
    private Collection<IPostEntityProcessingService> postEntityProcessingServices;
    private ScoreClient scoreClient;

    private static MediaPlayer backgroundPlayer;
    private AudioClip fireSound;

    @Override
    public void start(Stage window) {
        gameData = new GameData();
        world = new World();

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane root = new Pane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed((KeyEvent e) -> handleKeyPress(e.getCode(), true));
        scene.setOnKeyReleased((KeyEvent e) -> handleKeyPress(e.getCode(), false));

        window.setScene(scene);
        window.setTitle("Robina's Asteroids Game");
        window.show();

        gameData.setDisplayWidth((int) canvas.getWidth());
        gameData.setDisplayHeight((int) canvas.getHeight());
        gameData.resetScore();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
        scoreClient = context.getBean(ScoreClient.class);

        gamePluginServices = context.getBeansOfType(IGamePluginService.class).values();
        entityProcessingServices = context.getBeansOfType(IEntityProcessingService.class).values();
        postEntityProcessingServices = context.getBeansOfType(IPostEntityProcessingService.class).values();

        for (IGamePluginService plugin : gamePluginServices) {
            plugin.start(gameData, world);
        }

        loadSounds();
        playBackgroundMusic();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                float delta = (lastTime > 0) ? (now - lastTime) / 1_000_000_000.0f : 0;
                lastTime = now;
                gameData.setDelta(delta);

                if (gameData.isGameOver()) {
                    stop(); // stop timer
                    stopMusic();
                    renderGameOver(gc, window);
                    return;
                }

                for (IEntityProcessingService processor : entityProcessingServices) {
                    processor.process(gameData, world);
                }

                for (IPostEntityProcessingService postProcessor : postEntityProcessingServices) {
                    postProcessor.process(gameData, world);
                }

                render(gc);
            }
        };

        timer.start();
    }

    private void handleKeyPress(KeyCode code, boolean pressed) {
        switch (code) {
            case LEFT -> updateKey(GameKeys.LEFT, pressed);
            case RIGHT -> updateKey(GameKeys.RIGHT, pressed);
            case UP -> updateKey(GameKeys.UP, pressed);
            case DOWN -> updateKey(GameKeys.DOWN, pressed);
            case SPACE -> {
                updateKey(GameKeys.SPACE, pressed);
                if (pressed && fireSound != null) fireSound.play();
            }
        }
    }

    private void updateKey(String key, boolean pressed) {
        if (pressed) {
            gameData.addKey(key);
        } else {
            gameData.removeKey(key);
        }
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        for (Entity entity : world.getEntities()) {
            double[] shapex = entity.getShapeX();
            double[] shapey = entity.getShapeY();

            if (shapex != null && shapey != null && shapex.length > 0) {
                String className = entity.getClass().getSimpleName();

                switch (className) {
                    case "EnemyBullet" -> {
                        gc.setStroke(Color.RED);
                        gc.setLineWidth(2.5);
                    }
                    case "Bullet" -> {
                        gc.setStroke(Color.CYAN);
                        gc.setLineWidth(2.5);
                    }
                    case "Asteroid" -> {
                        gc.setStroke(Color.GRAY);
                        gc.setLineWidth(1.0);
                    }
                    case "Player" -> {
                        gc.setStroke(Color.LIMEGREEN);
                        gc.setLineWidth(2.5);
                    }
                    case "Enemy" -> {
                        gc.setStroke(Color.ORANGERED);
                        gc.setLineWidth(2.0);
                    }
                    default -> {
                        gc.setStroke(Color.WHITE);
                        gc.setLineWidth(1.0);
                    }
                }

                gc.strokePolygon(shapex, shapey, shapex.length);
            }
        }

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 20));
        gc.fillText("Score (Local): " + gameData.getScore(), 10, 25);
        gc.fillText("Score (API): " + scoreClient.getScore(), 10, 50);
    }

    private void renderGameOver(GraphicsContext gc, Stage stage) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        gc.setFill(Color.RED);
        gc.setFont(new Font("Consolas", 40));
        gc.fillText("GAME OVER", 300, 300);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 20));
        gc.fillText("Final Score: " + gameData.getScore(), 320, 350);

        Platform.runLater(() -> {
            Button retryButton = new Button("Retry");
            retryButton.setLayoutX(360);
            retryButton.setLayoutY(400);
            retryButton.setOnAction(e -> {
                scoreClient.resetScore();
                stopMusic(); // stop music on restart
                stage.close();
                Platform.runLater(() -> {
                    try {
                        new AsteroidsGame().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            });

            Pane root = (Pane) stage.getScene().getRoot();
            if (!root.getChildren().contains(retryButton)) {
                root.getChildren().add(retryButton);
            }
        });
    }

    private void loadSounds() {
        try {
            String bgPath = getClass().getResource("/sounds/background.mp3").toExternalForm();
            backgroundPlayer = new MediaPlayer(new Media(bgPath));
            backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            String firePath = getClass().getResource("/sounds/shoot.mp3").toExternalForm();
            fireSound = new AudioClip(firePath);
        } catch (Exception e) {
            System.out.println("[Sound] Failed to load audio files.");
            e.printStackTrace();
        }
    }

    private void playBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.setVolume(0.4);
            backgroundPlayer.play();
        }
    }

    private void stopMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package dk.sdu.mmmi.cbse.core;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.asteroidssystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.asteroidssystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBulletControlSystem;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AsteroidsGame extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();

    private final List<IGamePluginService> gamePluginList = new ArrayList<>();
    private final List<IEntityProcessingService> entityProcessorList = new ArrayList<>();
    private final List<IPostEntityProcessingService> postEntityProcessors = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Robina's Asteroids Game");

        Canvas canvas = new Canvas(800, 600);
        gameData.setDisplayWidth((int) canvas.getWidth());
        gameData.setDisplayHeight((int) canvas.getHeight());
        gameData.resetScore();

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(e -> gameData.addKey(e.getCode().toString()));
        scene.setOnKeyReleased(e -> gameData.removeKey(e.getCode().toString()));

        // Register plugins
        gamePluginList.add(new PlayerPlugin());
        gamePluginList.add(new BulletPlugin());
        gamePluginList.add(new AsteroidPlugin());
        gamePluginList.add(new EnemyPlugin());

        // Register processing systems
        entityProcessorList.add(new PlayerControlSystem());
        entityProcessorList.add(new BulletControlSystem());
        entityProcessorList.add(new AsteroidControlSystem());
        entityProcessorList.add(new EnemyControlSystem());
        entityProcessorList.add(new EnemyBulletControlSystem());

        // Register post-processors
        postEntityProcessors.add(new CollisionSystem());

        for (IGamePluginService plugin : gamePluginList) {
            plugin.start(gameData, world);
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            long last = System.nanoTime();

            @Override
            public void handle(long now) {
                double delta = (now - last) / 1e9;
                last = now;

                gameData.setDelta(delta);

                if (gameData.isGameOver()) {
                    renderGameOver(gc);
                    return;
                }

                for (IEntityProcessingService processor : entityProcessorList) {
                    processor.process(gameData, world);
                }

                for (IPostEntityProcessingService postProcessor : postEntityProcessors) {
                    postProcessor.process(gameData, world);
                }

                render(gc);
            }
        };
        timer.start();
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        for (Entity entity : world.getEntities()) {
            double[] shapex = entity.getShapeX();
            double[] shapey = entity.getShapeY();

            if (shapex != null && shapey != null && shapex.length > 0) {
                if (entity.getClass().getSimpleName().equals("EnemyBullet")) {
                    gc.setStroke(Color.RED);
                } else {
                    gc.setStroke(Color.WHITE);
                }
                gc.strokePolygon(shapex, shapey, shapex.length);
            }
        }

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 20));
        gc.fillText("Score: " + gameData.getScore(), 10, 25);
    }

    private void renderGameOver(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        gc.setFill(Color.RED);
        gc.setFont(new Font("Consolas", 40));
        gc.fillText("GAME OVER", 300, 300);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 20));
        gc.fillText("Final Score: " + gameData.getScore(), 320, 350);
    }
}

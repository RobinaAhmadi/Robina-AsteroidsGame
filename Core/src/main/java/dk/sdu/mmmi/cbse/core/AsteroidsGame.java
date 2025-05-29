package dk.sdu.mmmi.cbse.core;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;


import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class AsteroidsGame extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();

    private final List<IGamePluginService> gamePluginList = new ArrayList<>();
    private final List<IEntityProcessingService> entityProcessorList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Robina's Asteroids Game");

        Canvas canvas = new Canvas(800, 600);
        gameData.setDisplayWidth((int) canvas.getWidth());
        gameData.setDisplayHeight((int) canvas.getHeight());

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(e -> gameData.addKey(e.getCode().toString()));
        scene.setOnKeyReleased(e -> gameData.removeKey(e.getCode().toString()));


        // Load plugins and systems manually or via ServiceLoader
        gamePluginList.add(new PlayerPlugin());
        gamePluginList.add(new BulletPlugin());
        
        entityProcessorList.add(new PlayerControlSystem());
        entityProcessorList.add(new BulletControlSystem());

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

                // Update
                for (IEntityProcessingService processor : entityProcessorList) {
                    processor.process(gameData, world);
                }

                // Render
                render(gc);
            }
        };
        timer.start();
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        gc.setStroke(Color.WHITE);
        for (Entity entity : world.getEntities()) {
            double[] shapex = entity.getShapeX();
            double[] shapey = entity.getShapeY();
            if (shapex != null && shapey != null && shapex.length > 0) {
                gc.strokePolygon(shapex, shapey, shapex.length);
            }
        }
    }
}

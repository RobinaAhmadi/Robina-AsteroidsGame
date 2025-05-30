package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            double x = entity.getX();
            double y = entity.getY();
            double dx = entity.getDx();
            double dy = entity.getDy();

            // Movement
            x += dx * gameData.getDelta();
            y += dy * gameData.getDelta();

            // Bounce off screen edges
            int screenWidth = gameData.getDisplayWidth();
            int screenHeight = gameData.getDisplayHeight();

            if (x < 0 || x > screenWidth) {
                dx *= -1;
                x = Math.max(0, Math.min(x, screenWidth));
            }

            if (y < 0 || y > screenHeight) {
                dy *= -1;
                y = Math.max(0, Math.min(y, screenHeight));
            }

            entity.setX(x);
            entity.setY(y);
            entity.setDx(dx);
            entity.setDy(dy);

            updateShape(entity);
        }
    }

    private void updateShape(Entity entity) {
        double[] shapex = new double[8];
        double[] shapey = new double[8];

        double x = entity.getX();
        double y = entity.getY();
        double radius = 20;
        double angle = 0;

        for (int i = 0; i < 8; i++) {
            shapex[i] = x + Math.cos(angle) * radius;
            shapey[i] = y + Math.sin(angle) * radius;
            angle += Math.PI / 4;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}

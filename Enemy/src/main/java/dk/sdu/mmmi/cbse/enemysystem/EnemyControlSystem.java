package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Entity player = findPlayer(world);

        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (player != null) {
                steerTowardPlayer(enemy, player, gameData);
            }

            double speed = 40;
            double dx = Math.cos(enemy.getRadians()) * speed * gameData.getDelta();
            double dy = Math.sin(enemy.getRadians()) * speed * gameData.getDelta();

            enemy.setX(enemy.getX() + dx);
            enemy.setY(enemy.getY() + dy);

            wrap(enemy, gameData);
            updateShape(enemy);
        }
    }

    private Entity findPlayer(World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass().getSimpleName().toLowerCase().contains("player")) {
                return e;
            }
        }
        return null;
    }

    private void steerTowardPlayer(Entity enemy, Entity player, GameData gameData) {
        double dx = player.getX() - enemy.getX();
        double dy = player.getY() - enemy.getY();
        double angleToPlayer = Math.atan2(dy, dx);
        enemy.setRadians(angleToPlayer);
    }

    private void wrap(Entity entity, GameData gameData) {
        if (entity.getX() < 0) entity.setX(gameData.getDisplayWidth());
        if (entity.getX() > gameData.getDisplayWidth()) entity.setX(0);
        if (entity.getY() < 0) entity.setY(gameData.getDisplayHeight());
        if (entity.getY() > gameData.getDisplayHeight()) entity.setY(0);
    }

    private void updateShape(Entity entity) {
        double[] shapeX = new double[4];
        double[] shapeY = new double[4];

        double x = entity.getX();
        double y = entity.getY();
        double r = entity.getRadius();
        double radians = entity.getRadians();

        shapeX[0] = x + Math.cos(radians) * r;
        shapeY[0] = y + Math.sin(radians) * r;

        shapeX[1] = x + Math.cos(radians - 4 * Math.PI / 5) * r;
        shapeY[1] = y + Math.sin(radians - 4 * Math.PI / 5) * r;

        shapeX[2] = x + Math.cos(radians + Math.PI) * (r / 2);
        shapeY[2] = y + Math.sin(radians + Math.PI) * (r / 2);

        shapeX[3] = x + Math.cos(radians + 4 * Math.PI / 5) * r;
        shapeY[3] = y + Math.sin(radians + 4 * Math.PI / 5) * r;

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}

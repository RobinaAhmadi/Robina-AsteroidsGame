package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);

        System.out.println("Enemy added at (" + enemy.getX() + ", " + enemy.getY() + ")");
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setX(Math.random() * gameData.getDisplayWidth());
        enemy.setY(Math.random() * gameData.getDisplayHeight());
        enemy.setRadians(Math.random() * 2 * Math.PI);
        enemy.setRadius(10);

        updateShape(enemy);
        return enemy;
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

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}

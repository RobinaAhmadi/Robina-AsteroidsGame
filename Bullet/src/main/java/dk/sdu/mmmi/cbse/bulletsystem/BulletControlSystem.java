package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            float x = (float) entity.getX();
            float y = (float) entity.getY();
            float dx = (float) entity.getDx();
            float dy = (float) entity.getDy();

            x += dx * gameData.getDelta();
            y += dy * gameData.getDelta();

            entity.setX(x);
            entity.setY(y);

            // Handle lifetime
            Bullet bullet = (Bullet) entity;
            bullet.setLifeTime((float) (bullet.getLifeTime() - gameData.getDelta()));
            if (bullet.getLifeTime() <= 0) {
                world.removeEntity(bullet);
                continue;
            }

            updateShape(entity);
        }
    }

    private void updateShape(Entity entity) {
        double x = entity.getX();
        double y = entity.getY();
        double radius = 2.0;

        double[] shapeX = new double[4];
        double[] shapeY = new double[4];

        shapeX[0] = x - radius;
        shapeY[0] = y - radius;

        shapeX[1] = x + radius;
        shapeY[1] = y - radius;

        shapeX[2] = x + radius;
        shapeY[2] = y + radius;

        shapeX[3] = x - radius;
        shapeY[3] = y + radius;

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}

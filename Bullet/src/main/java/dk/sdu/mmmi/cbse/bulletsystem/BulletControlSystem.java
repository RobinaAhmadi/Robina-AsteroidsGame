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
            bullet.setLifeTime((float)(bullet.getLifeTime() - gameData.getDelta()));
            if (bullet.getLifeTime() <= 0) {
                world.removeEntity(bullet);
            }

            updateShape(entity);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        float x = (float) entity.getX();
        float y = (float) entity.getY();
        double size = 4;

        shapex[0] = x - 1;
        shapey[0] = y - 1;

        shapex[1] = x + 1;
        shapey[1] = y - 1;

        shapex[2] = x + 1;
        shapey[2] = y + 1;

        shapex[3] = x - 1;
        shapey[3] = y + 1;

        double[] doubleShapeX = new double[shapex.length];
        double[] doubleShapeY = new double[shapey.length];

        for (int i = 0; i < shapex.length; i++) {
        doubleShapeX[i] = shapex[i];
        doubleShapeY[i] = shapey[i];
        }

        entity.setShapeX(doubleShapeX);
        entity.setShapeY(doubleShapeY);

    }
}

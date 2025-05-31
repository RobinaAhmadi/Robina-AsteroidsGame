package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {

    private float shootCooldown = 0f;
    private static final float SHOOT_INTERVAL = 0.25f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            double rotationSpeed = 3;
            double acceleration = 950;
            double deceleration = 10;

            double x = player.getX();
            double y = player.getY();
            double dx = player.getDx();
            double dy = player.getDy();
            double radians = player.getRadians();

            if (gameData.isDown(GameKeys.LEFT)) {
                radians += rotationSpeed * gameData.getDelta();
            }
            if (gameData.isDown(GameKeys.RIGHT)) {
                radians -= rotationSpeed * gameData.getDelta();
            }
            if (gameData.isDown(GameKeys.UP)) {
                dx += Math.cos(radians) * acceleration * gameData.getDelta();
                dy += Math.sin(radians) * acceleration * gameData.getDelta();
            }

            shootCooldown -= gameData.getDelta();
            if (gameData.isDown(GameKeys.SPACE) && shootCooldown <= 0) {
                Bullet bullet = new Bullet();
                bullet.setX((float) x);
                bullet.setY((float) y);
                bullet.setRadians((float) radians);
                bullet.setDx((float) (Math.cos(radians) * 300));
                bullet.setDy((float) (Math.sin(radians) * 300));
                bullet.setLifeTime(2);
                bullet.setRadius(2);
                world.addEntity(bullet);
                shootCooldown = SHOOT_INTERVAL;
            }

            x += dx * gameData.getDelta();
            y += dy * gameData.getDelta();
            dx *= 1 - deceleration * gameData.getDelta();
            dy *= 1 - deceleration * gameData.getDelta();

            if (x < 0) x = 0;
            if (x > gameData.getDisplayWidth()) x = gameData.getDisplayWidth();
            if (y < 0) y = 0;
            if (y > gameData.getDisplayHeight()) y = gameData.getDisplayHeight();

            player.setX((float) x);
            player.setY((float) y);
            player.setDx((float) dx);
            player.setDy((float) dy);
            player.setRadians((float) radians);

            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {
        double[] shapex = new double[4];
        double[] shapey = new double[4];

        double x = entity.getX();
        double y = entity.getY();
        double radians = entity.getRadians();

        shapex[0] = x + Math.cos(radians) * 8;
        shapey[0] = y + Math.sin(radians) * 8;

        shapex[1] = x + Math.cos(radians - 4 * Math.PI / 5) * 8;
        shapey[1] = y + Math.sin(radians - 4 * Math.PI / 5) * 8;

        shapex[2] = x + Math.cos(radians + Math.PI) * 5;
        shapey[2] = y + Math.sin(radians + Math.PI) * 5;

        shapex[3] = x + Math.cos(radians + 4 * Math.PI / 5) * 8;
        shapey[3] = y + Math.sin(radians + 4 * Math.PI / 5) * 8;

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}

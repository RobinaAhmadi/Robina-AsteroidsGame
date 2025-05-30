package dk.sdu.mmmi.cbse.enemybulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBullet;


public class EnemyBulletControlSystem implements IEntityProcessingService {

    private double shootCooldown = 2;
    private double timeSinceLastShot = 0;

    @Override
    public void process(GameData gameData, World world) {
        timeSinceLastShot += gameData.getDelta();

        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (timeSinceLastShot >= shootCooldown) {
                Entity bullet = new EnemyBullet();
                bullet.setX(enemy.getX());
                bullet.setY(enemy.getY());
                bullet.setRadians(enemy.getRadians());
                bullet.setRadius(2);

                world.addEntity(bullet);
                timeSinceLastShot = 0;
            }
        }

        for (Entity bullet : world.getEntities(EnemyBullet.class)) {
            double speed = 200;
            double dx = Math.cos(bullet.getRadians()) * speed * gameData.getDelta();
            double dy = Math.sin(bullet.getRadians()) * speed * gameData.getDelta();
            bullet.setX(bullet.getX() + dx);
            bullet.setY(bullet.getY() + dy);

            bullet.setShapeX(new double[]{bullet.getX() - 2, bullet.getX() + 2});
            bullet.setShapeY(new double[]{bullet.getY() - 2, bullet.getY() + 2});
        }
    }
}

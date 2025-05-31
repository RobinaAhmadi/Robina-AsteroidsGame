package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroidssystem.Asteroid;
import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBullet;
import dk.sdu.mmmi.cbse.common.data.ScoreClient;


public class CollisionSystem implements IPostEntityProcessingService {

    private final ScoreClient scoreClient = new ScoreClient(); 

    @Override
    public void process(GameData gameData, World world) {
        // Bullet vs Asteroid
        for (Entity bullet : world.getEntities(Bullet.class)) {
            for (Entity asteroid : world.getEntities(Asteroid.class)) {
                if (collides(bullet, asteroid)) {
                    world.removeEntity(bullet);
                    world.removeEntity(asteroid);
                    gameData.addScore(100);
                    scoreClient.addScore(100); // Sync to microservice
                    return;
                }
            }
        }

        // Bullet vs Enemy
        for (Entity bullet : world.getEntities(Bullet.class)) {
            for (Entity entity : world.getEntities()) {
                if (entity.getClass().getSimpleName().equals("Enemy") && collides(bullet, entity)) {
                    world.removeEntity(bullet);
                    world.removeEntity(entity);
                    gameData.addScore(200);
                    scoreClient.addScore(200); // Sync to microservice
                    return;
                }
            }
        }

        // EnemyBullet vs Player
        Entity player = world.getEntities().stream()
                .filter(e -> e.getClass().getSimpleName().equals("Player"))
                .findFirst().orElse(null);

        for (Entity bullet : world.getEntities(EnemyBullet.class)) {
            if (player != null && collides(player, bullet)) {
                System.out.println("💀 GAME OVER 💀");
                gameData.setGameOver(true);
                world.removeEntity(player);
                world.removeEntity(bullet);
                break;
            }
        }
    }

    private boolean collides(Entity a, Entity b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy) < 15;
    }
}

package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroidssystem.Asteroid;
import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollisionSystemTest {

    private CollisionSystem collisionSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setup() {
        collisionSystem = new CollisionSystem();
        gameData = new GameData();
        world = new World();
    }

    @Test
    public void testBulletAsteroidCollisionRemovesEntities() {
        Bullet bullet = new Bullet();
        bullet.setRadius(10);
        bullet.setX(100);
        bullet.setY(100);

        Asteroid asteroid = new Asteroid();
        asteroid.setRadius(10);
        asteroid.setX(105);
        asteroid.setY(105);

        world.addEntity(bullet);
        world.addEntity(asteroid);

        collisionSystem.process(gameData, world);

        assertEquals(0, world.getEntities().size(), "Entities should be removed after collision");
    }
}

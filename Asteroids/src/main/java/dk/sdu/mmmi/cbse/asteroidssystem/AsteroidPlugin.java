package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Asteroid.class).forEach(world::removeEntity);
    }

    private Entity createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid(Asteroid.Size.LARGE);
        asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(random.nextInt(gameData.getDisplayHeight()));
        asteroid.setDx(random.nextDouble() * 40 - 20);
        asteroid.setDy(random.nextDouble() * 40 - 20);
        asteroid.setRadians(random.nextDouble() * 2 * Math.PI);
        return asteroid;
    }
}

package dk.sdu.mmmi.cbse.core;

import dk.sdu.mmmi.cbse.asteroidssystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidssystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBulletControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import dk.sdu.mmmi.cbse.common.data.ScoreClient;
import dk.sdu.mmmi.cbse.common.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    public IGamePluginService playerPlugin() {
        return new PlayerPlugin();
    }

    @Bean
    public IGamePluginService bulletPlugin() {
        return new BulletPlugin();
    }

    @Bean
    public IGamePluginService asteroidPlugin() {
        return new AsteroidPlugin();
    }

    @Bean
    public IGamePluginService enemyPlugin() {
        return new EnemyPlugin();
    }

    @Bean
    public IEntityProcessingService playerControlSystem() {
        return new PlayerControlSystem();
    }

    @Bean
    public IEntityProcessingService bulletControlSystem() {
        return new BulletControlSystem();
    }

    @Bean
    public IEntityProcessingService asteroidControlSystem() {
        return new AsteroidControlSystem();
    }

    @Bean
    public IEntityProcessingService enemyControlSystem() {
        return new EnemyControlSystem();
    }

    @Bean
    public IEntityProcessingService enemyBulletControlSystem() {
        return new EnemyBulletControlSystem();
    }

    @Bean
    public IPostEntityProcessingService collisionSystem() {
        return new CollisionSystem();
    }

    @Bean
    public ScoreClient scoreClient() {
        return new ScoreClient();
    }
}

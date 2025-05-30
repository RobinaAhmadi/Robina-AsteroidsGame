module Core {
    requires Common;         // Shared base entities and services
    requires Player;         // Player logic
    requires Bullet;         // Bullet behavior
    requires Asteroids;      // Asteroid behavior
    requires Collision;      // Collision detection
    requires Enemy;          // Enemies
    requires EnemyBullet;    // Bullets fired by enemies
    requires javafx.controls; // JavaFX UI

    opens dk.sdu.mmmi.cbse.core to javafx.graphics; // Allows JavaFX to access main class
}

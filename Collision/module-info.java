module Collision {
    requires Common;
    requires Bullet;
    requires Asteroids;
    requires Enemy;        
    requires EnemyBullet;   

    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService with
            dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
}

module EnemyBullet {
    requires Common;
    requires Enemy;

    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBulletControlSystem;
}

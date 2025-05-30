module EnemyBullet {
    requires Common;
    requires Enemy;
    
    exports dk.sdu.mmmi.cbse.enemybulletsystem;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBulletPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.enemybulletsystem.EnemyBulletControlSystem;
}

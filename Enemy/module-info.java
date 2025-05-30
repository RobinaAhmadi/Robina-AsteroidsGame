module Enemy {
    requires Common;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
}

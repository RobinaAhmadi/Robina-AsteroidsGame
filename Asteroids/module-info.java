module Asteroids {
    requires Common;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.asteroidssystem.AsteroidPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.asteroidssystem.AsteroidControlSystem;
}

module Bullet {
    requires Common;

    // Required if you're using JavaFX inside Bullet logic
    requires javafx.graphics;

    // Export Bullet system package
    exports dk.sdu.mmmi.cbse.bulletsystem;

    // Declare bullet system as a service implementation if needed
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService
        with dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService
        with dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
}

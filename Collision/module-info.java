module Collision {
    requires Common;

    exports dk.sdu.mmmi.cbse.collisionsystem;

    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService
        with dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
}

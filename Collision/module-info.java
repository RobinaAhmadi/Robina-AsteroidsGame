module Collision {
    requires Common;

    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
}

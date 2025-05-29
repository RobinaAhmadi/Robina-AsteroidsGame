module Collision {
    requires Common;

    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionControlSystem;
}

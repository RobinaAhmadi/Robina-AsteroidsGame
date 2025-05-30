package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class CollisionControlSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> toRemove = new ArrayList<>();

        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (!e1.getID().equals(e2.getID()) && isColliding(e1, e2)) {
                    toRemove.add(e1);
                    toRemove.add(e2);
                }
            }
        }

        for (Entity e : toRemove) {
            world.removeEntity(e);
        }
    }

    private boolean isColliding(Entity e1, Entity e2) {
        // Placeholder logic: always false unless you implement radius, shape, or bounding box logic
        return false;
    }
}

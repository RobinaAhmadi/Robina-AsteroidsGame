package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<Entity> entities = new ArrayList<>();

    public List<Entity> getEntities() {
        return entities;
    }

    public <E extends Entity> List<Entity> getEntities(Class<E> entityClass) {
        List<Entity> result = new ArrayList<>();
        for (Entity e : entities) {
            if (entityClass.isInstance(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }
}

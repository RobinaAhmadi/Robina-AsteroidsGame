package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.GamePluginService;

public class PlayerPlugin implements GamePluginService {
    @Override
    public void start(GameData gameData, Entity entity) {
        System.out.println("PlayerPlugin started");
        entity.setX(100);
        entity.setY(100);
        entity.setRadius(10);
    }

    @Override
    public void stop(GameData gameData, Entity entity) {
        System.out.println("PlayerPlugin stopped");
    }
}

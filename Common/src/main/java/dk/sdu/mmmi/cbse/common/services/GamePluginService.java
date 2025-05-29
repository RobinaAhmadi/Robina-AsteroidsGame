package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Entity;

public interface GamePluginService {
    void start(GameData gameData, Entity entity);
    void stop(GameData gameData, Entity entity);
}

package dk.sdu.mmmi.cbse.enemybulletsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyBulletPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // Bullets are spawned dynamically by EnemyBulletControlSystem
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Cleanup if needed (none in this case)
    }
}

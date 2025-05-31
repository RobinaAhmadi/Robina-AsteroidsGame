package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPluginTest {

    private PlayerPlugin playerPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    void setUp() {
        playerPlugin = new PlayerPlugin();
        gameData = new GameData();
        world = new World();
    }

    @Test
    void testPlayerAddedOnStart() {
        playerPlugin.start(gameData, world);
        long playerCount = world.getEntities().stream()
                .filter(e -> e.getClass().getSimpleName().equals("Player"))
                .count();

        assertEquals(1, playerCount, "One Player should be added to the world");
    }
}

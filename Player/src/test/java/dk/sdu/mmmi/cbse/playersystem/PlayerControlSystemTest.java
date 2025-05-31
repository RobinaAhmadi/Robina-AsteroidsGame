package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerControlSystemTest {

    private GameData gameData;
    private World world;
    private PlayerControlSystem playerControlSystem;
    private Entity player;

    @BeforeEach
    public void setUp() {
        gameData = new GameData();
        gameData.setDelta(0.1f); // simulate frame time
        world = new World();

        player = new Player();
        player.setX(100f);           // explicitly float
        player.setY(100f);           // explicitly float
        player.setRotation(0f);      // explicitly float

        world.addEntity(player);
        playerControlSystem = new PlayerControlSystem();

        gameData.addKey(GameKeys.UP); // simulate UP key pressed
    }

    @Test
    public void testPlayerMovesForwardWhenUpPressed() {
    float beforeX = (float) player.getX();
    float beforeY = (float) player.getY();

    playerControlSystem.process(gameData, world);

    float afterX = (float) player.getX();
    float afterY = (float) player.getY();

    assertTrue(afterX != beforeX || afterY != beforeY,
            "Player should move forward when UP is pressed");
}

}

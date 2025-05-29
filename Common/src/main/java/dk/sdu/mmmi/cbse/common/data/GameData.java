package dk.sdu.mmmi.cbse.common.data;

import java.util.HashSet;
import java.util.Set;

public class GameData {
    private int displayWidth;
    private int displayHeight;
    private double delta;
    private final Set<String> keys = new HashSet<>();

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    // 🔥 These are required for keyboard handling!
    public void addKey(String key) {
        keys.add(key);
    }

    public void removeKey(String key) {
        keys.remove(key);
    }

    public boolean isDown(String key) {
        return keys.contains(key);
    }

    public boolean isKeyPressed(String space) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isKeyPressed'");
    }
}

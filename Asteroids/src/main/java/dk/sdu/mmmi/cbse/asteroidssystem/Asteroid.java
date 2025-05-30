package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
    public enum Size { LARGE, MEDIUM, SMALL }

    private Size size;

    public Asteroid(Size size) {
        this.size = size;
        setRadius(size == Size.LARGE ? 30 : size == Size.MEDIUM ? 20 : 10);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}

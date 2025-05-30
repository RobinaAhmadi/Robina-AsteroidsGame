package dk.sdu.mmmi.cbse.bulletsystem;


import dk.sdu.mmmi.cbse.common.data.Entity;

public class Bullet extends Entity {
    private float lifeTime = 2f;

    public float getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }
}

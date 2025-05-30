package dk.sdu.mmmi.cbse.common.data;

public class LifePart {
    private int life;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void reduceLife() {
        life--;
    }
}

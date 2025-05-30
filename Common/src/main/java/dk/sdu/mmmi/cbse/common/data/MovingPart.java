package dk.sdu.mmmi.cbse.common.data;

public class MovingPart {
    private boolean left, right, up;
    private double acceleration, deceleration, maxSpeed;
    private double dx, dy;

    public MovingPart(double acceleration, double deceleration, double maxSpeed) {
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.maxSpeed = maxSpeed;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void process(GameData gameData, Entity entity) {
        double radians = entity.getRadians();

        if (left) radians += 3 * gameData.getDelta();
        if (right) radians -= 3 * gameData.getDelta();
        if (up) {
            dx += Math.cos(radians) * acceleration * gameData.getDelta();
            dy += Math.sin(radians) * acceleration * gameData.getDelta();
        }

        dx *= 1 - deceleration * gameData.getDelta();
        dy *= 1 - deceleration * gameData.getDelta();

        entity.setRadians(radians);
        entity.setDx(dx);
        entity.setDy(dy);
    }
}

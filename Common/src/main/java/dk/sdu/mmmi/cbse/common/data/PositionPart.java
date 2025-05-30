package dk.sdu.mmmi.cbse.common.data;

public class PositionPart {
    private double x, y, radians;

    public PositionPart(double x, double y, double radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
    }

    public void process(GameData gameData, Entity entity) {
        x += entity.getDx() * gameData.getDelta();
        y += entity.getDy() * gameData.getDelta();

        entity.setX(x);
        entity.setY(y);
        entity.setRadians(radians);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }
}

package dk.sdu.mmmi.cbse.common.data;

import java.util.UUID;

public class Entity {

    private final UUID id = UUID.randomUUID();

    private double x, y;
    private double dx, dy;
    private double radians;
    private double rotation;
    private double radius;

    private double[] shapeX;
    private double[] shapeY;

    public UUID getID() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(double[] shapeX) {
        this.shapeX = shapeX;
    }

    public double[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(double[] shapeY) {
        this.shapeY = shapeY;
    }
}

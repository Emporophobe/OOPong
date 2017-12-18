package Model;

import javafx.geometry.Point2D;

public abstract class APhysical implements IPhysical {
    private Point2D topLeft;
    private Point2D velocity;

    private int width;
    private int height;

    protected int getTop() {
        return (int)topLeft.getY();
    }

    protected int getBottom() {
        return (int)topLeft.getY() + height;
    }

    protected int getLeft() {
        return (int)topLeft.getX();
    }

    protected int getRight() {
        return (int)topLeft.getX() + width;
    }

    private void boundPosition(int maxX, int maxY) {
        if (getTop() < 0) {
            topLeft = new Point2D(topLeft.getX(),0);
        }
        if (getBottom() > maxY) {
            topLeft = new Point2D(topLeft.getX(), maxY);
        }
        if (getLeft() < 0) {
            topLeft = new Point2D(0, topLeft.getY());
        }
        if (getRight() > maxY) {
            topLeft = new Point2D(maxX, topLeft.getY());
        }
    }

    protected void move(double xVelocity, double yVelocity, int maxX, int maxY) {
        topLeft = new Point2D(topLeft.getX() + xVelocity, topLeft.getY() + yVelocity);
        boundPosition(maxX, maxY);
    }

    @Override
    public abstract void onTick();
}

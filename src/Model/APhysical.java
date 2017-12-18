package Model;

import javafx.geometry.Point2D;

public abstract class APhysical implements IPhysical {
    private Point2D topLeft;
    private Point2D velocity;

    private int width;
    private int height;

    APhysical(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        setVelocity(0, 0);
        setTopLeft(x, y);
    }

    private void setTopLeft(int x, int y) {
        topLeft = new Point2D(x, y);
    }

    public void setVelocity(double dx, double dy) {
        velocity = new Point2D(dx, dy);
    }

    Point2D getVelocity() {
        return velocity;
    }

    public int getTop() {
        return (int)topLeft.getY();
    }

    public int getBottom() {
        return (int)topLeft.getY() + height;
    }

    public int getLeft() {
        return (int)topLeft.getX();
    }

    public int getRight() {
        return (int)topLeft.getX() + width;
    }

    public Point2D getTopLeft() {
        return topLeft;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    void boundPosition(int maxX, int maxY) {
        if (getTop() < 0) {
            topLeft = new Point2D(topLeft.getX(),0);
        }
        if (getBottom() > maxY) {
            topLeft = new Point2D(topLeft.getX(), maxY - height);
        }
        if (getLeft() < 0) {
            topLeft = new Point2D(0, topLeft.getY());
        }
        if (getRight() > maxX) {
            topLeft = new Point2D(maxX - width, topLeft.getY());
        }
    }

    public boolean overlaps(IPhysical other) {
        return (this.getLeft() < other.getRight() &&
                this.getRight() > other.getLeft() &&
                this.getTop() < other.getBottom() &&
                this.getBottom() > other.getTop());
    }

    void move(double xVelocity, double yVelocity) {
        topLeft = new Point2D(topLeft.getX() + xVelocity, topLeft.getY() + yVelocity);
    }

    @Override
    public abstract void onTick(Game g);
}

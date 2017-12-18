package Model;

import javafx.geometry.Point2D;

public class Paddle extends APhysical {
    private Point2D velocity;

    Paddle(int width, int height, int x, int y) {
        super(width, height, x, y);
    }

    public void setVelocity(double dx, double dy) {
        velocity = new Point2D(dx, dy);
    }

    @Override
    public void onTick(Game g) {
        move(velocity.getX(), velocity.getY());
        boundPosition(g.getWidth(), g.getHeight());
    }
}

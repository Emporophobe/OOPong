package Model;

import javafx.geometry.Point2D;

public class Paddle extends APhysical {

    Paddle(int width, int height, int x, int y) {
        super(width, height, x, y);
    }

    @Override
    public void onTick(Game g) {
        Point2D velocity = getVelocity();
        move(velocity.getX(), velocity.getY());
        boundPosition(g.getWidth(), g.getHeight());
    }
}

package Model;

import app.PropertiesManager;
import javafx.geometry.Point2D;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Ball extends APhysical {

    Ball(int width, int height, int x, int y) {
        super(width, height, x, y);
        int speed = PropertiesManager.getIntProperty("ball_speed");

        Random r = new Random();
        double angle = 360 * r.nextDouble();

        setVelocity(speed * Math.sin(angle),
                speed * Math.cos(angle));
    }

    private void bounce(Game g) {
        int left1 = this.getLeft();
        int right1 = this.getRight();
        int top1 = this.getTop();
        int bottom1 = this.getBottom();

        Point2D velocity = getVelocity();
        double dx = velocity.getX();
        double dy = velocity.getY();

        Collection<IPhysical> collidables = new LinkedList<>();
        collidables.addAll(g.getPaddles());

        if (PropertiesManager.getBooleanProperty("balls_collide")) {
            collidables.addAll(g.getBalls());
        }

        for (IPhysical that : collidables) {
            if (this.overlaps(that)) {
                int left2 = that.getLeft();
                int right2 = that.getRight();
                int top2 = that.getTop();
                int bottom2 = that.getBottom();

                Point2D velocity2 = that.getVelocity();
                if ((left1 < right2 && left1 > left2) ||
                        (right1 < right2 && right1 > left2)) {
                    dx = -dx + velocity2.getX();
                    dy += velocity2.getY();
                }
                else if ((top1 < bottom2 && top1 > top2) ||
                        (bottom1 > top2 && bottom1 < bottom2)) {
                    dy = -dy + velocity2.getY();
                    dx += velocity2.getX();
                }
            }
        }
        if (top1 <= 0 || bottom1 >= g.getHeight()) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
    }

    @Override
    public void onTick(Game g) {
        Point2D velocity = getVelocity();
        move(velocity.getX(), velocity.getY());
        bounce(g);
    }
}

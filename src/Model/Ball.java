package Model;

import javafx.geometry.Point2D;

public class Ball extends APhysical {

    private static int counter = 0;

    Ball(int width, int height, int x, int y) {
        super(width, height, x, y);
        if (counter % 2 == 0) {
            setVelocity(5, 2);
        } else {
            setVelocity(-5, 2);
        }
        counter++;
    }

    private void bounce(Game g) {
        int left1 = this.getLeft();
        int right1 = this.getRight();
        int top1 = this.getTop();
        int bottom1 = this.getBottom();

        Point2D velocity = getVelocity();
        double dx = velocity.getX();
        double dy = velocity.getY();

        for (Paddle that : g.getPaddles()) {
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

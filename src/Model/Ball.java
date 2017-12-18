package Model;

import javafx.geometry.Point2D;

public class Ball extends APhysical {

    Ball(int width, int height, int x, int y) {
        super(width, height, x, y);
        setVelocity(5, 2);
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

                if ((left1 < right2 && left1 > left2) ||
                        (right1 < right2 && right1 > left2)) {
                    dx = -dx + that.getVelocity().getX();
                }
                else if ((top1 < bottom2 && top1 > top2) ||
                        (bottom1 > top2 && bottom1 < bottom2)) {
                    dy = -dy + that.getVelocity().getY();
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

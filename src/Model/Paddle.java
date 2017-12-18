package Model;

public class Paddle extends APhysical {
    private double xVelocity;

    Paddle(int width, int height, int x, int y) {
        super(width, height, x, y);
    }

    public void setxVelocity(double velocity) {
        xVelocity = velocity;
    }

    @Override
    public void onTick(Game g) {
        move(xVelocity, 0);
        boundPosition(g.getWidth(), g.getHeight());
    }
}

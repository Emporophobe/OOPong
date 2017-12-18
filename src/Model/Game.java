package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Paddle> paddles = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private int width;
    private int height;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.initializeGame();
    }

    private void initializeGame() {
        int paddleWidth = 20;
        int paddleHeight = 100;

        int distanceFromEdge = 50;

        paddles.clear();
        paddles.add(new Paddle(paddleWidth, paddleHeight, distanceFromEdge, height / 2));
        paddles.add(new Paddle(paddleWidth, paddleHeight, width - paddleWidth - distanceFromEdge, height / 2));

        balls.clear();
        balls.add(new Ball(20, 20, width / 2, height / 2));
    }

    public void onTick() {
        paddles.forEach(p -> p.onTick(this));
        balls.forEach(b -> b.onTick(this));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Paddle> getPaddles() {
        return paddles;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setPaddleVelocity(int playernumber, double dx, double dy) {
        paddles.get(playernumber).setVelocity(dx, dy);
    }

    public void reset() {
        initializeGame();
    }
}

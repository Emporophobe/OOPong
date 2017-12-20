package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Paddle> paddles = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private int width;
    private int height;

    private int players;
    private int maxScore;
    private List<Integer> scores = new ArrayList<>();

    public Game(int width, int height, int players, int maxScore) {
        this.width = width;
        this.height = height;
        this.players = players;
        this.maxScore = maxScore;
        this.initializeGame();
    }

    private void initializeGame() {
        int paddleWidth = 20;
        int paddleHeight = 100;

        int distanceFromEdge = 50;

        paddles.clear();
        paddles.add(new Paddle(paddleWidth, paddleHeight, distanceFromEdge, height / 2));
        paddles.add(new Paddle(paddleWidth, paddleHeight, width - paddleWidth - distanceFromEdge, height / 2));

        scores.clear();
        for (int i = 0; i < players; i++) {
            scores.add(0);
        }

        balls.clear();
        balls.add(new Ball(20, 20, width / 2, height / 2));
    }

    private void score() {
        Set<Ball> toRemove = new HashSet<>();
        for (Ball ball : balls) {
            if (ball.getRight() < 0 && players >= 1) {
                scores.set(0, scores.get(0) + 1);
                toRemove.add(ball);
            } else if (ball.getLeft() > width && players >= 2) {
                scores.set(1, scores.get(1) + 1);
                toRemove.add(ball);
            }
        }
        balls.removeAll(toRemove);
    }

    public boolean isOver() {
        return scores.stream().anyMatch(score -> score >= maxScore);
    }

    public void onTick() {
        if (!isOver()) {
            paddles.forEach(p -> p.onTick(this));
            balls.forEach(b -> b.onTick(this));
            score();
        }
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

    public List<Integer> getScores() {
        return scores;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setPaddleVelocity(int playernumber, double dx, double dy) {
        paddles.get(playernumber).setVelocity(dx, dy);
    }

    public void addBall() {
        if (!isOver()) {
            balls.add(new Ball(20, 20, width / 2, height / 2));
        }
    }

    public void reset() {
        initializeGame();
    }
}

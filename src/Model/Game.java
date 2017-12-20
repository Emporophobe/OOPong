package Model;

import app.PropertiesManager;

import java.util.*;

public class Game {
    private List<Paddle> paddles = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
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
        this.reset();
    }

    private void initializePongGame() {
        int paddleWidth = PropertiesManager.getIntProperty("paddle_width");
        int paddleHeight = PropertiesManager.getIntProperty("paddle_height");

        int distanceFromEdge = 50;

        paddles.clear();
        paddles.add(new Paddle(paddleWidth, paddleHeight, distanceFromEdge, height / 2));

        if (players >= 2) {
            paddles.add(new Paddle(paddleWidth, paddleHeight, width - paddleWidth - distanceFromEdge, height / 2));
        }

        scores.clear();
        for (int i = 0; i < players; i++) {
            scores.add(0);
        }

        balls.clear();
        balls.add(new Ball(20, 20, width / 2, height / 2));
    }

    private void initializeBreakoutGame() {
        int paddleWidth = PropertiesManager.getIntProperty("paddle_width");
        int paddleHeight = PropertiesManager.getIntProperty("paddle_height");

        int paddleDistanceFromEdge = 100;

        paddles.clear();
        paddles.add(new Paddle(paddleWidth, paddleHeight, paddleDistanceFromEdge, height / 2));
        if (players >= 2) {
            paddles.add(new Paddle(paddleWidth, paddleHeight, width - paddleWidth - paddleDistanceFromEdge, height / 2));
        }
        int blockDistanceFromEdge = 20;
        int blockWidth = PropertiesManager.getIntProperty("block_width");
        int blockHeight = PropertiesManager.getIntProperty("block_height");

        blocks.clear();
        for (int i = 0; i < height / blockHeight; i++) {
            blocks.add(new Block(blockWidth, blockHeight - 1, width - blockWidth - blockDistanceFromEdge, i * blockHeight + i));
        }

        if (PropertiesManager.getIntProperty("players") >= 2) {
            for (int i = 0; i < height / blockHeight; i++) {
                blocks.add(new Block(blockWidth, blockHeight - 1, blockDistanceFromEdge, i * blockHeight + i));
            }
        }

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
            blocks.forEach(b -> b.onTick(this));
            handleBlocks();
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

    public List<Block> getBlocks() {
        return blocks;
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
        if (!isOver() && balls.size() < PropertiesManager.getIntProperty("max_balls")) {
            int size = PropertiesManager.getIntProperty("ball_size");
            balls.add(new Ball(size, size, width / 2, height / 2));
        }
    }

    private void handleBlocks() {
        Collection<Block> toRemove = new ArrayList<>();
        for (Block block : blocks) {
            if (balls.stream().anyMatch(b -> b.overlaps(block))) {
                toRemove.add(block);
            }
        }
        blocks.removeAll(toRemove);
    }

    public void reset() {
        if (PropertiesManager.getBooleanProperty("breakout")) {
            initializeBreakoutGame();
        } else {
            initializePongGame();
        }
    }
}

package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<IPhysical> paddles = new ArrayList<>();
    private int width;
    private int height;

    public Game(int width, int height) {
        this.initializeGame();
        this.width = width;
        this.height = height;
    }

    private void initializeGame() {
        paddles.clear();
        paddles.add(new Paddle(20, 100, 50, 400));
        paddles.add(new Paddle(20, 100, 550, 400));
    }

    public void onTick() {
        paddles.forEach(p -> p.onTick(this));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<IPhysical> getPaddles() {
        return paddles;
    }
}

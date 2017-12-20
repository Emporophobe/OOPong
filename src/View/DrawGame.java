package View;

import Model.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class DrawGame {
    private static void drawScores(Game g, GraphicsContext gc) {
        List<Integer> scores = g.getScores();
        gc.setFill(Color.color(0.5, 0.6, 0.7));
        gc.setFont(Font.font(36));
        int playerCount = scores.size();
        int margin = 30;
        int width = g.getWidth();
        int height = g.getHeight();
        if (playerCount >= 1) {
            gc.fillText(scores.get(0).toString(), margin, margin);
        }
        if (playerCount >= 2) {
            gc.fillText(scores.get(1).toString(), width - margin, margin);
        }
        if (playerCount >= 3) {
            gc.fillText(scores.get(2).toString(), margin, height - margin);
        }
        if (playerCount >= 4) {
            gc.fillText(scores.get(3).toString(), width - margin, height - margin);
        }

        if (g.isOver()) {
            int maxScore = g.getMaxScore();
            for (int i = 0; i < playerCount; i++) {
                if (scores.get(i) >= maxScore) {
                    gc.fillText(String.format("Player %d won!", i + 1), width / 2, height / 2);
                }
            }
        }
    }

    public static void draw(Game g, GraphicsContext gc) {
        gc.setFill(Color.color(0.16, 0.16, 0.18));
        gc.fillRect(0, 0, g.getWidth(), g.getHeight());
        g.getPaddles().forEach(p -> DrawIPhysical.draw(p, gc));
        g.getBalls().forEach(b -> DrawIPhysical.draw(b, gc));
        g.getBlocks().forEach(b -> DrawIPhysical.draw(b, gc));
        drawScores(g, gc);
    }
}

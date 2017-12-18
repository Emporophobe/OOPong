package View;

import Model.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawGame {
    public static void draw(Game g, GraphicsContext gc) {
        gc.setFill(Color.color(0.16, 0.16, 0.18));
        gc.fillRect(0, 0, g.getWidth(), g.getHeight());
        g.getPaddles().forEach(p -> DrawIPhysical.draw(p, gc));
    }
}

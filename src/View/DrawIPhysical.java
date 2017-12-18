package View;

import Model.IPhysical;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.geometry.Point2D;

public class DrawIPhysical {
    public static void draw(IPhysical p, GraphicsContext gc) {
        gc.setFill(Color.color(0.7, 0.7, 0.99));
        Point2D topLeft = p.getTopLeft();
        gc.fillRect(topLeft.getX(), topLeft.getY(), p.getWidth(), p.getHeight());
    }
}

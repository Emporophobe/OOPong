package Model;

import javafx.geometry.Point2D;

public interface IPhysical {
    void onTick(Game g);

    Point2D getTopLeft();
    int getWidth();
    int getHeight();
}

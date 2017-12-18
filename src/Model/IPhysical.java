package Model;

import javafx.geometry.Point2D;

public interface IPhysical {
    void onTick(Game g);

    boolean overlaps(IPhysical other);

    Point2D getTopLeft();

    int getWidth();
    int getHeight();

    int getTop();
    int getBottom();
    int getLeft();
    int getRight();
}

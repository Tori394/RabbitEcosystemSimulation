package Model;

import java.awt.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int size;
    protected Color color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}

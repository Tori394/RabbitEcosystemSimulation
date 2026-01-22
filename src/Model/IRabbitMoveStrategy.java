package Model;

import java.awt.Point;
import java.util.List;

public interface IRabbitMoveStrategy {
    Point calculateNextMove(Rabbit rabbit, List<Carrot> carrots, int gridSize);
}


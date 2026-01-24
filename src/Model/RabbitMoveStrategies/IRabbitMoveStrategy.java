package Model.RabbitMoveStrategies;

import Model.Entities.Carrot;
import Model.Entities.Rabbit;

import java.awt.Point;
import java.util.List;

public interface IRabbitMoveStrategy {
    Point calculateNextMove(Rabbit rabbit, List<Carrot> carrots, Carrot[][] carrotMap, Rabbit[][] rabbitMates, int gridSize);
}


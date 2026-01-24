package Model.RabbitMoveStrategies;

import Model.Entities.Carrot;
import Model.Entities.Rabbit;

import java.awt.Point;
import java.util.List;
import java.util.Random;

public class RandomMoveStrategy implements IRabbitMoveStrategy {
    private Random rn = new Random();

    @Override
    public Point calculateNextMove(Rabbit rabbit, List<Carrot> carrots, Carrot[][] carrotMap, Rabbit[][] rabbitMates, int gridSize) {
        int currentX = rabbit.getX();
        int currentY = rabbit.getY();

        // -1 (lewo/góra), 0 (bez zmian), 1 (prawo/dół)
        int dx = rn.nextInt(3) - 1;
        int dy = rn.nextInt(3) - 1;

        int newX = currentX + dx;
        int newY = currentY + dy;

        if (newX < 0) newX = 0;
        if (newX >= gridSize) newX = gridSize - 1;
        if (newY < 0) newY = 0;
        if (newY >= gridSize) newY = gridSize - 1;

        return new Point(newX, newY);
    }
}

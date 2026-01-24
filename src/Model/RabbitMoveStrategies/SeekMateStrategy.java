package Model.RabbitMoveStrategies;

import Model.Entities.Carrot;
import Model.Entities.Rabbit;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class SeekMateStrategy implements IRabbitMoveStrategy {
    private final Random rn = new Random();

    private Point findMate(int x, int y, Rabbit[][] rabbitMates, int gridSize) {
        int[][] visionField = {
                // --- KRĄG 1 (Najbliższe) ---
                {0, -1}, {0, 1}, {-1, 0}, {1, 0},           // Góra, Dół, Lewo, Prawo
                {-1, -1}, {1, -1}, {-1, 1}, {1, 1},         // Skosy (rogi 1x1)

                // --- KRĄG 2 ---
                {0, -2}, {0, 2}, {-2, 0}, {2, 0},           // Kardynalne 2
                {-1, -2}, {1, -2}, {-2, -1}, {2, -1},       // Skoczki (Góra/Dół)
                {-1, 2}, {1, 2}, {-2, 1}, {2, 1},           // Skoczki (Dół/Boki)
                {-2, -2}, {2, -2}, {-2, 2}, {2, 2},         // Skosy (rogi 2x2)

                // --- KRĄG 3 ---
                {0, -3}, {0, 3}, {-3, 0}, {3, 0},           // Kardynalne 3
                {-1, -3}, {1, -3}, {-3, -1}, {3, -1},       // Wypełnienie koła 3
                {-1, 3}, {1, 3}, {-3, 1}, {3, 1},
                {-2, -3}, {2, -3}, {-3, -2}, {3, -2},       // Szersze wypełnienie
                {-2, 3}, {2, 3}, {-3, 2}, {3, 2},
                {-3, -3}, {3, -3}, {-3, 3}, {3, 3},         // Skosy (rogi 3x3)

                // --- KRĄG 4 ---
                {0, -4}, {0, 4}, {-4, 0}, {4, 0},           // Kardynalne 4
                {-1, -4}, {1, -4}, {-4, -1}, {4, -1},       // Wypełnienie koła 4
                {-1, 4}, {1, 4}, {-4, 1}, {4, 1},
                {-2, -4}, {2, -4}, {-4, -2}, {4, -2},
                {-2, 4}, {2, 4}, {-4, 2}, {4, 2},
                {-3, -4}, {3, -4}, {-4, -3}, {4, -3},       // Szersze
                {-3, 4}, {3, 4}, {-4, 3}, {4, 3},

                // --- KRĄG 5 (Najdalsze) ---
                {0, -5}, {0, 5}, {-5, 0}, {5, 0},           // Kardynalne 5
                {-1, -5}, {1, -5}, {-5, -1}, {5, -1},       // Wypełnienie koła 5
                {-1, 5}, {1, 5}, {-5, 1}, {5, 1},
                {-2, -5}, {2, -5}, {-5, -2}, {5, -2},
                {-2, 5}, {2, 5}, {-5, 2}, {5, 2},
                {-3, -5}, {3, -5}, {-5, -3}, {5, -3},
                {-3, 5}, {3, 5}, {-5, 3}, {5, 3},
                {-4, -5}, {4, -5}, {-5, -4}, {5, -4},       // Najszersze brzegi koła
                {-4, 5}, {4, 5}, {-5, 4}, {5, 4}
        };

        for (int[] offset : visionField) {
            int targetX = x + offset[0];
            int targetY = y + offset[1];
            if (targetX >= 0 && targetX < gridSize && targetY >= 0 && targetY < gridSize) {
                if (rabbitMates[targetX][targetY] != null) {
                    return new Point(targetX, targetY);
                }
            }
        }

        return null;
    }

    private Point getDirection(int x, int y, Point target) {
        // TIE-BREAKER: Jeśli sąsiedzi, jeden musi stać, a drugi iść
        int dist = Math.max(Math.abs(target.x - x), Math.abs(target.y - y));

        if (dist == 1) {
            // Zasada pierwszeństwa: Rusza się ten z większymi współrzędnymi
            boolean amITheMover = (x > target.x) || (x == target.x && y > target.y);

            if (!amITheMover) {
                return new Point(x, y); // Czekam, aż partner wejdzie na mnie
            }
        }

        int newX, newY;

        newX = (target.x - x);
        if (newX != 0) {
            newX = newX/Math.abs(newX);
        }

        newY = (target.y - y);
        if (newY != 0) {
            newY = newY/Math.abs(newY);
        }

        return new Point(x+newX, y+newY);
    }

    @Override
    public Point calculateNextMove(Rabbit rabbit, List<Carrot> carrots, Carrot[][] carrotMap, Rabbit[][] rabbitMates, int gridSize) {
        int x = rabbit.getX();
        int y = rabbit.getY();
        Point target = findMate(x, y, rabbitMates, gridSize);
        if (target!=null) {
            return getDirection(x, y, target);
        }
        else {
            int dx = rn.nextInt(3) - 1;
            int dy = rn.nextInt(3) - 1;

            if (dx == 0 && dy == 0) {
                dx = 1; // Wymuś ruch
            }
            int newX = x + dx;
            int newY = y + dy;

            if (newX < 0) newX = 0;
            if (newX >= gridSize) newX = gridSize - 1;
            if (newY < 0) newY = 0;
            if (newY >= gridSize) newY = gridSize - 1;

            return new Point(newX, newY);
        }
    }
}
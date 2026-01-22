package Controller;

import Model.Carrot;
import Model.MatureState;
import Model.Rabbit;

import java.util.List;
import java.util.Random;

public class EntityController {
    private static Random rn = new Random();

    private static final int[][] directions = {
            {1, 0}, {-1, 0}, {0, -1}, {0, 1}
    };


    public static void initEntities(int rabbitCount, int carrotCount, int tileSize, int gridSize,
                                    List<Rabbit> rabbits, List<Carrot> carrots) {


        boolean[][] occupied = new boolean[gridSize][gridSize];
        Carrot carrot;

        // Marchewki
        for (int i = 0; i < carrotCount; i++) {
            int x, y;
            do {
                x = rn.nextInt(gridSize-10)+5;
                y = rn.nextInt(gridSize-10)+5;
            } while (occupied[x][y]);

            occupied[x][y] = true;
            carrot = new Carrot(x, y, tileSize);
            carrot.setState(new MatureState(carrot));
            carrots.add(carrot);

            // zrób grządke
            for (int[] dir : directions) {
                if (i >= carrotCount) break;

                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {

                    if (!occupied[newX][newY]) {
                        occupied[newX][newY] = true;

                        Carrot c = new Carrot(newX, newY, tileSize);
                        c.setState(new MatureState(c));
                        carrots.add(c);

                        i++;
                    }
                }
            }
        }

        // Króliki
        for (int i = 0; i < rabbitCount; i++) {
            int x, y;
            do {
                x = rn.nextInt(gridSize);
                y = rn.nextInt(gridSize);
            } while (occupied[x][y]);

            occupied[x][y] = true;
            rabbits.add(new Rabbit(x, y, tileSize));
        }
    }

    public static void step(List<Rabbit> rabbits, List<Carrot> carrots, Carrot[][] carrotMap, int gridSize) {
        for (Rabbit r : rabbits) {
            r.move(carrots, gridSize);

            int rx = r.getX();
            int ry = r.getY();

            // czy w tym miejscu na mapie leży marchewka
            if (carrotMap[rx][ry] != null) {
                Carrot eatenCarrot = carrotMap[rx][ry];

                if(eatenCarrot.getState() instanceof MatureState && r.getEnergy()<200) {
                    r.eat();
                    carrotMap[rx][ry] = null;
                    spreadSeeds(eatenCarrot, carrots, carrotMap, gridSize);
                    carrots.remove(eatenCarrot);
                }
            }
        }

        // jak umarł to usuń
        rabbits.removeIf(r -> r.getEnergy() <= 0);
    }

    public static void spreadSeeds(Carrot eatenCarrot, List<Carrot> carrots, Carrot[][] carrotMap, int gridSize) {
        int x = eatenCarrot.getX();
        int y = eatenCarrot.getY();

        for (int[] dir : directions) {
            if (rn.nextInt(3) == 1) { // 1/3 szans na rozsianie

                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {

                    if (carrotMap[newX][newY] == null) {

                        Carrot c = new Carrot(newX, newY, eatenCarrot.getSize());
                        carrots.add(c);

                        carrotMap[newX][newY] = c;
                    }
                }
            }
        }
    }
}

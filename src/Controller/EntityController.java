package Controller;

import Model.Carrot;
import Model.MatureState;
import Model.Rabbit;

import java.util.List;
import java.util.Random;

public class EntityController {
    private static Random rn = new Random();
    private static boolean[][] occupied;

    public static void initEntities(int rabbitCount, int carrotCount, int tileSize, int gridSize,
                                    List<Rabbit> rabbits, List<Carrot> carrots) {


        occupied = new boolean[gridSize][gridSize];
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
            if (!occupied[x+1][y]) {
                occupied[x+1][y] = true;
                carrot = new Carrot(x+1, y, tileSize);
                carrot.setState(new MatureState(carrot));
                carrots.add(carrot);
                i++;
            }
            if (!occupied[x-1][y] && i < carrotCount) {
                occupied[x-1][y] = true;
                carrot = new Carrot(x-1, y, tileSize);
                carrot.setState(new MatureState(carrot));
                carrots.add(carrot);
                i++;
            }
            if (!occupied[x][y-1] && i < carrotCount) {
                occupied[x][y-1] = true;
                carrot = new Carrot(x, y-1, tileSize);
                carrot.setState(new MatureState(carrot));
                carrots.add(carrot);
                i++;
            }
            if (!occupied[x][y+1] && i < carrotCount) {
                occupied[x][y+1] = true;
                carrot = new Carrot(x, y+1, tileSize);
                carrot.setState(new MatureState(carrot));
                carrots.add(carrot);
                i++;
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
}

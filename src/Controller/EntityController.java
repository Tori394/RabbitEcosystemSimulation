package Controller;

import Model.Carrot;
import Model.MatureState;
import Model.Rabbit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityController {
    private static Random rn = new Random();
    private static boolean[][] occupied;

    public static void initEntities(int rabbitCount, int carrotCount, int tileSize, int gridSize,
                                    List<Rabbit> rabbits, List<Carrot> carrots) {


        occupied = new boolean[gridSize][gridSize];

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

        // Marchewki
        for (int i = 0; i < carrotCount; i++) {
            int x, y;
            do {
                x = rn.nextInt(gridSize);
                y = rn.nextInt(gridSize);
            } while (occupied[x][y]);

            occupied[x][y] = true;
            Carrot carrot = new Carrot(x, y, tileSize);
            carrot.setState(new MatureState(carrot));
            carrots.add(carrot);
        }
    }
}

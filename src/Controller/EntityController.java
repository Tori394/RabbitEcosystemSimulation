package Controller;

import Model.Carrot;
import Model.Rabbit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityController {
    private static Random rn = new Random();

    public static List<Rabbit> initRabbits(int size, int tileSize, int gridSize) {
        int x,y;
        List<Rabbit> rabbits = new ArrayList<>();
        for (int i=0; i<size; i++) {
            x=rn.nextInt(gridSize);
            y=rn.nextInt(gridSize);
            rabbits.add(new Rabbit(x,y,tileSize));
        }
        return rabbits;
    }

    public static List<Carrot> initCarrots(int size, int tileSize, int gridSize) {
        int x,y;
        List<Carrot> carrots = new ArrayList<>();
        for (int i=0; i<size; i++) {
            x=rn.nextInt(gridSize);
            y=rn.nextInt(gridSize);
            carrots.add(new Carrot(x,y,tileSize));
        }
        return carrots;
    }
}

package Controller;

import Model.CarrotStates.ExpiredState;
import Model.Entities.Carrot;
import Model.CarrotStates.MatureState;
import Model.Entities.Rabbit;
import Model.Entities.EntityType;
import Model.RabbitStates.DeadState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EntityController {
    private static Random rn = new Random();

    private static List<ISimulationObserver> observers = new ArrayList<>();

    public static void addObserver(ISimulationObserver observer) {
        observers.add(observer);
    }


    private static void notifyObservers(int rCount, int cCount) {
        for (ISimulationObserver observer : observers) {
            observer.onStatsChanged(rCount, cCount);
        }
    }

    private static final int[][] directions = {
            {1, 0}, {-1, 0}, {0, -1}, {0, 1}
    };

    public static void initEntities(int rabbitCount, int carrotCount, int tileSize, int gridSize,
                                    List<Rabbit> rabbits, List<Carrot> carrots) {


        boolean[][] occupied = new boolean[gridSize][gridSize];

        // Marchewki
        for (int i = 0; i < carrotCount; i++) {
            int x, y;
            do {
                x = rn.nextInt(gridSize-10)+5;
                y = rn.nextInt(gridSize-10)+5;
            } while (occupied[x][y]);

            addCarrot(x, y, tileSize, carrots, occupied);

            // zrób grządke
            for (int[] dir : directions) {
                if (i >= carrotCount) break;

                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValid(newX, newY, gridSize) && !occupied[newX][newY]) {
                    addCarrot(newX, newY, tileSize, carrots, occupied);
                    i++;
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

            Rabbit r = (Rabbit) EntityFactory.createEntity(EntityType.RABBIT, x, y, tileSize);
            rabbits.add(r);
        }
    }

    public static void step(List<Rabbit> rabbits, List<Carrot> carrots, Carrot[][] carrotMap, Rabbit[][] rabbitMates, int gridSize) {
        int rabbitsBefore = rabbits.size();
        int carrotsBefore = carrots.size();

        Rabbit[][] collisionMap = new Rabbit[gridSize][gridSize];
        List<Rabbit> babies = new ArrayList<>();

        for (Rabbit r : rabbits) {
            r.move(carrots, gridSize, carrotMap, rabbitMates);

            int rx = r.getX();
            int ry = r.getY();

            // JEDZENIE
            if (carrotMap[rx][ry] != null) {
                Carrot eatenCarrot = carrotMap[rx][ry];

                if(eatenCarrot.isMature() && r.getEnergy()<150) {
                    r.eat(eatenCarrot.getEnergy());
                    carrotMap[rx][ry] = null;
                    spreadSeeds(eatenCarrot, carrots, carrotMap, gridSize);
                    carrots.remove(eatenCarrot);
                }
            }

            // ROZMNAŻANIE
            if (r.isReadyToMate()) {
                // Czy ktoś tu jest
                Rabbit partner = collisionMap[rx][ry];

                if (partner != null) {
                    // JEST PARTNER
                    // Tworzymy dziecko
                    int babiesAmount = rn.nextInt(3)+2;
                    for (int i = 0; i < babiesAmount; i++) {
                        Rabbit baby = (Rabbit) EntityFactory.createEntity(EntityType.RABBIT, rx, ry, r.getSize());
                        babies.add(baby);
                    }

                    r.Breed(babiesAmount);
                    partner.Breed(babiesAmount);

                    collisionMap[rx][ry] = null;

                } else {
                    // PUSTO
                    collisionMap[rx][ry] = r;
                }
            }

        }

        // update królików szukających partnera
        for (Rabbit[] row : rabbitMates) {
            Arrays.fill(row, null);
        }

        for (Rabbit r : rabbits) {
            if (r.isReadyToMate() && !r.isBusy()) {
                rabbitMates[r.getX()][r.getY()] = r;
            }
        }

        rabbits.addAll(babies);

        // jak umarł to usuń
        rabbits.removeIf(r -> r.getEnergy() <= 0); //z głodu
        rabbits.removeIf(r -> r.getState() instanceof DeadState); //ze starości

        //jak marchewka zgniła to usuń
        carrots.removeIf(c -> c.getState() instanceof ExpiredState);

        int rabbitsAfter = rabbits.size();
        int carrotsAfter = carrots.size();

        if (rabbitsAfter != rabbitsBefore || carrotsAfter != carrotsBefore) {
            notifyObservers(rabbitsAfter, carrotsAfter);
        }
    }

    private static void addCarrot(int x, int y, int tileSize, List<Carrot> list, boolean[][] occupied) {
        occupied[x][y] = true;
        Carrot c = (Carrot) EntityFactory.createEntity(EntityType.CARROT, x, y, tileSize);
        c.setState(new MatureState(c));
        list.add(c);
    }

    private static boolean isValid(int x, int y, int gridSize) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    public static void spreadSeeds(Carrot eatenCarrot, List<Carrot> carrots, Carrot[][] carrotMap, int gridSize) {
        int x = eatenCarrot.getX();
        int y = eatenCarrot.getY();

        for (int[] dir : directions) {
            if (rn.nextInt(3) == 1) { // 1/3 szans na rozsianie

                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValid(newX, newY, gridSize)) {

                    if (carrotMap[newX][newY] == null) {

                        Carrot c = (Carrot) EntityFactory.createEntity(EntityType.CARROT, newX, newY, eatenCarrot.getSize());

                        carrots.add(c);
                        carrotMap[newX][newY] = c;
                    }
                }
            }
        }
    }
}

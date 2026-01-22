package Model;

import java.awt.Point;
import java.awt.Color;
import java.util.List;

public class Rabbit extends Entity {
    private IRabbitMoveStrategy currentStrategy = null;
    private int energy;

    public Rabbit(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.color = new Color(209, 209, 209);
        this.size = size;
        this.energy = 100;
        this.currentStrategy = new RandomMoveStrategy();
    }

    public void move(List<Carrot> carrots, int gridSize) {

        Point nextStep = currentStrategy.calculateNextMove(this, carrots, gridSize);

        // Zaktualizuj pozycję
        this.x = nextStep.x;
        this.y = nextStep.y;

        // Królik się męczy ruchem
        this.energy--;
    }

    public void setStrategy(IRabbitMoveStrategy strategy) {
        if (this.currentStrategy.getClass() != strategy.getClass()) {
            this.currentStrategy = strategy;
        }
    }

    public void eat() {
        this.energy += 30;
    }

    public int getEnergy() {
        return energy;
    }
}


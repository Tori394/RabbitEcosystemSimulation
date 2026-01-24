package Model.Entities;

import Model.RabbitMoveStrategies.IRabbitMoveStrategy;
import Model.RabbitMoveStrategies.RandomMoveStrategy;
import Model.RabbitMoveStrategies.SeekFoodStrategy;
import Model.RabbitMoveStrategies.SeekMateStrategy;
import Model.RabbitStates.AdultState;
import Model.RabbitStates.DeadState;
import Model.RabbitStates.KidState;
import Model.RabbitStates.RabbitState;

import java.awt.*;
import java.util.List;

public class Rabbit extends Entity {
    private IRabbitMoveStrategy currentStrategy = null;
    private int energy;
    private RabbitState state;
    private Rabbit partner;

    // statystyki
    private int age;
    private int carrotsEaten;
    private int kids;
    private final int generation;

    public Rabbit(int x, int y, int size, int gen) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.energy = 100;
        this.partner = null;

        this.currentStrategy = new RandomMoveStrategy();
        setState(new KidState(this));

        this.age = 0;
        this.carrotsEaten = 0;
        this.kids = 0;
        this.generation = gen+1;
    }

    public void move(List<Carrot> carrots, int gridSize, Carrot[][] carrotMap, Rabbit[][] rabbitMates) {
        if (this.energy < 50){
            this.setStrategy(new SeekFoodStrategy());
        } else if (this.isReadyToMate()) {
            this.setStrategy(new SeekMateStrategy());
        } else {
            this.setStrategy(new RandomMoveStrategy());
        }

        Point nextStep = currentStrategy.calculateNextMove(this, carrots, carrotMap, rabbitMates, gridSize);

        // Zaktualizuj pozycję
        this.x = nextStep.x;
        this.y = nextStep.y;

        // Królik się męczy ruchem
        this.energy--;
        this.age ++;
    }

    public void setStrategy(IRabbitMoveStrategy strategy) {
        if (this.currentStrategy.getClass() != strategy.getClass()) {
            this.currentStrategy = strategy;
        }
    }

    public void eat(int energy) {
        this.energy += energy;
        this.carrotsEaten ++;
    }

    public int getEnergy() {
        return energy;
    }

    public String getStats() {
        String status;
        if (this.energy <= 0 || state instanceof DeadState) {
            status = "Dead";
        }
        else if (this.currentStrategy instanceof RandomMoveStrategy) {
            status = "Alive";
        }
        else if (this.currentStrategy instanceof SeekMateStrategy) {
            status = "Looking for love";
        }
        else {
            status = "Hungry";
        }
        return status + "\nTimes eaten: " + carrotsEaten +
                "\nEnergy: " + energy +
                "/180 \nAge: " + age +
                "\nKids: " + kids +
                "\nGeneration: " + generation;
    }

    public Object getState() {
        return state;
    }

    public void setState(RabbitState state) {
        this.state = state;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public boolean isReadyToMate() {
        return (this.energy>=150 && state instanceof AdultState);
    }

    public boolean isBusy() {
        return partner != null;
    }

    public void Breed(int babiesAmount) {
        this.kids += babiesAmount;
        this.energy -= 50;
    }

    public int getGeneration() {
        return generation;
    }
}


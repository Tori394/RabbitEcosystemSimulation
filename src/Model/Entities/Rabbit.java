package Model.Entities;

import Model.CarrotStates.CarrotState;
import Model.CarrotStates.GrowingState;
import Model.RabbitMoveStrategies.IRabbitMoveStrategy;
import Model.RabbitMoveStrategies.RandomMoveStrategy;
import Model.RabbitMoveStrategies.SeekFoodStrategy;
import Model.RabbitStates.KidState;
import Model.RabbitStates.RabbitState;

import java.awt.Point;
import java.awt.Color;
import java.util.List;

public class Rabbit extends Entity {
    private IRabbitMoveStrategy currentStrategy = null;
    private int energy;
    private RabbitState state;

    // statystyki
    private int age;
    private int carrotsEaten;
    private int kids;

    public Rabbit(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.energy = 100;

        this.currentStrategy = new RandomMoveStrategy();
        setState(new KidState(this));

        this.age = 0;
        this.carrotsEaten = 0;
        this.kids = 0;
    }

    public void move(List<Carrot> carrots, int gridSize, Carrot[][] carrotMap) {
        if (this.energy < 50){
            this.setStrategy(new SeekFoodStrategy());
        }else {
            this.setStrategy(new RandomMoveStrategy());
        }

        Point nextStep = currentStrategy.calculateNextMove(this, carrots, carrotMap, gridSize);

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
        if (this.energy <= 0) {
            status = "Dead";dd
        }
        else if (this.currentStrategy instanceof RandomMoveStrategy) {
            status = "Alive";
        }
        else {
            status = "Hungry";
        }
        return status + "\nTimes eaten: " + carrotsEaten + "\nEnergy: " + energy + "/180 \nAge: " + age + "\nKids: " + kids;
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
}


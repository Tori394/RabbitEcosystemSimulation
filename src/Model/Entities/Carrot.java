package Model.Entities;

import Model.CarrotStates.GrowingState;
import Model.CarrotStates.CarrotState;
import Model.CarrotStates.MatureState;
import Model.CarrotStates.RottenState;

import java.awt.*;

public class Carrot extends Entity {
    private CarrotState state;
    private int energy;

    public Carrot(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        setState(new GrowingState(this));
    }

    public void setState(CarrotState state) {
        this.state = state;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Object getState() {
        return state;
    }

    public boolean isMature() {
        return (this.getState() instanceof MatureState || this.getState() instanceof RottenState);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
}

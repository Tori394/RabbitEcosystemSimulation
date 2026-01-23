package Model.Entities;

import Model.CarrotStates.GrowingState;
import Model.CarrotStates.CarrotState;
import Model.CarrotStates.MatureState;

import java.awt.*;

public class Carrot extends Entity {
    private CarrotState state;

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
        return this.getState() instanceof MatureState;
    }
}

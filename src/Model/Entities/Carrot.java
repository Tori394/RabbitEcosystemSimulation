package Model.Entities;

import Model.CarrotStates.GrowingState;
import Model.CarrotStates.ICarrotState;
import Model.CarrotStates.MatureState;

import java.awt.*;

public class Carrot extends Entity {
    private ICarrotState state;

    public Carrot(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        setState(new GrowingState(this));
    }

    public void setState(ICarrotState state) {
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

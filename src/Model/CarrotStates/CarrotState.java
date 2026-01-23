package Model.CarrotStates;

import Model.Entities.Carrot;

public abstract class CarrotState {
    protected Carrot carrot;

    public CarrotState(Carrot carrot) {
        this.carrot = carrot;
    }

    public abstract CarrotState copy(Carrot carrot);
}


package Model;

public abstract class ICarrotState {
    protected Carrot carrot;

    public ICarrotState(Carrot carrot) {
        this.carrot = carrot;
    }

    public abstract ICarrotState copy(Carrot carrot);
}


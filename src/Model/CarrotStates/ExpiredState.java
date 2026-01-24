package Model.CarrotStates;

import Model.Entities.Carrot;

public class ExpiredState extends CarrotState {
    public ExpiredState(Carrot carrot) {
        super(carrot);
    }

    @Override
    public CarrotState copy(Carrot newOwner) {return new ExpiredState(newOwner); }
}

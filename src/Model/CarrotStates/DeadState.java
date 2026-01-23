package Model.CarrotStates;

import Model.Entities.Carrot;

public class DeadState extends CarrotState {
    public DeadState(Carrot carrot) {
        super(carrot);
    }

    @Override
    public CarrotState copy(Carrot newOwner) {return new DeadState(newOwner); }
}

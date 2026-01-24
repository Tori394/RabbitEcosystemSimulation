package Model.RabbitStates;

import Model.Entities.Rabbit;

public class DeadState extends RabbitState {
    public DeadState(Rabbit rabbit) {
        super(rabbit);
    }

    @Override
    public RabbitState copy(Rabbit newOwner) {return new Model.RabbitStates.DeadState(newOwner); }
}
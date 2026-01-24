package Model.RabbitStates;

import Model.Entities.Rabbit;

public abstract class RabbitState {
    protected Rabbit rabbit;

    public RabbitState(Rabbit rabbit) {
        this.rabbit = rabbit;
    }

    public abstract RabbitState copy(Rabbit rabbit);
}

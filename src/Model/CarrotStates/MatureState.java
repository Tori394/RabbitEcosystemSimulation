package Model.CarrotStates;

import Model.Entities.Carrot;

import java.awt.*;

public class MatureState extends ICarrotState {
    
    public MatureState(Carrot carrot) {
        super(carrot);
        carrot.setColor(new Color(255, 98, 0));
    }

    @Override
    public ICarrotState copy(Carrot newOwner) {
        return new MatureState(newOwner);
    }
}

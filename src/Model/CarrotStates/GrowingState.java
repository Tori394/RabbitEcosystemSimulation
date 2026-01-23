package Model.CarrotStates;

import Model.Entities.Carrot;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GrowingState extends ICarrotState {

    public GrowingState(Carrot carrot) {
        super(carrot);
        carrot.setColor(new Color(207, 174, 63));
        int delay = new Random().nextInt(10) + 20;
        Timer timer = new Timer(delay*1000, e -> {
            carrot.setState(new MatureState(carrot));
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public ICarrotState copy(Carrot newOwner) {
        return new GrowingState(newOwner);
    }
}

package Model.CarrotStates;

import Model.Entities.Carrot;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RottenState extends CarrotState {
    public RottenState(Carrot carrot) {
        super(carrot);
        carrot.setEnergy(10);
        carrot.setColor(new Color(80, 72, 33));
        int delay = new Random().nextInt(10) + 5;
        Timer timer = new Timer(delay*1000, e -> {
            carrot.setState(new DeadState(carrot));
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public CarrotState copy(Carrot newOwner) {
        return new RottenState(newOwner);
    }
}



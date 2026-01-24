package Model.CarrotStates;

import Model.Entities.Carrot;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MatureState extends CarrotState {
    
    public MatureState(Carrot carrot) {
        super(carrot);
        carrot.setEnergy(30);
        carrot.setColor(new Color(255, 115, 0));
        int delay = new Random().nextInt(20) + 40;
        Timer timer = new Timer(delay*1000, e -> {
            carrot.setState(new RottenState(carrot));
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public CarrotState copy(Carrot newOwner) {
        return new MatureState(newOwner);
    }
}

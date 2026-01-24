package Model.RabbitStates;

import Model.Entities.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AdultState extends RabbitState{

    public AdultState(Rabbit rabbit) {
        super(rabbit);
        rabbit.setColor(new Color(179, 179, 179));
        int delay = new Random().nextInt(60) + 60;
        Timer timer = new Timer(delay*1000, e -> {
            rabbit.setState(new DeadState(rabbit));
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public RabbitState copy(Rabbit newOwner) {
        return new AdultState(newOwner);
    }
}

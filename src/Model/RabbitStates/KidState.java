package Model.RabbitStates;

import Model.Entities.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class KidState extends RabbitState{

    public KidState(Rabbit rabbit) {
        super(rabbit);
        rabbit.setColor(new Color(246, 246, 246));
        int delay = new Random().nextInt(15) + 15;
        Timer timer = new Timer(delay*1000, e -> {
            rabbit.setState(new AdultState(rabbit));
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public RabbitState copy(Rabbit newOwner) {
        return new Model.RabbitStates.KidState(newOwner);
    }
}


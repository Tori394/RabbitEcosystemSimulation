package View;

import Controller.EntityController;
import Model.Carrot;
import Model.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Simulator {
    static final int WINDOW_SIZE = 600;
    static int GRID_SIZE = 30;
    static int TILE_SIZE;

    private JPanel StartPanel;
    private JPanel SimulationPanel;
    private List<Rabbit> rabbits;
    private List<Carrot> carrots;

    public Simulator(int rabbitsSize, int carrotsSize) {
        TILE_SIZE = WINDOW_SIZE/GRID_SIZE;
        rabbits = EntityController.initRabbits(rabbitsSize, TILE_SIZE, GRID_SIZE);
        carrots = EntityController.initCarrots(carrotsSize, TILE_SIZE, GRID_SIZE);
        SimulationPanel = new MapPanel(GRID_SIZE,TILE_SIZE, WINDOW_SIZE,rabbits,carrots);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ecosystem");
        frame.setContentPane(new Simulator(10,10).SimulationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

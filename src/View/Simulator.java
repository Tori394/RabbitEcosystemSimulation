package View;

import Controller.EntityController;
import Model.Carrot;
import Model.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    static final int WINDOW_SIZE = 600;
    static int GRID_SIZE = 50;
    static int TILE_SIZE;

    private JPanel GameSettings;      // Panel ustawień
    private JPanel SimulationPanel;   // Panel mapy
    private JSpinner rabbitSpinner;
    private JSpinner carrotSpinner;
    private JButton startButton;

    private JPanel rabbitSettings;
    private JPanel carrotSettings;

    private List<Rabbit> rabbits;
    private List<Carrot> carrots;

    public Simulator() {
        SpinnerNumberModel rabbitModel = new SpinnerNumberModel(100, 10, 1000, 1);
        rabbitSpinner.setModel(rabbitModel);

        SpinnerNumberModel carrotModel = new SpinnerNumberModel(100, 10, 1000, 1);
        carrotSpinner.setModel(carrotModel);

        startButton.addActionListener(e -> {
            startSimulation();
        });
    }

    private void startSimulation() {
        int rabbitsCount = (Integer) rabbitSpinner.getValue();
        int carrotsCount = (Integer) carrotSpinner.getValue();

        TILE_SIZE = WINDOW_SIZE / GRID_SIZE;
        rabbits = new ArrayList<>();
        carrots = new ArrayList<>();

        EntityController.initEntities(rabbitsCount, carrotsCount, TILE_SIZE, GRID_SIZE, rabbits, carrots);

        SimulationPanel = new MapPanel(GRID_SIZE, TILE_SIZE, WINDOW_SIZE, rabbits, carrots);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GameSettings);
        frame.setContentPane(SimulationPanel);
        frame.getContentPane().setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        frame.pack();
        frame.revalidate();
        frame.repaint();
        SimulationPanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ecosystem");
        Simulator sim = new Simulator();

        frame.setContentPane(sim.GameSettings);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setSize(250,200);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}
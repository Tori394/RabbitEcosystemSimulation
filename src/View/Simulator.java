package View;

import Controller.EntityController;
import Model.Entities.Carrot;
import Model.Entities.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    static final int WINDOW_SIZE = 600;
    static int GRID_SIZE = 50;
    static int TILE_SIZE;

    private JPanel GameSettings;
    private MapPanel mapPanel;

    private JSpinner rabbitSpinner;
    private JSpinner carrotSpinner;
    private JButton startButton;

    private JPanel rabbitSettings;
    private JPanel carrotSettings;

    private List<Rabbit> rabbits;
    private List<Carrot> carrots;
    private Carrot[][] carrotMap;
    private Rabbit[][] rabbitMates;

    private JLabel rabbitCountLabel;
    private JLabel carrotCountLabel;
    private JLabel maxGenLabel;
    private JTextArea rabbitStatsArea;

    private Timer simulationTimer;

    public Simulator() {
        SpinnerNumberModel rabbitModel = new SpinnerNumberModel(100, 10, 1000, 1);
        rabbitSpinner.setModel(rabbitModel);

        SpinnerNumberModel carrotModel = new SpinnerNumberModel(100, 10, 1000, 1);
        carrotSpinner.setModel(carrotModel);

        rabbitCountLabel = new JLabel();
        carrotCountLabel = new JLabel();
        maxGenLabel = new JLabel();

        rabbitStatsArea = new JTextArea(10, 15);
        rabbitStatsArea.setEditable(false);
        rabbitStatsArea.setLineWrap(true);
        rabbitStatsArea.setOpaque(false);

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
        carrotMap = new Carrot[GRID_SIZE][GRID_SIZE];
        rabbitMates = new Rabbit[GRID_SIZE][GRID_SIZE];
        for (Carrot c : carrots) {
            carrotMap[c.getX()][c.getY()] = c;
        }

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(150, WINDOW_SIZE));

        JLabel headerLabel = new JLabel("STATS:");
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanel.add(headerLabel);

        sidePanel.add(Box.createVerticalStrut(8));

        rabbitCountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanel.add(rabbitCountLabel);

        carrotCountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanel.add(carrotCountLabel);

        maxGenLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanel.add(maxGenLabel);

        EntityController.addObserver((rCount, cCount, maxGen) -> {
            rabbitCountLabel.setText("Rabbits: " + rCount);
            carrotCountLabel.setText("Carrots: " + cCount);
            maxGenLabel.setText("Max generation: " + maxGen);
        });

        rabbitCountLabel.setText("Rabbits: " + rabbits.size());
        carrotCountLabel.setText("Carrots: " + carrots.size());
        maxGenLabel.setText("Max generation: " + 1);

        sidePanel.add(Box.createVerticalStrut(20));

        JLabel infoLabel = new JLabel("Rabbit INFO:");
        infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanel.add(infoLabel);

        rabbitStatsArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanel.add(rabbitStatsArea);

        mapPanel = new MapPanel(GRID_SIZE, TILE_SIZE, WINDOW_SIZE, rabbits, carrots,
                rabbitCountLabel, carrotCountLabel, rabbitStatsArea);

        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.add(mapPanel, BorderLayout.CENTER);
        mainContainer.add(sidePanel, BorderLayout.EAST);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GameSettings);
        frame.setContentPane(mainContainer);

        frame.getContentPane().setPreferredSize(new Dimension(WINDOW_SIZE + 150, WINDOW_SIZE));
        frame.pack();
        frame.revalidate();
        frame.repaint();
        mapPanel.requestFocusInWindow();

        simulationTimer = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntityController.step(rabbits, carrots, carrotMap, rabbitMates, GRID_SIZE);
                mapPanel.repaint();
            }
        });

        simulationTimer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ecosystem");
        Simulator sim = new Simulator();

        frame.setContentPane(sim.GameSettings);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setSize(250, 200);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}
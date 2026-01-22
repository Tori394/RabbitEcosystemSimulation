package View;

import javax.swing.*;

public class Simulator {
    static int gridSize = 30;
    private JPanel StartPanel;
    private final JPanel SimulationPanel;

    public Simulator() {
        SimulationPanel = new MapPanel(gridSize);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tracking System");
        frame.setContentPane(new Simulator().SimulationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

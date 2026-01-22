package View;

import Model.Carrot;
import Model.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class MapPanel extends JPanel {
    private final int tileSize;
    private final int windowSize;
    private final int gridSize;

    private final List<Rabbit> rabbits;
    private final List<Carrot> carrots;

    public MapPanel(int gridSize, int tileSize, int windowSize, List<Rabbit> rabbits, List<Carrot> carrots) {
        this.gridSize = gridSize;
        this.tileSize = tileSize;
        this.windowSize = windowSize;
        this.rabbits = rabbits;
        this.carrots = carrots;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(82, 165, 72));
        g.fillRect(0, 0, windowSize, windowSize);

        g.setColor(new Color(48, 99, 38));

        for (int i = 0; i <= gridSize; i++) {
            int pos = i * tileSize;
            g.drawLine(pos, 0, pos, windowSize);
            g.drawLine(0, pos, windowSize, pos);
        }

        // Rysowanie Marchewek
        for (Carrot c : carrots) {
            g.setColor(c.getColor());
            g.fillOval(c.getX() * tileSize, c.getY() * tileSize, c.getSize(), c.getSize());
        }

        // Rysowanie Królików
        for (Rabbit r : rabbits) {
            g.setColor(r.getColor());
            g.fillOval(r.getX() * tileSize, r.getY() * tileSize, r.getSize(), r.getSize());
        }
    }
}
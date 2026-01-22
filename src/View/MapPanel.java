package View;

import javax.swing.*;
import java.awt.*;

class MapPanel extends JPanel {
    private int gridSize;

    public MapPanel(int gridSize) {
        this.gridSize=gridSize;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / gridSize;
        int cellHeight = height / gridSize;

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i <= gridSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        for (int i = 0; i <= gridSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
    }
}
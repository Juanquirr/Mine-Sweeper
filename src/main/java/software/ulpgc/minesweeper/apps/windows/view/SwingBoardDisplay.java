package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingBoardDisplay extends JPanel implements BoardDisplay {
    private PaintOrder paintOrder = null;

    public SwingBoardDisplay() {
        this.setLayout(new BorderLayout());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GRAY);
        for (int i = 0; i < paintOrder.height() * paintOrder.width(); i++) {
            int x = 50 * (i / paintOrder.width());
            int y = 50 * (i % paintOrder.width());
            g.fillRect(x, y, 50, 50);
            g.setColor(Color.RED);
            g.drawRect(x, y, 50, 50);
            g.setColor(Color.GRAY);
        }
    }

    @Override
    public void paint(PaintOrder paintOrder) {
        this.paintOrder = paintOrder;
        this.setSize(paintOrder.width()*50, paintOrder.height()*50);
        this.setMinimumSize(new Dimension(paintOrder.width()*50, paintOrder.height()*50));
        repaint();
    }

    @Override
    public void on(Click click) {

    }
}

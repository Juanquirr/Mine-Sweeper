package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.Level;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class SwingBoardDisplay extends JPanel implements BoardDisplay {
    private final List<PaintOrder> orders;
    private Click click = Click.NULL;
    private final HashMap<Integer, Color> colors;

    public SwingBoardDisplay() {
        this.colors = new HashMap<>();
        this.colors.put(1, Color.ONE);
        this.colors.put(2, Color.TWO);
        this.colors.put(3, Color.THREE);
        this.colors.put(4, Color.FOUR);
        this.colors.put(null, Color.ONE);
        setLayout(new BorderLayout());
        this.orders = new ArrayList<>();
        this.addMouseListener(createMouseListener());
    }

    private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                click.offset((e.getX() / CELL_SIZE) * CELL_SIZE, (e.getY() / CELL_SIZE) * CELL_SIZE, e.getButton());
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        orders.forEach(p -> {
            SwingPaintUtilities.drawCell(g, p, CELL_SIZE, colors);
            if (p.flag()) SwingPaintUtilities.drawFlag(g, p.position(), CELL_SIZE);
        });
    }

    @Override
    public void adjustDimensionTo(Level.Size size) {
        setPreferredSize(new Dimension(size.width() * CELL_SIZE, size.height() * CELL_SIZE));
    }

    @Override
    public void paint(PaintOrder... orders) {
        this.orders.clear();
        Collections.addAll(this.orders, orders);
        repaint();
    }

    @Override
    public void on(Click click) {
        this.click = click;
    }
}

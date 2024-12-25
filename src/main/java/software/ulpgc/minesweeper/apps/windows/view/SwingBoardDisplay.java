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
                click.offset((e.getX() / CELL_SIZE) * CELL_SIZE, (e.getY() / CELL_SIZE) * CELL_SIZE);
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        orders.forEach(p -> {
            g.setColor(p.color().getColor());
            g.fillRect(p.position().row(), p.position().column(), CELL_SIZE, CELL_SIZE);
            g.setColor(new java.awt.Color(0, 128, 0));
            g.drawRect(p.position().row(), p.position().column(), CELL_SIZE, CELL_SIZE);
            g.setColor(this.colors.get(p.number()).getColor());
            if (p.number() != null) g.drawString(String.valueOf(p.number()), p.position().row() + CELL_SIZE / 2, p.position().column() + CELL_SIZE / 2);
        });
    }

    @Override
    public void adjustDimensionTo(Level.Size size) {
        setPreferredSize(new Dimension(size.width() * CELL_SIZE, size.height() * CELL_SIZE));
    }

    @Override
    public void paint(PaintOrder... orders) {
        if (this.orders.isEmpty()) Collections.addAll(this.orders, orders);
        for (PaintOrder order : orders) {
            this.orders.set(((order.position().row() / CELL_SIZE) * (getPreferredSize().width / CELL_SIZE)) + (order.position().column() / CELL_SIZE), order);
        }
        repaint();
    }

    @Override
    public void on(Click click) {
        this.click = click;
    }

    @Override
    public void clear() {
        this.orders.clear();
        repaint();
    }
}

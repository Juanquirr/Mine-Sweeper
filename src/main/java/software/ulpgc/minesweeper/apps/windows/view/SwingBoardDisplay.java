package software.ulpgc.minesweeper.apps.windows.view;

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

    public SwingBoardDisplay() {
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
            g.setColor(p.color());
            g.fillRect(p.x(), p.y(), p.width(), p.height());
            g.setColor(Color.RED);
            g.drawRect(p.x(), p.y(), p.width(), p.height());
            g.setColor(Color.BLACK);
            if (p.number() != null) g.drawString(String.valueOf(p.number()), p.x() + CELL_SIZE / 2, p.y() + CELL_SIZE / 2);
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
            this.orders.set(((order.y() / CELL_SIZE) * (getPreferredSize().width / CELL_SIZE)) + (order.x() / CELL_SIZE), order);
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
        this.orders.add(new PaintOrder(Color.BLACK, 0, 0, getWidth(), getHeight(), null));
        repaint();
        this.orders.clear();
    }
}

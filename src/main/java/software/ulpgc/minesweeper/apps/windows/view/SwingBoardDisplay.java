package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.model.Level;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwingBoardDisplay extends JPanel implements BoardDisplay {
    private final List<PaintOrder> orders;
    private Click click = Click.NULL;

    public SwingBoardDisplay() {
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
        });
    }

    @Override
    public void adjustDimensionTo(Level.Size size) {
        setPreferredSize(new Dimension(size.width() * CELL_SIZE, size.height() * CELL_SIZE));
    }

    @Override
    public void paint(PaintOrder... orders) {
        /*if (orders.length == 1) {
            PaintOrder order = orders[0];
            this.orders.add(order);
            repaint(order.x(), order.y(), order.width(), order.height());
        } else {
            this.orders.clear();
            Collections.addAll(this.orders, orders);
            repaint();
        }*/
        Collections.addAll(this.orders, orders);

        // Repaint sobre las áreas de todas las órdenes recibidas
        for (PaintOrder order : orders) {
            repaint(order.x(), order.y(), order.width(), order.height());
        }
    }

    @Override
    public void on(Click click) {
        this.click = click;
    }
}

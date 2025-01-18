package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.CounterDisplay;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SwingCounterDisplay extends JPanel implements CounterDisplay {
    private final JLabel label;

    public SwingCounterDisplay() {
        add(this.label = new JLabel("000"));
        this.label.setBorder(new LineBorder(new Color(0,0,10), 4));
        this.label.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    @Override
    public void show(Integer integer) {
        label.setText(String.format("%03d", integer));
    }
}

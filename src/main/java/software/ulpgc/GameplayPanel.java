package software.ulpgc;

import javax.swing.*;
import java.awt.*;

public class GameplayPanel extends JPanel {
    private boolean isGreen = false;

    public GameplayPanel(int rows, int cols) {
        GridLayout gameplay = new GridLayout(rows, cols);
        setLayout(gameplay);
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(26,255,110));

        for (int i = 0; i < rows * cols; i++) {
            add(createSquare());
            isGreen = !isGreen;
        }
    }

    private JButton createSquare() {
        JButton button = new JButton("");
        button.addActionListener(e -> {
            button.setText("X");
        });
        button.setBackground(isGreen ? new Color(0, 255, 0) : new Color(153, 255, 153));
        return button;
    }

    public boolean isGreen() {
        return isGreen;
    }
}

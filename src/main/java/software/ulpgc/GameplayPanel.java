package software.ulpgc;

import javax.swing.*;
import java.awt.*;

public class GameplayPanel extends JPanel {
    private final GridLayout gameplay;

    public GameplayPanel(int rows, int cols) {
        gameplay = new GridLayout(rows, cols);
        setLayout(gameplay);

        for (int i = 0; i < rows * cols; i++) {
            add(createSquare());
        }
    }

    private JButton createSquare() {
        JButton button = new JButton("");
        button.addActionListener(e -> {
            button.setText("X");
        });
        return button;
    }
}

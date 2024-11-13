package software.ulpgc;

import javax.swing.*;
import java.awt.*;

public class MineBoard extends JFrame {
    public MineBoard() throws HeadlessException {
        setVisible(true);
        setTitle("Minesweeper");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

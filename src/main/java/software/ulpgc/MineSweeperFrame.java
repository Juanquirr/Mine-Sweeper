package software.ulpgc;

import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    public MineSweeperFrame() throws HeadlessException {
        setTitle("Minesweeper");
        setResizable(false);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

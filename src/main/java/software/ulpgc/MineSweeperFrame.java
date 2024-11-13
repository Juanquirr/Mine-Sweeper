package software.ulpgc;

import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    public MineSweeperFrame() throws HeadlessException {
        setTitle("Minesweeper");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

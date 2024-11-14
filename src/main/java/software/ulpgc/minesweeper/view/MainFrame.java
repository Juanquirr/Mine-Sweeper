package software.ulpgc.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setTitle("Minesweeper");
        setResizable(false);
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


}

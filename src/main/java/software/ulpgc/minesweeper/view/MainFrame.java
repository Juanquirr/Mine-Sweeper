package software.ulpgc.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setTitle("Minesweeper");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(loadingPanel());
    }

    private Component loadingPanel() {
        return new SwingLoadingPanel();
    }
}

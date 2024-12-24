package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.GamePlayPanel;

import javax.swing.*;
import java.awt.*;

public class SwingGameplayPanel extends JPanel implements GamePlayPanel {
    private final Chronometer timer;
    private final SwingBoardDisplay boardDisplay;
    private JButton finalizeButton;

    public SwingGameplayPanel() {
        this.timer = new Chronometer();
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, createToolbar());
        this.add(BorderLayout.CENTER, this.boardDisplay = createBoardDisplay());
    }

    private SwingBoardDisplay createBoardDisplay() {
        SwingBoardDisplay swingBoardDisplay = new SwingBoardDisplay();
        return swingBoardDisplay;
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(timer);
        panel.add(creteFinalizeButton());
        return panel;
    }

    private JButton creteFinalizeButton() {
        this.finalizeButton = new JButton("Finalize");
        finalizeButton.addActionListener(e -> hidePanel());
        return finalizeButton;
    }

    @Override
    public void hidePanel() {
        this.setVisible(false);
    }

    @Override
    public void showPanel() {
        this.setVisible(true);
    }

    @Override
    public BoardDisplay boardDisplay() {
        return boardDisplay;
    }

    public JButton finalizeButton() {
        return finalizeButton;
    }
}

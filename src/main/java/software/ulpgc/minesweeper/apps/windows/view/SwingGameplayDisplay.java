package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingGameplayDisplay extends JPanel implements GameDisplay {
    private final Chronometer timer;
    private final SwingBoardDisplay boardDisplay;
    private JButton finalizeButton;

    public SwingGameplayDisplay() {
        this.timer = new Chronometer();
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, createToolbar());
        this.add(BorderLayout.CENTER, this.boardDisplay = createBoardDisplay());
    }

    private SwingBoardDisplay createBoardDisplay() {
        return new SwingBoardDisplay();
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(timer);
        panel.add(creteFinalizeButton());
        return panel;
    }

    private JButton creteFinalizeButton() {
        this.finalizeButton = new JButton("Return");
        return finalizeButton;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void stopGame() {
        setVisible(false);
    }

    @Override
    public BoardDisplay boardDisplay() {
        return boardDisplay;
    }

    public JButton finalizeButton() {
        return finalizeButton;
    }
}

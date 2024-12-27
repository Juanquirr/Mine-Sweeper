package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.Chronometer;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingGameDisplay extends JPanel implements GameDisplay {
    private final SwingChronometer chronometer;
    private final SwingBoardDisplay boardDisplay;
    private JButton finalizeButton;
    private JButton restartButton;

    public SwingGameDisplay() {
        this.chronometer = new SwingChronometer();
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, createToolbar());
        this.add(BorderLayout.CENTER, this.boardDisplay = createBoardDisplay());
    }

    private SwingBoardDisplay createBoardDisplay() {
        return new SwingBoardDisplay();
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(chronometer);
        panel.add(creteFinalizeButton());
        panel.add(createRestartButton());
        return panel;
    }

    private JButton createRestartButton() {
        this.restartButton = new JButton("🙂");
        return restartButton;
    }

    private JButton creteFinalizeButton() {
        this.finalizeButton = new JButton("Return");
        return finalizeButton;
    }

    @Override
    public void startGame() {
        chronometer.start();
        setVisible(true);
    }

    @Override
    public void stopGame() {
        chronometer.stop();
        setVisible(false);
    }

    @Override
    public BoardDisplay boardDisplay() {
        return boardDisplay;
    }

    @Override
    public Chronometer chronometer() {
        return chronometer;
    }

    public JButton finalizeButton() {
        return finalizeButton;
    }

    public JButton restartButton() {
        return restartButton;
    }
}

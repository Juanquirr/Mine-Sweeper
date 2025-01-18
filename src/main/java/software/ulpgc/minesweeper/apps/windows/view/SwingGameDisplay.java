package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.Chronometer;
import software.ulpgc.minesweeper.architecture.view.CounterDisplay;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingGameDisplay extends JPanel implements GameDisplay {
    private final Chronometer chronometer;
    private final BoardDisplay boardDisplay;
    private final CounterDisplay counterDisplay;
    private JButton finalizeButton;
    private JButton restartButton;

    public SwingGameDisplay() {
        this.chronometer = new SwingChronometer();
        this.counterDisplay = createCounterDisplay();
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, createToolbar());
        this.add(BorderLayout.CENTER, (Component) (this.boardDisplay = createBoardDisplay()));
    }

    private SwingBoardDisplay createBoardDisplay() {
        return new SwingBoardDisplay();
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add((Component) counterDisplay);
        panel.add(createRestartButton());
        panel.add((Component) chronometer);
        panel.add(creteFinalizeButton());
        return panel;
    }

    private SwingCounterDisplay createCounterDisplay() {
        return new SwingCounterDisplay();
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
    }

    @Override
    public void resetGame() {
        this.restartButton.setText("🙂");
    }

    @Override
    public void showWinDisplay() {
        this.restartButton.setText("😎");
        JOptionPane.showMessageDialog(null, "YOU WON! :-D");
    }

    @Override
    public void showLostDisplay() {
        this.restartButton.setText("🙁");
        JOptionPane.showMessageDialog(null, "TRY HARDER! (╯°□°）╯︵ ┻━┻");
    }

    @Override
    public BoardDisplay boardDisplay() {
        return boardDisplay;
    }

    @Override
    public Chronometer chronometer() {
        return chronometer;
    }

    @Override
    public CounterDisplay counterDisplay() {
        return counterDisplay;
    }

    public JButton finalizeButton() {
        return finalizeButton;
    }

    public JButton restartButton() {
        return restartButton;
    }
}

package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.*;

import javax.swing.*;
import java.awt.*;

public class SwingGameDisplay extends JPanel implements GameDisplay {
    private final BoardDisplay boardDisplay;
    private final CounterDisplay counterDisplay;
    private final ReplayController replayController;
    private final Chronometer chronometer;
    private final JButton finalizeButton;
    private final JButton restartButton;

    public SwingGameDisplay() {
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, createToolbar(this.counterDisplay = createCounterDisplay(), this.restartButton = createRestartButton(), this.chronometer = new SwingChronometer(), this.finalizeButton = creteFinalizeButton()));
        this.add(BorderLayout.CENTER, (Component) (this.boardDisplay = createBoardDisplay()));
        this.add(BorderLayout.SOUTH, (Component) (this.replayController = createReplayToolbar()));
    }

    private SwingReplayController createReplayToolbar() {
        return new SwingReplayController();
    }

    private SwingBoardDisplay createBoardDisplay() {
        return new SwingBoardDisplay();
    }

    private JPanel createToolbar(CounterDisplay counterDisplay, JButton restartButton, Chronometer chronometer, JButton finalizeButton) {
        JPanel panel = new JPanel();
        panel.add((Component)counterDisplay);
        panel.add(restartButton);
        panel.add((Component) chronometer);
        return panel;
    }

    private SwingCounterDisplay createCounterDisplay() {
        return new SwingCounterDisplay();
    }

    private JButton createRestartButton() {
        return new JButton("🙂");
    }

    private JButton creteFinalizeButton() {
        return new JButton("Return");
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
        this.chronometer.reset();
        this.replayController.disableController();
    }

    private void enableReplayControlsFor(String emoji, String message) {
        this.restartButton.setText(emoji);
        JOptionPane.showMessageDialog(null, message);
        replayController.enableController();

    }

    @Override
    public void showWinDisplay() {
        enableReplayControlsFor("😎", "YOU WON! :-D");
    }

    @Override
    public void showLostDisplay() {
        enableReplayControlsFor("🙁", "TRY HARDER! (╯°□°）╯︵ ┻━┻");
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

    @Override
    public ReplayController replayController() {
        return replayController;
    }

    public JButton finalizeButton() {
        return finalizeButton;
    }

    public JButton restartButton() {
        return restartButton;
    }
}

package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.ReplayController;

import javax.swing.*;

public class SwingReplayController extends JPanel implements ReplayController {
    private final JButton startButton;
    private final JButton stopButton;

    public SwingReplayController() {
        this.startButton = new JButton("START");
        this.stopButton = new JButton("STOP");
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        add(startButton);
        add(stopButton);
    }

    public JButton startButton() {
        return startButton;
    }

    public JButton stopButton() {
        return stopButton;
    }

    @Override
    public void enableController() {
        startButton.setEnabled(true);
        stopButton.setEnabled(true);
    }

    @Override
    public void disableController() {
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
    }
}

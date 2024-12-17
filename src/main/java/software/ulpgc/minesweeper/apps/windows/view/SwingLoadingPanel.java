package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.CustomFont;
import software.ulpgc.minesweeper.architecture.view.LoadingPanel;

import javax.swing.*;
import java.awt.*;

public class SwingLoadingPanel extends JPanel implements LoadingPanel {

    private final JButton startButton;
    private final SwingDifficultyDialog difficultyDialog;

    public SwingLoadingPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(72, 108, 217));

        add(BorderLayout.NORTH, createTitleDisplay());
        add(BorderLayout.CENTER, createCenterPanel(this.difficultyDialog = createDifficultyDialog()));
        add(BorderLayout.SOUTH, toolbar(this.startButton = createStartButton()));
    }

    private JPanel createCenterPanel(SwingDifficultyDialog difficultyDialog) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(difficultyDialog);
        return panel;
    }


    private JPanel toolbar(JButton button) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        return panel;
    }

    private JButton createStartButton() {
        JButton button = new JButton("START");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JPanel createTitleDisplay() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("MINESWEEPER", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new CustomFont().loadFont().deriveFont(40f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(30));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    private SwingDifficultyDialog createDifficultyDialog() {
        return new SwingDifficultyDialog();
    }

    public JButton startButton() {
        return startButton;
    }

    public SwingDifficultyDialog difficultyDialog() {
        return difficultyDialog;
    }

    @Override
    public LoadingPanel showPanel() {
        setVisible(true);
        return this;
    }

    @Override
    public LoadingPanel hidePanel() {
        setVisible(false);
        return this;
    }
}

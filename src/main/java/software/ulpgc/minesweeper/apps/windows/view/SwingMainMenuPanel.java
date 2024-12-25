package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.CustomFont;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class SwingMainMenuPanel extends JPanel implements MainMenuPanel {

    private final JButton startButton;
    private final SwingLevelDialog difficultyDialog;

    public SwingMainMenuPanel() {
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, createTitleDisplay());
        add(BorderLayout.CENTER, createCenterPanel(this.difficultyDialog = createDifficultyDialog()));
        add(BorderLayout.SOUTH, toolbar(this.startButton = createStartButton()));
    }

    private JPanel createCenterPanel(SwingLevelDialog difficultyDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(difficultyDialog);
        return panel;
    }

    private JPanel toolbar(JButton button) {
        JPanel panel = new JPanel();
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
        panel.setBackground(new Color(153, 204, 255));
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

    private SwingLevelDialog createDifficultyDialog() {
        return new SwingLevelDialog();
    }

    public JButton startButton() {
        return startButton;
    }

    public SwingLevelDialog difficultyDialog() {
        return difficultyDialog;
    }

    @Override
    public MainMenuPanel showPanel() {
        setVisible(true);
        return this;
    }

    @Override
    public MainMenuPanel hidePanel() {
        setVisible(false);
        return this;
    }
}

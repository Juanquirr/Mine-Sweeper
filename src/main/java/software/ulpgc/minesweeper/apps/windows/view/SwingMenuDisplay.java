package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.CustomFont;
import software.ulpgc.minesweeper.architecture.view.MenuDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMenuDisplay extends JPanel implements MenuDisplay {

    private final JButton startButton;
    private final SwingLevelDialog difficultyDialog;

    public SwingMenuDisplay() {
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, createTitleDisplay());
        add(BorderLayout.CENTER, createCenterPanel(this.difficultyDialog = createDifficultyDialog()));
        add(BorderLayout.SOUTH, toolbar(this.startButton = createStartButton()));
    }

    private JPanel createCenterPanel(SwingLevelDialog levelDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(levelDialog);
        return panel;
    }

    private JPanel toolbar(JButton button) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
        JPanel mediumPanel = new JPanel();
        mediumPanel.setBackground(new java.awt.Color(0, 144, 0));
        panel.add(mediumPanel);
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
    public void showPanel() {
        setVisible(true);
    }

}

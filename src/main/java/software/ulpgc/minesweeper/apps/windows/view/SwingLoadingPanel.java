package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.LoadingPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SwingLoadingPanel extends JPanel implements LoadingPanel {

    private final Font customFont;
    private final JButton startButton;
    private final SwingDifficultyDialog difficultyDialog;

    public SwingLoadingPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        customFont = createCustomFont();

        add(BorderLayout.NORTH, createTitleDisplay());
        add(BorderLayout.CENTER, createCenterPanel(this.difficultyDialog = createDifficultyDialog()));
        add(BorderLayout.SOUTH, toolbar(this.startButton = createStartButton()));
    }

    private JPanel createCenterPanel(SwingDifficultyDialog difficultyDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(difficultyDialog);
        return panel;
    }


    private Font createCustomFont() {
        final Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("LuckiestGuy-Regular.ttf")).deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }
        return customFont;
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
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("MINESWEEPER", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(customFont.deriveFont(40f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(30));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    private JButton customizeButton(String text) {
        JButton button = new JButton(text);
        button.setFont(customFont.deriveFont(30f));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(65, 61, 111));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
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

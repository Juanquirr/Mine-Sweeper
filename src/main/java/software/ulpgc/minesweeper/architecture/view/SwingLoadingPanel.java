package software.ulpgc.minesweeper.architecture.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SwingLoadingPanel extends JPanel implements LoadingPanel {

    private final Font customFont;
    private final JButton startButton;
    private final SwingDifficultyDialog difficultyDialog;

    public SwingLoadingPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("LuckiestGuy-Regular.ttf")).deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }

        add(displayTitle());
        add(Box.createVerticalStrut(10));
        add(this.difficultyDialog = createDifficultyDialog());
        add(Box.createVerticalStrut(100));
        add(toolbar(this.startButton = createStartButton()));
    }

    private JPanel toolbar(JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        return panel;
    }

    private JButton createStartButton() {
        JButton button = new JButton("START");
        button.setMaximumSize(new Dimension(800, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private Component displayTitle() {
        JLabel label = new JLabel("MINESWEEPER", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setMaximumSize(new Dimension(800, 200));
        label.setFont(customFont.deriveFont(40f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar en BoxLayout
        return label;
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

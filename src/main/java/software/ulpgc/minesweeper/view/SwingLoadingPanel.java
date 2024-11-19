package software.ulpgc.minesweeper.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SwingLoadingPanel extends JPanel {

    private final Font customFont;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;

    public SwingLoadingPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("LuckiestGuy-Regular.ttf")).deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }

        add(displayTitle());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createDifficultySelectionPanel());
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(toolbar());
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createStartButton());
        return panel;
    }

    private Component createStartButton() {
        JButton button = new JButton("START");
        button.addActionListener(e -> System.out.println("START!"));
        button.setMaximumSize(new Dimension(800, 50)); // Ancho máximo razonable
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

    private JPanel createDifficultySelectionPanel() {
        return new SwingDifficultyDialog();
    }
}

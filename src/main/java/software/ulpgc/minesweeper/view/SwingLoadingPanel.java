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
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("LuckiestGuy-Regular.ttf")).deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }

        createButtons();
        add(displayTitle(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
    }

    private JLabel displayTitle() {
        JLabel label = new JLabel("MINESWEEPER", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(700, 300));
        label.setFont(customFont.deriveFont(40f));
        return label;
    }

    private void createButtons() {
        easyButton = customizeButton("EASY");
        mediumButton = customizeButton("MEDIUM");
        hardButton = customizeButton("HARD");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(easyButton);
        buttonGroup.add(mediumButton);
        buttonGroup.add(hardButton);


        easyButton.addActionListener(e -> updateButtonStyles(easyButton));
        mediumButton.addActionListener(e -> updateButtonStyles(mediumButton));
        hardButton.addActionListener(e -> updateButtonStyles(hardButton));
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

    private void updateButtonStyles(JButton selectedButton) {
        for (JButton button : new JButton[]{easyButton, mediumButton, hardButton}) {
            button.setBackground(new Color(65, 61, 111));
            button.setFont(customFont.deriveFont(30f));
            button.setForeground(Color.WHITE);
        }
        selectedButton.setBackground(new Color(255, 223, 0));
        selectedButton.setFont(customFont.deriveFont(Font.BOLD, 35f));
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(mediumButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(hardButton);

        return buttonPanel;
    }
}

// MainPanel.java
package software.ulpgc;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainPanel {
    private final JPanel mainPanel = new JPanel();
    private GameplayPanel gameplayPanel;

    public MainPanel() {
        mainPanel.setLayout(new BorderLayout());
        initialSelector();
    }

    private void initialSelector() {
        JComboBox<String> difficultySelector = new JComboBox<>();

        difficultySelector.setPreferredSize(new Dimension(150, 50));
        difficultySelector.setFont(new Font("Comics Sans", Font.PLAIN, 20));


        difficultySelector.addItem("Easy");
        difficultySelector.addItem("Intermediate");
        difficultySelector.addItem("Expert"); // TODO Lista de dificultades

        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboBoxPanel.add(difficultySelector);

        difficultySelector.addActionListener(e -> {
            changeDifficulty(difficultySelector);
        });
        mainPanel.add(comboBoxPanel, BorderLayout.NORTH);
    }

    private void changeDifficulty(JComboBox<String> difficultySelector) {
        if (gameplayPanel != null) mainPanel.remove(gameplayPanel);
        if (Objects.equals(difficultySelector.getSelectedItem(), "Easy")){
            configurateGameplayPanel(9, 9);
        } else if (Objects.equals(difficultySelector.getSelectedItem(), "Intermediate")){
            configurateGameplayPanel(16, 16);
        } else if (Objects.equals(difficultySelector.getSelectedItem(), "Expert")){
            configurateGameplayPanel(16, 30);
        }
        mainPanel.add(gameplayPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void configurateGameplayPanel(int i, int j) {
        gameplayPanel = new GameplayPanel(i, j);
        gameplayPanel.setPreferredSize(new Dimension(i*10, j*10));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

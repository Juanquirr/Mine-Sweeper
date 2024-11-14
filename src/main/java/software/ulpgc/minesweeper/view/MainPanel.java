// MainPanel.java
package software.ulpgc.minesweeper.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainPanel {
    private final JPanel mainPanel = new JPanel();
    private GameplayPanel gameplayPanel;

    public MainPanel() {
        mainPanel.setLayout(new BorderLayout());
        selectDifficulty();
    }

    private void selectDifficulty() {
        JComboBox<String> difficultySelector = new JComboBox<>();
        addDifficulties(difficultySelector);
        personalizeAspect(difficultySelector);
        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboBoxPanel.setBackground(new Color(20, 145,66));
        comboBoxPanel.add(difficultySelector);

        difficultySelector.addActionListener(e -> {
            changeDifficulty(difficultySelector);
        });
        mainPanel.add(comboBoxPanel, BorderLayout.NORTH);
    }

    private static void personalizeAspect(JComboBox<String> difficultySelector) {
        difficultySelector.setPreferredSize(new Dimension(130, 50));
        difficultySelector.setFont(new Font("Comics Sans", Font.PLAIN, 16));
        difficultySelector.setBackground(new Color(178,186,181));
    }


    private static void addDifficulties(JComboBox<String> difficultySelector) {
        difficultySelector.addItem("Easy");
        difficultySelector.addItem("Intermediate");
        difficultySelector.addItem("Expert"); // TODO Lista de dificultades
    }

    private void changeDifficulty(JComboBox<String> difficultySelector) {
        if (gameplayPanel != null) mainPanel.remove(gameplayPanel);
        if (Objects.equals(difficultySelector.getSelectedItem(), "Easy")){
            configureGameplayPanel(9, 9);
        } else if (Objects.equals(difficultySelector.getSelectedItem(), "Intermediate")){
            configureGameplayPanel(16, 16);
        } else if (Objects.equals(difficultySelector.getSelectedItem(), "Expert")){
            configureGameplayPanel(16, 30);
        }
        mainPanel.add(gameplayPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void configureGameplayPanel(int i, int j) {
        gameplayPanel = new GameplayPanel(i, j);
        gameplayPanel.setPreferredSize(new Dimension(i*10, j*10));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

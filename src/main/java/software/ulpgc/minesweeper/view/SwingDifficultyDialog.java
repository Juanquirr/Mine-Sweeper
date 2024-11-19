package software.ulpgc.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class SwingDifficultyDialog extends JPanel implements DifficultyDialog {
    private final ButtonGroup group;

    public SwingDifficultyDialog() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.darkGray);
        group = new ButtonGroup();
        for (JButton button : createButtons()) {
            add(button);
            button.addActionListener(e -> updateButtonStyles(button));
            group.add(button);
        }
    }

    private JButton[] createButtons() {
        CustomizedButton easyButton = new CustomizedButton("EASY");
        ButtonStyler.applySelectedButtonStyle(easyButton);
        return new JButton[]{
                easyButton,
                new CustomizedButton("MEDIUM"),
                new CustomizedButton("HARD"),
                new CustomizedButton("EXPERT")
        };
    }

    private void updateButtonStyles(JButton selectedButton) {
        Font customFont = selectedButton.getFont();
        for (Component component : getComponents()) {
            if (component.equals(selectedButton)) {
                ButtonStyler.applySelectedButtonStyle((CustomizedButton) component);
                continue;
            }
            ButtonStyler.applyNonSelectedButtonStyle((CustomizedButton) component);
        }

    }

    @Override
    public Difficulty getSelection() {
        return Difficulty.valueOf(((JButton) group.getSelection()).getText());
    }
}

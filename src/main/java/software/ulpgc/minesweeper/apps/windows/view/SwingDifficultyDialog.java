package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.model.Level;
import software.ulpgc.minesweeper.architecture.view.DifficultyDialog;
import software.ulpgc.minesweeper.apps.windows.view.customization.ButtonStyler;
import software.ulpgc.minesweeper.apps.windows.view.customization.CustomizedButton;

import javax.swing.*;
import java.awt.*;

public class SwingDifficultyDialog extends JPanel implements DifficultyDialog {
    private final ButtonGroup group;

    public SwingDifficultyDialog() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.darkGray);
        this.group = new ButtonGroup();
        for (JToggleButton button : createButtons()) {
            add(button);
            button.addActionListener(e -> updateButtonStyles(button));
            group.add(button);
        }
    }

    private JToggleButton[] createButtons() {
        CustomizedButton easyButton = new CustomizedButton("BEGINNER");
        ButtonStyler.applySelectedButtonStyle(easyButton);
        return new JToggleButton[]{
                easyButton,
                new CustomizedButton("INTERMEDIATE"),
                new CustomizedButton("EXPERT"),
        };
    }

    private void updateButtonStyles(JToggleButton selectedButton) {
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
    public Level get() {
        System.out.println(Level.valueOf(group.getSelection().getActionCommand()));
        return Level.valueOf(group.getSelection().getActionCommand());
    }
}

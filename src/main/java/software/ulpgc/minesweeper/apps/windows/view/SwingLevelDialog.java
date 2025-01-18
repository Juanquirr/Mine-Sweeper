package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.model.Level;
import software.ulpgc.minesweeper.architecture.view.LevelDialog;
import software.ulpgc.minesweeper.apps.windows.view.customization.CustomizedButton;

import javax.swing.*;
import java.awt.*;

public class SwingLevelDialog extends JPanel implements LevelDialog {
    private final ButtonGroup group;

    public SwingLevelDialog() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.group = new ButtonGroup();
        for (JToggleButton button : createButtons()) {
            add(button);
            button.addActionListener(x -> updateButtonStyles(button));
            group.add(button);
        }
    }

    private JToggleButton[] createButtons() {
        CustomizedButton beginnerButton = new CustomizedButton().personalizeButton("BEGINNER");
        beginnerButton.setSelected(true);
        return new JToggleButton[]{
                beginnerButton,
                new CustomizedButton().personalizeButton("INTERMEDIATE"),
                new CustomizedButton().personalizeButton("EXPERT"),
        };
    }

    private void updateButtonStyles(JToggleButton selectedButton) {
        for (Component component : getComponents()) {
            if (component.equals(selectedButton)) {
                ((CustomizedButton) component).setSelected(true);
                continue;
            }
            ((CustomizedButton) component).setSelected(false);
        }
    }

    @Override
    public Level get() {
        return Level.valueOf(group.getSelection().getActionCommand());
    }
}

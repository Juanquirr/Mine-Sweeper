package software.ulpgc.minesweeper.apps.windows.view.customization;

import java.awt.*;

public class ButtonStyler {
    public static void applySelectedButtonStyle(CustomizedButton button) {
        button.setBackground(new Color(51, 153, 255));
        button.setFont(button.getCustomFont().deriveFont(Font.BOLD, 35f));
        button.setSelected(true);
    }

    public static void applyNonSelectedButtonStyle(CustomizedButton button) {
        button.setBackground(Color.darkGray);
        button.setFont(button.getCustomFont().deriveFont(Font.BOLD, 30f));
        button.setSelected(false);
    }
}

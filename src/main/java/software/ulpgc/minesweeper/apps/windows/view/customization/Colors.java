package software.ulpgc.minesweeper.apps.windows.view.customization;

import java.awt.*;

public enum Colors {
    ONE(Color.BLUE),
    TWO(Color.GREEN),
    THREE(Color.MAGENTA),
    FOUR(Color.ORANGE);

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

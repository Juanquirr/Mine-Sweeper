package software.ulpgc.minesweeper.architecture.view;

import java.awt.*;

public enum Colors {
    ONE(Color.BLUE),
    TWO(Color.GREEN),
    THREE(Color.MAGENTA),
    FOUR(Color.orange);

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Level;

import java.awt.*;

public interface BoardDisplay {
    int CELL_SIZE = 40;

    void adjustDimensionTo(Level.Size size);
    void paint(PaintOrder... orders);
    void on(Click click);

    interface Click {
        Click NULL = (xOffset, yOffset) -> {};
        void offset(int xOffset, int yOffset);
    }

    public record PaintOrder(Color color, int x, int y, int width, int height) {
    }
}

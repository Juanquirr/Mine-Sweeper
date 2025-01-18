package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Level;

public interface BoardDisplay {
    int CELL_SIZE = 40;

    void adjustDimensionTo(Level.Size size);
    void paint(PaintOrder... orders);
    void on(Click click);

    interface Click {
        Click NULL = (xOffset, yOffset, button) -> {};
        void offset(int xOffset, int yOffset, int button);
    }

    record PaintOrder(Cell.Position position, Color color, Integer number, boolean flag) { }
}

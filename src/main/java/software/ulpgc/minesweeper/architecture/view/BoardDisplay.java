package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Level;

import java.awt.*;
import java.util.Objects;

public interface BoardDisplay {
    int CELL_SIZE = 40;

    void adjustDimensionTo(Level.Size size);
    void paint(PaintOrder... orders);
    void on(Click click);
    void clear();

    interface Click {
        Click NULL = (xOffset, yOffset) -> {};
        void offset(int xOffset, int yOffset);
    }

    public record PaintOrder(Color color, int x, int y, Integer number) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PaintOrder that = (PaintOrder) o;
            return x == that.x && y == that.y && Objects.equals(color, that.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

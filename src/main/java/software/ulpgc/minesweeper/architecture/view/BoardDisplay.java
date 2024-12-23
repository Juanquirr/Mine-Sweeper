package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Board;

public interface BoardDisplay {
    void paint(PaintOrder paintOrder);
    void on(Click click);

    interface Click {
        void offset(int offset);
    }

    public record PaintOrder(int width, int height) {
    }
}

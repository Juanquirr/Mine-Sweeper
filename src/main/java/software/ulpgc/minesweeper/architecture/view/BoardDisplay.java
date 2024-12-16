package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Board;

public interface BoardDisplay {
    BoardDisplay setBoard(Board boardMatrix);
    BoardDisplay showPanel();
    BoardDisplay hidePanel();
}

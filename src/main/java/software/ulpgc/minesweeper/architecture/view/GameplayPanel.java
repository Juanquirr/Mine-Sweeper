package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Board;

public interface GameplayPanel {
    GameplayPanel setBoard(Board boardMatrix);
    GameplayPanel showPanel();
    GameplayPanel hidePanel();
}

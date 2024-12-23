package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

public class BoardPresenter {
    private final BoardDisplay boardDisplay;

    public BoardPresenter(BoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
        this.boardDisplay.on(click());
    }

    private BoardDisplay.Click click() {
        return null;
    }

    public void show(Board board) {
        boardDisplay.paint(new BoardDisplay.PaintOrder(board.level().width(), board.level().height()));
    }
}

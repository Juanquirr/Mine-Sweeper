package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.SwingBoardDisplay;
import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.BoardExplorer;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BoardPresenter {
    private final BoardDisplay boardDisplay;
    private final BoardExplorer boardExplorer;
    private Board board;

    public BoardPresenter(BoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
        this.boardExplorer = new BoardExplorer();
        this.boardDisplay.on(click());
    }

    private BoardDisplay.Click click() {
        return (xOffset, yOffset) -> {
            Cell.Position position = new Cell.Position(xOffset / BoardDisplay.CELL_SIZE, yOffset / BoardDisplay.CELL_SIZE);
            this.board.initializeMinesExcluding(position);
            BoardExplorer e = boardExplorer.exploreFrom(this.board, position);
            BoardDisplay.PaintOrder[] array = e.safeCells().stream()
                    .map(
                            p -> new BoardDisplay.PaintOrder(
                                    Color.GREEN,
                                    p.row() * BoardDisplay.CELL_SIZE,
                                    p.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE
                            )
                    )
                    .toArray(BoardDisplay.PaintOrder[]::new);
            boardDisplay.paint(
                    array
            );
        };
    }

    public void show(Board board) {
        this.board = board;
        boardDisplay.adjustDimensionTo(board.level().size());
        boardDisplay.paint(
                IntStream.range(0, board.level().width() * board.level().height())
                        .mapToObj(
                                i -> new BoardDisplay.PaintOrder(
                                        Color.GRAY,
                                        BoardDisplay.CELL_SIZE * (i / board.level().width()),
                                        BoardDisplay.CELL_SIZE * (i % board.level().width()),
                                        BoardDisplay.CELL_SIZE,
                                        BoardDisplay.CELL_SIZE
                                )
                        )
                        .toArray(BoardDisplay.PaintOrder[]::new)
        );
    }
}

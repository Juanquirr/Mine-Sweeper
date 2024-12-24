package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.BoardExplorer;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.awt.*;
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
                                    Color.GRAY,
                                    p.row() * BoardDisplay.CELL_SIZE,
                                    p.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    null
                            )
                    )
                    .toArray(BoardDisplay.PaintOrder[]::new);
            BoardDisplay.PaintOrder[] array1 = e.edges().stream()
                    .map(
                            p -> new BoardDisplay.PaintOrder(
                                    Color.GRAY,
                                    p.row() * BoardDisplay.CELL_SIZE,
                                    p.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    countNearMines(p)
                            )
                    )
                    .toArray(BoardDisplay.PaintOrder[]::new);
            boardDisplay.paint(
                    array.length != 0 ? array :
                            new BoardDisplay.PaintOrder[]{
                            new BoardDisplay.PaintOrder(
                                    Color.RED,
                                    position.row() * BoardDisplay.CELL_SIZE,
                                    position.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    null
                            )}
            );
            boardDisplay.paint(
                    array1
            );
        };
    }

    private Integer countNearMines(Cell.Position p) {
        return Math.toIntExact(this.board.cellNeighborsOf(p).stream()
                .filter(n -> this.board.hasMineIn(n))
                .count());
    }

    public void show(Board board) {
        this.board = board;
        boardDisplay.clear();
        boardDisplay.adjustDimensionTo(board.level().size());
        boardDisplay.paint(
                IntStream.range(0, board.level().width() * board.level().height())
                        .mapToObj(
                                i -> new BoardDisplay.PaintOrder(
                                        Color.LIGHT_GRAY,
                                        BoardDisplay.CELL_SIZE * (i % board.level().width()),
                                        BoardDisplay.CELL_SIZE * (i / board.level().width()),
                                        BoardDisplay.CELL_SIZE,
                                        BoardDisplay.CELL_SIZE,
                                        null
                                )
                        )
                        .toArray(BoardDisplay.PaintOrder[]::new)
        );
    }
}

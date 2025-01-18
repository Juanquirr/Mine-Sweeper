package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.BoardExplorer;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.util.Map;
import java.util.stream.Stream;

public class PaintOrderBuilder implements Builder<BoardDisplay.PaintOrder> {
    private Cell.Position position;
    private Color color;
    private Integer number;
    private boolean flag;
    private final Map<Cell.CellState, Color> colorMap = Map.of(
            Cell.CellState.OPENED, Color.SafeCell,
            Cell.CellState.FLAGGED, Color.UnopenedCell,
            Cell.CellState.UNOPENED, Color.UnopenedCell
    );

    private PaintOrderBuilder() {
        flag = false;
    }

    public static PaintOrderBuilder create() {
        return new PaintOrderBuilder();
    }

    public static Stream<BoardDisplay.PaintOrder> boardPaintOrder(Board board, int dt) {
        return board.cells().stream()
                .map(c -> board.hasMineIn(c.position()) && board.cellAt(c.position()).cellState().equals(Cell.CellState.OPENED) ?
                        minePaintOrder(c, dt) :
                        (c.cellState().equals(Cell.CellState.FLAGGED)) ? flaggedPaintOrder(board, c, dt) :
                        (BoardExplorer.countNearMines(board, c.position()) != 0 ?
                            edgePaintOrder(board, dt, c) :
                            safePaintOrder(c, dt))
                );
    }

    private static BoardDisplay.PaintOrder minePaintOrder(Cell c, int dt) {
        return create().withPosition(PositionAdapter.adaptToPixel(c.position(), dt)).withColor(Color.MineCell).build();
    }

    private static BoardDisplay.PaintOrder flaggedPaintOrder(Board board, Cell c, int dt) {
        return (board.cellAt(c.position()).cellState().equals(Cell.CellState.FLAGGED)) ?
                create().withPosition(PositionAdapter.adaptToPixel(c.position(), dt)).withColor(Color.UnopenedCell).withFlag(true).build() :
                create().withPosition(PositionAdapter.adaptToPixel(c.position(), dt)).withColor(Color.UnopenedCell).withFlag(false).build();
    }

    private static BoardDisplay.PaintOrder safePaintOrder(Cell c, int dt) {
        return create().withPosition(PositionAdapter.adaptToPixel(c.position(), dt)).withColor(c.cellState().equals(Cell.CellState.OPENED) ? Color.SafeCell : Color.UnopenedCell).build();
    }

    private static BoardDisplay.PaintOrder edgePaintOrder(Board board, int dt, Cell c) {
        return create().withPosition(PositionAdapter.adaptToPixel(c.position(), dt))
                .withColor(c.cellState().equals(Cell.CellState.OPENED) ? Color.EdgeCell : Color.UnopenedCell)
                .withNumber(c.cellState().equals(Cell.CellState.OPENED) ? BoardExplorer.countNearMines(board, c.position()) : null).build();
    }

    public BoardDisplay.PaintOrder build() {
        return new BoardDisplay.PaintOrder(position, color, number, flag);
    }

    public PaintOrderBuilder withPosition(Cell.Position position) {
        this.position = position;
        return this;
    }

    public PaintOrderBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public PaintOrderBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public PaintOrderBuilder withFlag(boolean flag) {
        this.flag = flag;
        return this;
    }
}

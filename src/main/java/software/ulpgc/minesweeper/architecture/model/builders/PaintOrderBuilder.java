package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.BoardExplorer;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PaintOrderBuilder implements Builder<BoardDisplay.PaintOrder> {
    private Cell.Position position;
    private Color color;
    private Integer number;
    private boolean flag;

    private PaintOrderBuilder() {
        flag = false;
    }

    public static PaintOrderBuilder create() {
        return new PaintOrderBuilder();
    }

    public static Stream<BoardDisplay.PaintOrder> safeCell(BoardExplorer boardExplorer, int dt) {
        return boardExplorer.safeCells().stream().map(p -> create().withPosition(PositionAdapter.adaptToPixel(p, dt)).withColor(Color.SafeCell).build());
    }

    public static Stream<BoardDisplay.PaintOrder> edgeCell(BoardExplorer boardExplorer, Game game, int dt) {
        return boardExplorer.edges().stream().map(p -> create().withPosition(PositionAdapter.adaptToPixel(p, dt)).withColor(Color.EdgeCell).withNumber(boardExplorer.countNearMines(game.board(), p)).build());
    }

    public static Stream<BoardDisplay.PaintOrder> mineCell(Set<Cell.Position> cells, int dt) {
        return cells.stream().map(p -> create().withPosition(PositionAdapter.adaptToPixel(p, dt)).withColor(Color.MineCell).build());
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

package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Level;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BoardBuilder implements Builder<Board> {
    private Level level;
    private List<Cell> cells;
    private Set<Cell.Position> mines;

    private BoardBuilder() {
    }

    public static BoardBuilder create() {
        return new BoardBuilder();
    }

    public BoardBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    public BoardBuilder withCells(List<Cell> cells) {
        this.cells = cells;
        return this;
    }

    public BoardBuilder withMines(Set<Cell.Position> mines) {
        this.mines = mines;
        return this;
    }

    @Override
    public Board build() {
        return Objects.isNull(cells) ? new Board(level) : new Board(level, cells, mines);
    }
}

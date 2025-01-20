package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Level;

import java.util.*;

public class BoardBuilder implements Builder<Board> {
    private Level level;
    private List<Cell> cells;
    private Set<Cell.Position> mines;

    private BoardBuilder() {
        this.cells = new ArrayList<>();
        this.mines = new HashSet<>();
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
        return new Board(level, cells, mines);
    }
}

package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.model.builders.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.builders.CellBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Level level;
    private final List<Cell> cells;
    private final Set<Cell.Position> mines;

    public Board(Level level, List<Cell> cells, Set<Cell.Position> mines) {
        this.level = level;
        this.cells = !cells.isEmpty() ? cells : initializeCells();
        this.mines = mines;
    }

    public Level level() {
        return level;
    }

    public Cell cellAt(Cell.Position position) {
        return cells.get(PositionUtilities.indexFromPosition(position, level.width()));
    }

    public List<Cell> cells() {
        return new ArrayList<>(cells);
    }

    public List<Cell.Position> nearPositionsOf(Cell.Position position) {
        return PositionUtilities.getNearPositionInBoundsFrom(position, level.width(), level.height());
    }

    public boolean hasMineIn(Cell.Position position) {
        return mines.contains(position);
    }

    public Set<Cell.Position> mines() {
        return new HashSet<>(mines);
    }

    public void initializeMinesExcluding(Cell.Position position) {
        if (!this.mines.isEmpty()) return;
        this.mines.addAll(new Random()
                .ints(0, level().width())
                .mapToObj(x -> new Cell.Position(x, new Random().nextInt(level().height())))
                .filter(p -> !p.equals(position))
                .distinct()
                .limit(level().numberOfMines())
                .collect(Collectors.toSet()));
    }

    private List<Cell> initializeCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < level.width() * level.height(); i++) {
            Cell.Position pos = new Cell.Position(i % level.width(), i / level.width());
            cells.add(CellBuilder.create().withPosition(pos).withCellState(Cell.CellState.UNOPENED).build());
        }
        return cells;
    }
}

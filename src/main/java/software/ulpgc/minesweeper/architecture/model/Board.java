package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.view.BuilderFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Level level;
    private final List<Cell> cells;

    private final Set<Cell.Position> mines;

    public Board(Level level) {
        this.level = level;
        this.cells = initializeCells();
        this.mines = new HashSet<>();
    }

    public Level level() {
        return level;
    }

    public Cell cellAt(Cell.Position position) {
        return cells.get(position.x() * level.width() + position.y());
    }

    public List<Cell.Position> cellNeighborsOf(Cell.Position position) {
        return Arrays.stream(getDeltas())
                .map(d -> new Cell.Position(position.x() + d[0], position.y() + d[1]))
                .filter(this::isInBounds)
                .toList();
    }

    private boolean isInBounds(Cell.Position p) {
        return p.x() >= 0 && p.x() < level().width() && p.y() >= 0 && p.y() < level().height();
    }

    private List<Cell> initializeCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < level.width() * level.height(); i++) {
            Cell.Position pos = new Cell.Position(i % level.width(), i / level.width());
            ((CellBuilder) BuilderFactory.getBuilder(Cell.class)).position(pos).gameState(Cell.CellState.UNOPENED).build();
        }
        return cells;
    }

    public void initializeMinesExcluding(Cell.Position position) {
        if (!this.mines.isEmpty()) return;
        this.mines.addAll(new Random()
                .ints(0, level().width())
                .mapToObj(x -> new Cell.Position(x, randomHeight()))
                .filter(p -> !p.equals(position))
                .distinct()
                .limit(level().numberOfMines())
                .collect(Collectors.toSet()));
    }

    public boolean hasMineIn(Cell.Position position) {
        return mines.contains(position);
    }

    private static int[][] getDeltas() {
        return new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, 1}, {1, 1}, {1, 0},
                {1, -1}, {0, -1}
        };
    }

    private int randomHeight() {
        return new Random().nextInt(level().height());
    }

    public Set<Cell.Position> mines() {
        return new HashSet<>(mines);
    }
}

package software.ulpgc.minesweeper.architecture.model;

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
        return cells.get(position.row() * level.width() + position.column());
    }

    public List<Cell.Position> cellNeighborsOf(Cell.Position position) {
        return Arrays.stream(getDeltas())
                .map(d -> new Cell.Position(position.row() + d[0], position.column() + d[1]))
                .filter(this::isInBounds)
                .toList();
    }

    private boolean isInBounds(Cell.Position p) {
        return p.row() >= 0 && p.row() < level().height() && p.column() >= 0 && p.column() < level().width();
    }

    private List<Cell> initializeCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < level.width() * level.height(); i++) {
            Cell.Position pos = new Cell.Position(i / level.width(), i % level.width());
            cells.add(
                    new Cell() {
                        private final CellState cellState = CellState.NonSelected;
                        private final Cell.Position position = pos;

                        @Override
                        public CellState cellState() {
                            return cellState;
                        }

                        @Override
                        public Position position() {
                            return pos;
                        }
                    }
            );
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
}

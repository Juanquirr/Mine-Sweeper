package software.ulpgc.minesweeper.architecture.model;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Difficulty difficulty;
    private final Map<Position, Cell> cells;
    private Set<Position> mines;

    private Board(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.cells = new HashMap<>();
        initializeCells();
        this.mines = new HashSet<>();
    }

    private void initializeCells() {
        for (int i = 0; i < getNumberOfCells(); i++)
            createCellWithNeighbours(getOrCreateCellAt(createPositionGiven(i)));
    }

    private Position createPositionGiven(int i) {
        return new Position(i / difficulty.width(), i % difficulty.width());
    }

    private void createCellWithNeighbours(Cell cell) {
        getNeighbourPositionsWithDeltas(cell.position())
                .forEach(p -> cell.addNeighbour(getOrCreateCellAt(p)));
    }

    private Cell getOrCreateCellAt(Position position) {
        return cells.computeIfAbsent(position, Cell::new);
    }

    private List<Position> getNeighbourPositionsWithDeltas(Position position) {
        return Arrays.stream(getDeltas())
                .map(delta -> getNeighbourPositionWithDelta(delta, position))
                .filter(this::isInBounds)
                .toList();
    }

    private static Position getNeighbourPositionWithDelta(int[] delta, Position centralPosition) {
        return new Position(
                centralPosition.x() + delta[0],
                centralPosition.y() + delta[1]
        );
    }

    private static int[][] getDeltas() {
        return new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, 1}, {1, 1}, {1, 0},
                {1, -1}, {0, -1}
        };
    }

    private boolean isInBounds(Position pos) {
        return pos.x() >= 0 && pos.x() < difficulty.height() && pos.y() >= 0 && pos.y() < difficulty.width();
    }

    private int getNumberOfCells() {
        return difficulty.width() * difficulty.height();
    }

    public Set<Position> initializeMines(Position position) {
        if (!this.mines.isEmpty()) return this.mines;
        return this.mines = new Random()
                .ints(0, difficulty().width())
                .mapToObj(x -> new Position(x, randomHeight()))
                .filter(p -> !p.equals(position))
                .distinct()
                .limit(difficulty().numberOfMines())
                .collect(Collectors.toSet());
    }

    private int randomHeight() {
        return new Random().nextInt(difficulty().height());
    }

    public Difficulty difficulty() {
        return difficulty;
    }

    public Set<Position> mines() {
        return mines;
    }

    public static Board ofDifficulty(Difficulty difficulty) {
        return new Board(difficulty);
    }

    public Cell cellAt(Position position) {
        return cells.get(position);
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        int last = 0;
        for (int i = 0; i < getNumberOfCells(); i++) {
            if (i / difficulty.width() > last) r.append("\n");
            r.append(
                    String.format("%s ", cells.get(getPosition(i)))
            );
            last = i / difficulty.width();
        }
       return r.toString();
    }

    private Position getPosition(int i) {
        return createPositionGiven(i);
    }

    public static class Builder {
        private Difficulty difficulty;
        private Set<Position> mines;

        public Builder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Board build() {
            return Board.ofDifficulty(difficulty);
        }
    }
}

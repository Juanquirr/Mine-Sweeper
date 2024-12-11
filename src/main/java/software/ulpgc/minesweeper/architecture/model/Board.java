package software.ulpgc.minesweeper.architecture.model;

import java.util.*;

public class Board {
    private final Difficulty difficulty;
    private final Map<Position, Cell> cells;
    private final Set<Position> mines;

    private Board(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.cells = new HashMap<>();
        initializeCells();
        this.mines = initializeMines();
    }

    private void initializeCells() {
        for (int i = 0; i < getNumberOfCells(); i++) {
            Position pos = new Position(i / difficulty.width(), i % difficulty.width());
            Cell cell;
            cells.put(pos, cell = new Cell(pos));
            createWithNeighbours(cell);
        }
    }

    private void createWithNeighbours(Cell cell) {
        for (int[] delta : getDeltas()) {
            Position neighbourPosition = getNeighbourPosition(delta, cell.position());
            cell.addNeighbour(
                    !isInBounds(neighbourPosition) ?
                            null : cells.computeIfAbsent(neighbourPosition, Cell::new)
            );
        }
    }

    private static Position getNeighbourPosition(int[] delta, Position centralPosition) {
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

    private Set<Position> initializeMines() {
        Set<Position> positions = new HashSet<>();
        Random random = new Random();
        while (positions.size() < difficulty.numberOfMines())
            positions.add(
                    new Position(
                            random.nextInt(difficulty.width()),
                            random.nextInt(difficulty.height())
                    )
            );
        return positions;
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

    public Cell get(Position position) {
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
        return new Position(i / difficulty.width(), i % difficulty.width());
    }
}

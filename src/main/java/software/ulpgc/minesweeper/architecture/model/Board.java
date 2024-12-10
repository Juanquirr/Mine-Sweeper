package software.ulpgc.minesweeper.architecture.model;

import java.util.*;

public class Board {
    private final Difficulty difficulty;
    private final Map<Position, Cell> cells;
    private final Set<Position> mines;

    private Board(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.cells = initializeCells();
        this.mines = initializeMines();
    }

    private Map<Position, Cell> initializeCells() {
        Map<Position, Cell> cells = new HashMap<>();
        for (int i = 0; i < getNumberOfCells(); i++) {
            Position pos = new Position(i / difficulty.width(), i % difficulty.width());
            cells.put(pos, new Cell(pos));
        }
        return cells;
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

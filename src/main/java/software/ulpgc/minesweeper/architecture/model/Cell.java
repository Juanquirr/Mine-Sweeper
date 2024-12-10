package software.ulpgc.minesweeper.architecture.model;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final Cell.CellState cellState;
    private final Position position;
    private final Set<Cell> neighbors;

    public Cell(Position position) {
        this.cellState = CellState.NonSelected;
        this.position = position;
        this.neighbors = new HashSet<>();
    }

    public Cell addNeighbor(Cell cell) {
        neighbors.add(cell);
        return this;
    }

    public CellState cellState() {
        return cellState;
    }

    public Position position() {
        return position;
    }

    public Set<Cell> neighbors() {
        return neighbors;
    }

    public enum CellState {
        NonSelected, Selected, Flagged, Unknown
    }

    @Override
    public String toString() {
        return position.toString();
    }
}

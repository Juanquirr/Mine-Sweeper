package software.ulpgc.minesweeper.architecture.model;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final Cell.CellState cellState;
    private final Position position;
    private final Set<Cell> neighbours;

    public Cell(Position position) {
        this.cellState = CellState.NonSelected;
        this.position = position;
        this.neighbours = new HashSet<>();
    }

    public Cell addNeighbour(Cell cell) {
        neighbours.add(cell);
        return this;
    }

    public CellState cellState() {
        return cellState;
    }

    public Position position() {
        return position;
    }

    public Set<Cell> neighbors() {
        return neighbours;
    }

    public enum CellState {
        NonSelected, Selected, Flagged, Unknown
    }

    @Override
    public String toString() {
        return position.toString();
    }
}

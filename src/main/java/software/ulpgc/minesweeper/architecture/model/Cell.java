package software.ulpgc.minesweeper.architecture.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Cell.CellState cellState;
    private final Position position;
    private final List<Cell> neighbours;

    public Cell(Position position) {
        this.cellState = CellState.NonSelected;
        this.position = position;
        this.neighbours = new ArrayList<>();
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

    public List<Cell> neighbors() {
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

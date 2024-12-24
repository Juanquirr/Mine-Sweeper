package software.ulpgc.minesweeper.architecture.model;

public interface Cell {
    Position position();
    record Position(int row, int column) {}

    CellState cellState();
    enum CellState {
        NonSelected, Selected, Flagged, Unknown
    }
}

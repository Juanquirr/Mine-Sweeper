package software.ulpgc.minesweeper.architecture.model;

public interface Cell {
    Position position();

    CellState cellState();

    Cell open();

    record Position(int x, int y) {}

    enum CellState {
        UNOPENED, OPENED, FLAGGED, UNKOWN
    }
}

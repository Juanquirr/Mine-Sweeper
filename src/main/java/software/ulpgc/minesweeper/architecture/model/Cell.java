package software.ulpgc.minesweeper.architecture.model;

public interface Cell {
    Position position();

    CellState cellState();

    Cell open();

    Cell flag();

    record Position(int x, int y) {}

    enum CellState {
        UNOPENED, OPENED, FLAGGED
    }
}

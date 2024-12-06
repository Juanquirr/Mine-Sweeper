package software.ulpgc.minesweeper.architecture.model;

public class Cell {
    private final Cell.CellState cellState;
    private final Cell.CellType cellType;

    public Cell() {
        this.cellState = CellState.NonSelected;
        this.cellType = CellType.NotBomb;
    }

    public Cell(CellState cellState, CellType cellType) {
        this.cellState = cellState;
        this.cellType = cellType;
    }

    public CellState getCellState() {
        return cellState;
    }

    public CellType getCellType() {
        return cellType;
    }

    public enum CellState {
        NonSelected, Selected, Flag, QuestionMark
    }

    public enum CellType {
        Bomb, NotBomb
    }

    @Override
    public String toString() {
        return "Cell{" +
                "state=" + cellState +
                ", type=" + cellType +
                '}';
    }
}

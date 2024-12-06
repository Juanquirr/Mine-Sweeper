package software.ulpgc.minesweeper.architecture.model;

public class Cell {
    private final Cell.CellState cellState;
    private final Cell.CellType cellType;
    private int nearBombs;

    public Cell() {
        this.cellState = CellState.NonSelected;
        this.cellType = CellType.NotBomb;
        nearBombs = 0;
    }

    public Cell(CellState cellState, CellType cellType) {
        this.cellState = cellState;
        this.cellType = cellType;
        this.nearBombs = -1;
    }

    public CellState getCellState() {
        return cellState;
    }

    public CellType getCellType() {
        return cellType;
    }

    public int getNearBombs() {
        return this.nearBombs;
    }

    public void addNearBomb(){
        if (getCellType() == CellType.Bomb) return;
        this.nearBombs++;
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
                ", counter=" + nearBombs +
                '}';
    }
}

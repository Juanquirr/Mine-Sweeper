package software.ulpgc.minesweeper.model;

public class Cell {
    private Cell.CellState cellState;
    private Cell.CellType cellType;

    public Cell() {
        this.cellState = CellState.NonSelected;
        this.cellType = CellType.Safe;
    }

    public CellState getCellState() {
        return cellState;
    }

    /*
    public void setCellState(CellState newState) {      // no estoy seguro de si necesitamos este método,
        cellState = newState;                           // pero supongo que sí, al menos para cuando le
    }                                                   // pongamos una bandera a la celda, lo que debe
                                                        // reducir el contador de bombas. Si no lo eliminamos
     */

    public void setBomb() {
        cellType = CellType.Bomb;
    }

    public enum CellState {
        NonSelected, Selected, Flag, QuestionMark
    }

    public enum CellType {
        Bomb, Safe
    }

    @Override
    public String toString() {
        return "Cell{" +
                "state=" + cellState +
                ", type=" + cellType +
                '}';
    }
}

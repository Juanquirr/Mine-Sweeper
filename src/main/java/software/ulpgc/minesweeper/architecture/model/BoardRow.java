package software.ulpgc.minesweeper.architecture.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardRow implements Iterable<Cell> {
    private final int size;
    private final List<Cell> rowData;

    private BoardRow(int rowLength) {
        this.size = rowLength;
        this.rowData = createRow();
    }

    public static BoardRow ofSize(int size) {
        return new BoardRow(size);
    }

    private List<Cell> createRow() {
        List<Cell> row = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            row.add(new Cell());
        }
        return row;
    }

    public int size() {
        return size;
    }

    public List<Cell> rowData() {
        return rowData;
    }

    @Override
    public String toString() {
        return "BoardRow{" +
                "size=" + size +
                ", rowData=" + rowData +
                '}';
    }

    public void setBomb(int x) {
        rowData.set(x, new Cell(Cell.CellState.NonSelected, Cell.CellType.Bomb));
    }

    @Override
    public Iterator<Cell> iterator() {
        return rowData.iterator();
    }
}

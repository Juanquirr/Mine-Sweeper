package software.ulpgc.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {
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
}

package software.ulpgc.minesweeper.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardMatrix implements Iterable<BoardRow> {
    private final int height;
    private final int width;
    private final List<BoardRow> matrix;

    private BoardMatrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.matrix = initializeMatrix();
    }

    public static BoardMatrix ofSize(int height, int width) {
        return new BoardMatrix(height, width);
    }

    private List<BoardRow> initializeMatrix() {
        List<BoardRow> matrix = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            matrix.add(BoardRow.ofSize(width));
        }
        return matrix;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    @Override
    public Iterator<BoardRow> iterator() {
        return matrix.iterator();
    }
}

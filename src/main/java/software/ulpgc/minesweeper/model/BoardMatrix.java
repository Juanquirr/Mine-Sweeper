package software.ulpgc.minesweeper.model;

import software.ulpgc.minesweeper.view.Difficulty;

import java.util.*;
import java.util.List;

public class BoardMatrix implements Iterable<BoardRow> {
    private final int height;
    private final int width;
    private final List<BoardRow> matrix;
    private final Difficulty difficulty;
    private final int bombsCounter;
    private final Set<Position> positions;

    private BoardMatrix(int height, int width, Difficulty difficulty) {
        this.height = height;
        this.width = width;
        this.difficulty = difficulty;
        this.bombsCounter = difficulty.getBombsCounter();
        this.positions = initializeBombCells();
        this.matrix = initializeMatrix();
    }

    private Set<Position> initializeBombCells() {
        Set<Position> positions = new HashSet<>();
        Random random = new Random();
        while (positions.size() < bombsCounter)
            positions.add(
                    new Position(
                            random.nextInt(width),
                            random.nextInt(height)
                    )
            );

        return positions;
    }

    public static BoardMatrix ofSize(int height, int width, Difficulty difficulty) {
        return new BoardMatrix(height, width, difficulty);
    }

    private List<BoardRow> initializeMatrix() {
        List<BoardRow> matrix = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            matrix.add(BoardRow.ofSize(width));
        }
        return defineBombs(matrix);
    }

    private List<BoardRow> defineBombs(List<BoardRow> matrix) {
        for (Position position : positions) {
            BoardRow row = matrix.get(position.y());
            row.setBomb(position.x());
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

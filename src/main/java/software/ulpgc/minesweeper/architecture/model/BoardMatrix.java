package software.ulpgc.minesweeper.architecture.model;

import java.util.*;
import java.util.List;

public class BoardMatrix implements Iterable<BoardRow> {
    private final int height;
    private final int width;
    private final List<BoardRow> matrix;
    private final Difficulty difficulty;
    private final int bombsCounter;
    private final Set<Position> positions;

    private BoardMatrix(Difficulty difficulty) {
        this.height = difficulty.height();
        this.width = difficulty.width();
        this.difficulty = difficulty;
        this.bombsCounter = difficulty.getBombsCounter();
        this.positions = initializeBombCells();
        this.matrix = defineBombs(initializeMatrix());

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

    public static BoardMatrix ofSize(Difficulty difficulty) {
        return new BoardMatrix(difficulty);
    }

    private List<BoardRow> initializeMatrix() {
        List<BoardRow> matrix = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            matrix.add(BoardRow.ofSize(width));
        }
        return matrix;
    }

    private List<BoardRow> defineBombs(List<BoardRow> matrix) {
        for (Position position : positions) {
            BoardRow row = matrix.get(position.x());
            row.setBomb(position.y());
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int xi = position.x() + i;
                    int yj = position.y() + j;

                    if (xi < 0) continue;
                    if (yj < 0) continue;
                    if (yj >= row.rowData().size()) continue;
                    if (xi >= this.height) continue;
                    matrix.get(xi).get(yj).addNearBomb();
                    // TODO re-factor
                }
            }
        }
        return matrix;
    }

    public BoardRow get(int y) {
        return matrix.get(y);
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Difficulty difficulty() {
        return difficulty;
    }

    @Override
    public Iterator<BoardRow> iterator() {
        return matrix.iterator();
    }
}

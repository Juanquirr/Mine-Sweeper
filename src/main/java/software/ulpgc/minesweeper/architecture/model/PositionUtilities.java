package software.ulpgc.minesweeper.architecture.model;

import java.util.Arrays;
import java.util.List;

public class PositionUtilities {
    public static boolean isInBounds(Cell.Position position, int width, int height) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    public static int[][] getDeltasForNearPositions() {
        return new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
    }

    public static List<Cell.Position> getNearPositionInBoundsFrom(Cell.Position position, int width, int height) {
        return Arrays.stream(getDeltasForNearPositions())
                .map(d -> new Cell.Position(position.x() + d[0], position.y() + d[1]))
                .filter(p -> isInBounds(p, width, height))
                .toList();
    }

    public static int indexFromPosition(Cell.Position position, int width) {
        return position.y() * width + position.x();
    }
}

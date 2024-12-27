package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.architecture.model.Cell;

public class PositionAdapter {
    public static Cell.Position adaptToPixel(Cell.Position p, int dp) {
        return new Cell.Position(p.x() * dp, p.y() * dp);
    }

    private static int pixelToInteger(int offset, int dp) {
        return offset / dp;
    }
}

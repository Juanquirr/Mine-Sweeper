package software.ulpgc.minesweeper.architecture.model;

import java.util.*;

public class BoardExplorer {
    public static Map<String, List<Cell.Position>> exploreFrom(Board board, Cell.Position startPosition) {
        if (board.cellAt(startPosition).cellState().equals(Cell.CellState.OPENED)
                || board.cellAt(startPosition).cellState().equals(Cell.CellState.FLAGGED)) return new HashMap<>();
        return explore(board, buildCellsMap(), startPosition, new HashSet<>());
    }

    private static Map<String, List<Cell.Position>> buildCellsMap() {
        Map<String, List<Cell.Position>> openedCells = new HashMap<>();
        List<Cell.Position> safeCells = new ArrayList<>();
        List<Cell.Position> edgeCells = new ArrayList<>();
        openedCells.put("safe", safeCells);
        openedCells.put("edge", edgeCells);
        return openedCells;
    }

    private static Map<String, List<Cell.Position>> explore(Board board, Map<String, List<Cell.Position>> openedCells, Cell.Position startPosition, Set<Cell.Position> visited) {
        if (visited.contains(startPosition) || board.hasMineIn(startPosition))
            return openedCells;

        visited.add(startPosition);

        for (Cell.Position position : board.nearPositionsOf(startPosition)) {
            if (board.hasMineIn(position)) {
                openedCells.get("edge").add(startPosition);
                return openedCells;
            }
        }

        openedCells.get("safe").add(startPosition);
        board.nearPositionsOf(startPosition).forEach(p -> explore(board, openedCells, p, visited));
        return openedCells;
    }

    public static Integer countNearMines(Board board, Cell.Position position) {
        return Math.toIntExact(board.nearPositionsOf(position).stream()
                .filter(board::hasMineIn)
                .count());
    }

    public static Integer countRemainMines(Board board) {
        return board.level().numberOfMines() - Math.toIntExact(board.cells().stream()
                .filter(c -> c.cellState().equals(Cell.CellState.FLAGGED))
                .count());
    }
}

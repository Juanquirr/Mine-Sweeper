package software.ulpgc.minesweeper.architecture.model;

import java.util.HashSet;
import java.util.Set;

public class BoardExplorer {
    private final Set<Cell.Position> safeCells;
    private final Set<Cell.Position> edges;

    public BoardExplorer() {
        this.safeCells = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public void exploreFrom(Board board, Cell.Position startPosition) {
        safeCells.clear();
        edges.clear();
        Set<Cell.Position> visited = new HashSet<>();
        explore(board, startPosition, visited);
    }

    private void explore(Board board, Cell.Position startPosition, Set<Cell.Position> visited) {
        if (visited.contains(startPosition) || board.hasMineIn(startPosition)) return;

        visited.add(startPosition);

        for (Cell.Position position : board.nearPositionsOf(startPosition)) {
            if (board.hasMineIn(position)) {
                edges.add(startPosition);
                return;
            }
        }

        safeCells.add(startPosition);
        board.nearPositionsOf(startPosition).forEach(p -> explore(board, p, visited));
    }

    public Set<Cell.Position> safeCells() {
        return new HashSet<>(safeCells);
    }

    public Set<Cell.Position> edges() {
        return new HashSet<>(edges);
    }

    public Integer countNearMines(Board board, Cell.Position position) {
        return Math.toIntExact(board.nearPositionsOf(position).stream()
                .filter(board::hasMineIn)
                .count());
    }
}

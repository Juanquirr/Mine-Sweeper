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

    public BoardExplorer exploreFrom(Board board, Cell.Position startPosition) {
        safeCells.clear();
        edges.clear();
        Set<Cell.Position> visited = new HashSet<>();
        explore(board, startPosition, visited);
        return this;
    }

    private void explore(Board board, Cell.Position startPosition, Set<Cell.Position> visited) {
        if (visited.contains(startPosition) || board.hasMineIn(startPosition)) return;

        visited.add(startPosition);

        for (Cell.Position position : board.cellNeighborsOf(startPosition)) {
            if (board.hasMineIn(position)) {
                edges.add(startPosition);
                return;
            }
        }

        board.cellNeighborsOf(startPosition).forEach(n -> explore(board, n, visited));

        safeCells.add(startPosition);
    }

    public Set<Cell.Position> safeCells() {
        return new HashSet<>(safeCells);
    }

    public Set<Cell.Position> edges() {
        return new HashSet<>(edges);
    }
}

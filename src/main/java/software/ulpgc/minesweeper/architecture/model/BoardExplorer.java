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
        System.out.println(startPosition);
        System.out.println(board.cellNeighborsOf(startPosition));
        safeCells.clear();
        edges.clear();
        Set<Cell.Position> visited = new HashSet<>();
        explore(board, startPosition, visited);
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

    public Integer countNearMines(Board board, Cell.Position position) {
        return Math.toIntExact(board.cellNeighborsOf(position).stream()
                .filter(board::hasMineIn)
                .count());
    }
}

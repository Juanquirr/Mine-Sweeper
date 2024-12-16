package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Position;
import software.ulpgc.minesweeper.apps.windows.view.customization.Colors;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SwingGameplayPanel extends JPanel implements BoardDisplay {
    private final JButton finishButton;
    private Board boardMatrix;
    private final JPanel headerPanel;
    private final JPanel boardPanel;
    private final JPanel footerPanel;
    private final Chronometer timer;
    private final Map<Integer, Color> colorMap;
    private final Map<Position, JButton> buttons = new HashMap<>();
    private boolean canInitialize = false;
    public SwingGameplayPanel() {
        this.timer = new Chronometer();
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(26,255,110));
        setLayout(new BorderLayout());
        this.colorMap = initializeColorMap();
        add(BorderLayout.NORTH, this.headerPanel = createHeaderPanel());
        add(BorderLayout.CENTER, this.boardPanel = createBoardPanel());
        add(BorderLayout.SOUTH, this.footerPanel = createFooterPanel(this.finishButton = new JButton("Finish")));
    }

    private Map<Integer, Color> initializeColorMap() {
        Map<Integer, Color> colorMap = new HashMap<>();
        colorMap.put(1, Colors.ONE.getColor());
        colorMap.put(2, Colors.TWO.getColor());
        colorMap.put(3, Colors.THREE.getColor());
        colorMap.put(4, Colors.FOUR.getColor());
        return colorMap;
    }

    private JPanel createFooterPanel(JButton button) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        return panel;
    }

    private JPanel createBoardPanel() {
        return new JPanel(new GridLayout());
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(timer);
        return panel;
    }
    @Override
    public BoardDisplay setBoard(Board board) {
        this.canInitialize = false;
        boardPanel.removeAll();
        buttons.clear();
        boardPanel.setLayout(new GridLayout(board.difficulty().width(), board.difficulty().height()));
        for (int i = 0; i < board.difficulty().width() * board.difficulty().height(); i++) {
            JButton button = new JButton();
            Position position = new Position(i / board.difficulty().width(), i % board.difficulty().width());
            boardPanel.add(
                    buttons.computeIfAbsent(
                        position, _ -> button
                    )
            );
        }
        buttons.entrySet().forEach(e -> e.getValue().addActionListener(_ -> this.exploreSafeCells(board, e.getKey())));
        return this;
    }

    private String getMinesCounterString(Board board, Cell cell) {
        if (board.mines().contains(cell.position())) return "B";
        return getMinesCounter(board, cell.position());
    }

    private void exploreSafeCells(Board board, Position startPosition) {
        if (!canInitialize) board.initializeMines(startPosition);
        if (board.mines().contains(startPosition)) {
            System.out.println("MINE!");
            return;
        }
        this.canInitialize = true;
        Set<Position> visited = new HashSet<>();
        Set<Position> safeCells = new HashSet<>();
        Set<Position> edges = new HashSet<>();
        explore(board, board.cellAt(startPosition), visited, safeCells, edges);
        edges.forEach(p -> buttons.get(p).setText(getMinesCounterString(board, board.cellAt(p))));
    }

    private void explore(Board board, Cell cell, Set<Position> visited, Set<Position> safeCells, Set<Position> edgeCells) {
        if (visited.contains(cell.position())) return;

        visited.add(cell.position());

        if (hasMinesInNeighbors(board, cell)) {
            edgeCells.add(cell.position());
            return;
        }

        safeCells.add(cell.position());
        buttons.get(cell.position()).setBackground(Color.BLACK);

        cell.neighbors().forEach(n -> explore(board, n, visited, safeCells, edgeCells));
    }

    private boolean hasMinesInNeighbors(Board board, Cell cell) {
        return cell.neighbors().stream()
                .map(Cell::position)
                .anyMatch(p -> board.mines().contains(p));
    }

    private String getMinesCounter(Board board, Position position) {
        int i = 0;
        for (Cell neighbor : board.cellAt(position).neighbors().stream().filter(Objects::nonNull).toList()) {
            if (board.mines().contains(neighbor.position()))
                i++;
        }
        return String.valueOf(i);
    }

    @Override
    public BoardDisplay showPanel() {
        setVisible(true);
        timer.start();
        return this;
    }

    @Override
    public BoardDisplay hidePanel() {
        setVisible(false);
        timer.stop();
        return this;
    }

    public Board boardMatrix() {
        return boardMatrix;
    }

    public JButton finishButton() {
        return finishButton;
    }
}

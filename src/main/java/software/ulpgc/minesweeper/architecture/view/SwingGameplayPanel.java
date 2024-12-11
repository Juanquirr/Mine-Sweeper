package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Position;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SwingGameplayPanel extends JPanel implements GameplayPanel {
    private final JButton finishButton;
    private Board boardMatrix;
    private final JPanel headerPanel;
    private final JPanel boardPanel;
    private final JPanel footerPanel;
    private final Chronometer timer;
    private final Map<Integer, Color> colorMap;
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
    public GameplayPanel setBoard(Board board) {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(board.difficulty().width(), board.difficulty().height()));
        Set<Position> mines = board.mines();
        for (int i = 0; i < board.difficulty().height() * board.difficulty().width(); i++)
            boardPanel.add(new JButton(getMinesCounter(board, new Position(i / board.difficulty().width(), i % board.difficulty().width()))));
        return this;
    }

    private String getMinesCounter(Board board, Position position) {
        if (board.mines().contains(position)) return "B";
        Cell cell = board.get(position);
        int i = 0;
        for (Cell neighbor : cell.neighbors().stream().filter(Objects::nonNull).toList()) {
            if (board.mines().contains(neighbor.position()))
                i++;
        }
        return String.valueOf(i);
    }

    @Override
    public GameplayPanel showPanel() {
        setVisible(true);
        timer.start();
        return this;
    }

    @Override
    public GameplayPanel hidePanel() {
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

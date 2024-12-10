package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
    public GameplayPanel setBoard(Board boardMatrix) {
        // TODO
        return this;
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

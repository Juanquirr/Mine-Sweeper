package software.ulpgc.minesweeper.architecture.view;

import software.ulpgc.minesweeper.architecture.model.BoardMatrix;
import software.ulpgc.minesweeper.architecture.model.BoardRow;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Difficulty;

import javax.swing.*;
import java.awt.*;

public class SwingGameplayPanel extends JPanel implements GameplayPanel {
    private final JButton finishButton;
    private boolean isGreen = false;
    private BoardMatrix boardMatrix;
    private final JPanel headerPanel;
    private final JPanel boardPanel;
    private final JPanel footerPanel;

    public SwingGameplayPanel() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(26,255,110));
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, this.headerPanel = createHeaderPanel());
        add(BorderLayout.CENTER, this.boardPanel = createBoardPanel());
        add(BorderLayout.SOUTH, this.footerPanel = createFooterPanel(this.finishButton = new JButton("Finish")));
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
        panel.add(new JLabel("MineSweeper"));
        return panel;
    }

    private JButton createSquare() {
        JButton button = new JButton("");
        button.addActionListener(e -> {
            button.setText("X");
            button.setEnabled(false);
        });
        button.setBackground(isGreen ? new Color(0, 255, 0) : new Color(153, 255, 153));
        return button;
    }

    public boolean isGreen() {
        return isGreen;
    }

    @Override
    public GameplayPanel setBoard(BoardMatrix boardMatrix) {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(boardMatrix.height(), boardMatrix.width()));
        this.boardMatrix= boardMatrix;
        for (BoardRow row : boardMatrix) {
            for (Cell cell : row)
                boardPanel.add(new JButton(
                        cell.getCellType() == Cell.CellType.Bomb ?
                                "BOMB!" :
                                "CELL"
                ));
        }
        boardPanel.repaint();
        return this;
    }

    @Override
    public GameplayPanel showPanel() {
        setVisible(true);
        return this;
    }

    @Override
    public GameplayPanel hidePanel() {
        setVisible(false);
        return this;
    }

    public BoardMatrix boardMatrix() {
        return boardMatrix;
    }

    public JButton finishButton() {
        return finishButton;
    }
}

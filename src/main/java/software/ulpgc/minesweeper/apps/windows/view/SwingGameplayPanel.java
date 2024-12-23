package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.control.BoardPresenter;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingGameplayPanel extends JPanel implements BoardDisplay {
    private final Chronometer timer;
    private final BoardDisplay boardDisplay;
    private BoardPresenter presenter;
    private JButton finalizeButton;

    public SwingGameplayPanel() {
        this.timer = new Chronometer();
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, createToolbar());
        this.add(BorderLayout.CENTER, (Component) (this.boardDisplay = createBoardDisplay()));
    }

    private BoardDisplay createBoardDisplay() {
        BoardDisplay swingBoardDisplay = new SwingBoardDisplay();
        this.presenter = new BoardPresenter(swingBoardDisplay);
        return swingBoardDisplay;
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(timer);
        panel.add(creteFinalizeButton());
        return panel;
    }

    private JButton creteFinalizeButton() {
        this.finalizeButton = new JButton("Finalize");
        finalizeButton.addActionListener(e -> hidePanel());
        return finalizeButton;
    }

    private void hidePanel() {
        this.setVisible(false);

    }

    @Override
    public void paint(PaintOrder paintOrder) {

    }

    @Override
    public void on(Click click) {

    }

    public BoardPresenter presenter() {
        return presenter;
    }

    public BoardDisplay boardDisplay() {
        return boardDisplay;
    }

    public JButton finalizeButton() {
        return finalizeButton;
    }
}

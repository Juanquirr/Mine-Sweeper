package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.Builder;

public class PaintOrderBuilder implements Builder<BoardDisplay.PaintOrder> {
    private Cell.Position position;
    private Color color;
    private Integer number;

    public PaintOrderBuilder setPosition(Cell.Position position) {
        this.position = position;
        return this;
    }

    public PaintOrderBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public PaintOrderBuilder setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public BoardDisplay.PaintOrder build() {
        return new BoardDisplay.PaintOrder(position, color, number);
    }
}

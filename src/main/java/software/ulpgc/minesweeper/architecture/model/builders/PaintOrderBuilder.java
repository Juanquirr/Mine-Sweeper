package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

public class PaintOrderBuilder implements Builder<BoardDisplay.PaintOrder> {
    private Cell.Position position;
    private Color color;
    private Integer number;

    private PaintOrderBuilder() {
    }

    public static PaintOrderBuilder create() {
        return new PaintOrderBuilder();
    }

    public PaintOrderBuilder withPosition(Cell.Position position) {
        this.position = position;
        return this;
    }

    public PaintOrderBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public PaintOrderBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public BoardDisplay.PaintOrder build() {
        return new BoardDisplay.PaintOrder(position, color, number);
    }
}

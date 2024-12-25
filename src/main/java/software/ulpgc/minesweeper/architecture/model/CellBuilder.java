package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.view.Builder;

public class CellBuilder implements Builder<Cell> {

    private Cell.Position position;
    private Cell.CellState cellState;

    public CellBuilder position(Cell.Position position) {
        this.position = position;
        return this;
    }

    public CellBuilder gameState(Cell.CellState cellState) {
        this.cellState = cellState;
        return this;
    }

    @Override
    public Cell build() {
        return new Cell() {

            @Override
            public Position position() {
                return position;
            }

            @Override
            public CellState cellState() {
                return cellState;
            }

            @Override
            public Cell open() {
                return null;
            }
        };
    }
}

package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.architecture.model.Cell;

public class CellBuilder implements Builder<Cell> {

    private Cell.Position position;
    private Cell.CellState cellState;

    private CellBuilder() {
    }

    public static CellBuilder create() {
        return new CellBuilder();
    }

    public CellBuilder withPosition(Cell.Position position) {
        this.position = position;
        return this;
    }

    public CellBuilder withCellState(Cell.CellState cellState) {
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
                return CellBuilder.create()
                        .withPosition(position)
                        .withCellState(Cell.CellState.OPENED)
                        .build();
            }
        };
    }
}

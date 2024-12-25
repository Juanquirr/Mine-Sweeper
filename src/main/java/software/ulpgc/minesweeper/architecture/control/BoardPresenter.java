package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.*;
import software.ulpgc.minesweeper.architecture.view.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardPresenter {
    private final BoardDisplay boardDisplay;
    private final BoardExplorer boardExplorer;
    private final AtomicInteger counter = new AtomicInteger(0);
    private Game game;


    public BoardPresenter(BoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
        this.boardExplorer = new BoardExplorer();
        this.boardDisplay.on(click());
    }

    private PaintOrderBuilder paintOrderBuilder() { return BuilderFactory.getBuilder(BoardDisplay.PaintOrder.class); }

    public void show(Game game) {
        this.game = game;
        boardDisplay.clear();
        boardDisplay.adjustDimensionTo(this.game.board().level().size());
        boardDisplay.paint(
                IntStream.range(0, this.game.board().level().width() * this.game.board().level().height())
                        .mapToObj(i ->   paintOrderBuilder().setPosition(
                                new Cell.Position(
                                        (i % this.game.board().level().width()) * BoardDisplay.CELL_SIZE,
                                        (i / this.game.board().level().width()) * BoardDisplay.CELL_SIZE
                                )).setColor(Color.UnopenedCell).build()
                        ).toArray(BoardDisplay.PaintOrder[]::new)
        );
    }

    private BoardDisplay.Click click() {
        return (xOffset, yOffset) -> {
            Cell.Position position = new Cell.Position(pixelToInteger(xOffset), pixelToInteger(yOffset));
            game.board().initializeMinesExcluding(position);
            boardDisplay.paint(getPaintOrderArrayFrom(position));
        };
    }

    private BoardDisplay.PaintOrder[] getPaintOrderArrayFrom(Cell.Position position) {
        boardExplorer.exploreFrom(this.game.board(), position);
        return !game.board().hasMineIn(position) ?
                Stream.of(safeCellStreamGiven(), edgeCellStreamGiven()).flatMap(s -> s)
                        .toArray(BoardDisplay.PaintOrder[]::new) :
                mineCellStreamGiven().toArray(BoardDisplay.PaintOrder[]::new);

    }

    private Stream<BoardDisplay.PaintOrder> mineCellStreamGiven() {
        return game.board().mines().stream()
                .map(p -> paintOrderBuilder().setPosition(integerToPixelPosition(p)).setColor(Color.MineCell).build());
    }

    private Stream<BoardDisplay.PaintOrder> edgeCellStreamGiven() {
        return boardExplorer.edges().stream()
                .map(p -> paintOrderBuilder().setPosition(integerToPixelPosition(p)).setColor(Color.EdgeCell).setNumber(boardExplorer.countNearMines(game.board(), p)).build());
    }

    private Stream<BoardDisplay.PaintOrder> safeCellStreamGiven() {
        return boardExplorer.safeCells().stream()
                .map(p -> paintOrderBuilder().setPosition(integerToPixelPosition(p)).setColor(Color.SafeCell).build());
    }

    private Cell.Position integerToPixelPosition(Cell.Position p) {
        return new Cell.Position(p.x() * BoardDisplay.CELL_SIZE, p.y() * BoardDisplay.CELL_SIZE);
    }

    private static int pixelToInteger(int offset) {
        return offset / BoardDisplay.CELL_SIZE;
    }
}

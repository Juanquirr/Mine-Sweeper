package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.BoardExplorer;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.util.*;
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

    private BoardDisplay.Click click() {
        return (xOffset, yOffset) -> {
            if (this.game.gameResult() != null) return;
            Cell.Position position = new Cell.Position(pixelToInteger(xOffset), pixelToInteger(yOffset));
            if (this.game.gameState().equals(Game.GameState.Unbegun)) beginGame();
            else this.game.add(new Game.Interaction(position, counter.get()));
            this.game.board().initializeMinesExcluding(position);
            boardDisplay.paint(getPaintOrderArrayFrom(position));
            if (game.board().hasMineIn(position)) {
                Game lostGame = new Game(this.game.board(), this.game.gameState(), Game.GameResult.Lost);
                this.game.interactions().forEach(lostGame::add);
                this.game = lostGame;
            }
        };
    }

    private BoardDisplay.PaintOrder[] getPaintOrderArrayFrom(Cell.Position position) {
        boardExplorer.exploreFrom(this.game.board(), position);
        return !game.board().hasMineIn(position) ? Stream.of(
                boardExplorer.safeCells().stream()
                        .map(
                                p -> createPaintOrderAt(integerToPixelPosition(p), Color.SafeCell, null)
                        ),
                boardExplorer.edges().stream()
                        .map(
                                p -> createPaintOrderAt(integerToPixelPosition(p), Color.EdgeCell, boardExplorer.countNearMines(game.board(), p))
                        )).flatMap(s -> s).toArray(BoardDisplay.PaintOrder[]::new) :

                this.game.board().mines().stream()
                        .map(
                                p -> createPaintOrderAt(integerToPixelPosition(p), Color.MineCell, null)
                        ).toArray(BoardDisplay.PaintOrder[]::new);

    }

    private Cell.Position integerToPixelPosition(Cell.Position p) {
        return new Cell.Position(p.row() * BoardDisplay.CELL_SIZE, p.column() * BoardDisplay.CELL_SIZE);
    }

    private BoardDisplay.PaintOrder createPaintOrderAt(Cell.Position position, Color color, Integer number) {
        return new PaintOrderBuilder().setPosition(position).setColor(color).setNumber(number).build();
    }

    private static int pixelToInteger(int offset) {
        return offset / BoardDisplay.CELL_SIZE;
    }

    private void beginGame() {
        this.game = new Game(this.game.board(), Game.GameState.Begun, null);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                counter.getAndIncrement();
            }
        }, 1000);
    }

    public void show(Game game) {
        this.game = game;
        boardDisplay.clear();
        boardDisplay.adjustDimensionTo(this.game.board().level().size());
        boardDisplay.paint(
                IntStream.range(0, this.game.board().level().width() * this.game.board().level().height())
                        .mapToObj(
                                i ->    createPaintOrderAt(
                                        new Cell.Position(
                                                (i % this.game.board().level().width()) * BoardDisplay.CELL_SIZE,
                                                (i / this.game.board().level().width()) * BoardDisplay.CELL_SIZE
                                        ),
                                        Color.UnopenedCell,
                                        null
                                        )
                                ).toArray(BoardDisplay.PaintOrder[]::new)
        );
    }
}

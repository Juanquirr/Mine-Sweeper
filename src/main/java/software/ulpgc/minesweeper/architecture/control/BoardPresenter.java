package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.BoardExplorer;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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
            Cell.Position position = new Cell.Position(xOffset / BoardDisplay.CELL_SIZE, yOffset / BoardDisplay.CELL_SIZE);
            if (this.game.gameState().equals(Game.GameState.Unbegun)) beginGame();
            else this.game.add(new Game.Interaction(position, counter.get()));
            this.game.board().initializeMinesExcluding(position);
            BoardExplorer e = boardExplorer.exploreFrom(this.game.board(), position);
            BoardDisplay.PaintOrder[] array = e.safeCells().stream()
                    .map(
                            p -> new BoardDisplay.PaintOrder(
                                    Color.GRAY,
                                    p.row() * BoardDisplay.CELL_SIZE,
                                    p.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    null
                            )
                    )
                    .toArray(BoardDisplay.PaintOrder[]::new);
            BoardDisplay.PaintOrder[] array1 = e.edges().stream()
                    .map(
                            p -> new BoardDisplay.PaintOrder(
                                    Color.GRAY,
                                    p.row() * BoardDisplay.CELL_SIZE,
                                    p.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    countNearMines(p)
                            )
                    )
                    .toArray(BoardDisplay.PaintOrder[]::new);
            boardDisplay.paint(
                    array.length != 0 ? array :
                            new BoardDisplay.PaintOrder[]{
                            new BoardDisplay.PaintOrder(
                                    Color.RED,
                                    position.row() * BoardDisplay.CELL_SIZE,
                                    position.column() * BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    BoardDisplay.CELL_SIZE,
                                    null
                            )}
            );
            boardDisplay.paint(
                    array1
            );
            if (game.board().hasMineIn(position)) {
                BoardDisplay.PaintOrder[] mines = this.game.board().mines().stream()
                        .map(
                                mine -> new BoardDisplay.PaintOrder(
                                        Color.RED,
                                        mine.row() * BoardDisplay.CELL_SIZE,
                                        mine.column() * BoardDisplay.CELL_SIZE,
                                        BoardDisplay.CELL_SIZE,
                                        BoardDisplay.CELL_SIZE,
                                        null
                                )
                        ).toArray(BoardDisplay.PaintOrder[]::new);
                Game lostGame = new Game(this.game.board(), this.game.gameState(), Game.GameResult.Lost);
                this.game.interactions().forEach(lostGame::add);
                this.game = lostGame;
                boardDisplay.paint(mines);
            }
        };
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

    private Integer countNearMines(Cell.Position p) {
        return Math.toIntExact(this.game.board().cellNeighborsOf(p).stream()
                .filter(n -> this.game.board().hasMineIn(n))
                .count());
    }

    public void show(Game game) {
        this.game = game;
        boardDisplay.clear();
        boardDisplay.adjustDimensionTo(this.game.board().level().size());
        boardDisplay.paint(
                IntStream.range(0, this.game.board().level().width() * this.game.board().level().height())
                        .mapToObj(
                                i -> new BoardDisplay.PaintOrder(
                                        Color.LIGHT_GRAY,
                                        BoardDisplay.CELL_SIZE * (i % this.game.board().level().width()),
                                        BoardDisplay.CELL_SIZE * (i / this.game.board().level().width()),
                                        BoardDisplay.CELL_SIZE,
                                        BoardDisplay.CELL_SIZE,
                                        null
                                )
                        )
                        .toArray(BoardDisplay.PaintOrder[]::new)
        );
    }
}

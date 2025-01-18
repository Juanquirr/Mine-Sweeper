package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.builders.PaintOrderBuilder;
import software.ulpgc.minesweeper.architecture.model.*;
import software.ulpgc.minesweeper.architecture.view.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static software.ulpgc.minesweeper.architecture.model.Button.LEFT_BUTTON;
import static software.ulpgc.minesweeper.architecture.model.Button.RIGHT_BUTTON;

public class GamePresenter {
    private Game game;
    private final GameDisplay gameDisplay;
    private final BoardExplorer boardExplorer;
    private final AtomicInteger counter = new AtomicInteger(0);


    public GamePresenter(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
        this.boardExplorer = new BoardExplorer();
        this.gameDisplay.boardDisplay().on(click());
    }

    public void show(Game game) {
        this.game = game;
        gameDisplay.boardDisplay().adjustDimensionTo(this.game.board().level().size());
        gameDisplay.boardDisplay().paint(
                IntStream.range(0, this.game.board().level().width() * this.game.board().level().height())
                        .mapToObj(i ->   PaintOrderBuilder.create().withPosition(
                                new Cell.Position(
                                        (i % game.board().level().width()) * BoardDisplay.CELL_SIZE,
                                        (i / game.board().level().width()) * BoardDisplay.CELL_SIZE
                                )).withColor(Color.UnopenedCell).build()
                        ).toArray(BoardDisplay.PaintOrder[]::new)
        );
    }

    private BoardDisplay.Click click() {
        return (xOffset, yOffset, button) -> {
            if (endedGame()) return;
            Cell.Position position = new Cell.Position(pixelToInteger(xOffset), pixelToInteger(yOffset));
            game = game.add(new Game.Interaction(position, getAction(button), gameDisplay.chronometer().currentTime()));
            gameDisplay.boardDisplay().paint(getPaintOrderArrayFrom(position));
            checkGameState();
        };
    }

    private void checkGameState() {
        if (!game.gameState().equals(Game.GameState.BEGUN)) gameDisplay.stopGame();
        switch (game.gameState()) {
            case WON -> gameDisplay.showWinDisplay();
            case LOST -> gameDisplay.showLostDisplay();
        }
    }

    private Game.Action getAction(int button) {
        return switch (button) {
            case LEFT_BUTTON -> Game.Action.OPEN;
            case RIGHT_BUTTON -> Game.Action.FLAG;
            default ->
                    throw new IllegalStateException("Unexpected value: " + button);
        };
    }

    private boolean endedGame() {
        if (game.gameState().equals(Game.GameState.UNBEGUN)) gameDisplay.startGame();
        return game.gameState().equals(Game.GameState.WON) || game.gameState().equals(Game.GameState.LOST);
    }

    private BoardDisplay.PaintOrder[] getFlagPaintOrderArrayFrom(Cell.Position position) {
        return (game.board().cellAt(position).cellState().equals(Cell.CellState.FLAGGED)) ?
                new BoardDisplay.PaintOrder[]{PaintOrderBuilder.create().withPosition(integerToPixelPosition(position)).withColor(Color.UnopenedCell).withFlag(true).build()} :
                new BoardDisplay.PaintOrder[]{PaintOrderBuilder.create().withPosition(integerToPixelPosition(position)).withColor(Color.UnopenedCell).withFlag(false).build()};
    }

    private BoardDisplay.PaintOrder[] getPaintOrderArrayFrom(Cell.Position position) {
        return PaintOrderBuilder.boardPaintOrder(boardExplorer, game.board(), BoardDisplay.CELL_SIZE).toArray(BoardDisplay.PaintOrder[]::new);
    }

    private Cell.Position integerToPixelPosition(Cell.Position p) {
        return new Cell.Position(p.x() * BoardDisplay.CELL_SIZE, p.y() * BoardDisplay.CELL_SIZE);
    }

    private static int pixelToInteger(int offset) {
        return offset / BoardDisplay.CELL_SIZE;
    }
}

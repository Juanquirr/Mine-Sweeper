package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.builders.PaintOrderBuilder;
import software.ulpgc.minesweeper.architecture.model.*;
import software.ulpgc.minesweeper.architecture.view.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        gameDisplay.stopGame();
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
            if (game.gameState().equals(Game.GameState.WON) || game.gameState().equals(Game.GameState.LOST)) return;
            if (game.gameState().equals(Game.GameState.UNBEGUN)) gameDisplay.startGame();
            Cell.Position position = new Cell.Position(pixelToInteger(xOffset), pixelToInteger(yOffset));
            game = game.add(new Game.Interaction(position, gameDisplay.chronometer().currentTime()));
            gameDisplay.boardDisplay().paint(button == 3 ? getFlagPaintOrderArrayFrom(position) : getPaintOrderArrayFrom(position));
            if (!game.gameState().equals(Game.GameState.BEGUN)) gameDisplay.stopGame();
        };
    }

    private BoardDisplay.PaintOrder[] getFlagPaintOrderArrayFrom(Cell.Position position) {
        return new BoardDisplay.PaintOrder[]{PaintOrderBuilder.create().withPosition(integerToPixelPosition(position)).withColor(Color.UnopenedCell).withFlag(true).build()};
    }

    private BoardDisplay.PaintOrder[] getPaintOrderArrayFrom(Cell.Position position) {
        boardExplorer.exploreFrom(this.game.board(), position);
        return !game.board().hasMineIn(position) ?
                Stream.of(
                        PaintOrderBuilder.safeCell(boardExplorer, BoardDisplay.CELL_SIZE),
                                PaintOrderBuilder.edgeCell(boardExplorer, game, BoardDisplay.CELL_SIZE)
                        ).flatMap(s -> s).toArray(BoardDisplay.PaintOrder[]::new) :
                PaintOrderBuilder.mineCell(game.board().mines(), BoardDisplay.CELL_SIZE).toArray(BoardDisplay.PaintOrder[]::new);
    }

    private Cell.Position integerToPixelPosition(Cell.Position p) {
        return new Cell.Position(p.x() * BoardDisplay.CELL_SIZE, p.y() * BoardDisplay.CELL_SIZE);
    }

    private static int pixelToInteger(int offset) {
        return offset / BoardDisplay.CELL_SIZE;
    }
}

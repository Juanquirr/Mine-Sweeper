package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.builders.PaintOrderBuilder;
import software.ulpgc.minesweeper.architecture.model.*;
import software.ulpgc.minesweeper.architecture.model.builders.PositionAdapter;
import software.ulpgc.minesweeper.architecture.view.*;

import static software.ulpgc.minesweeper.architecture.model.Button.LEFT_BUTTON;
import static software.ulpgc.minesweeper.architecture.model.Button.RIGHT_BUTTON;
import static software.ulpgc.minesweeper.architecture.view.BoardDisplay.CELL_SIZE;

public class GamePresenter {
    private Game game;
    private final GameDisplay gameDisplay;


    public GamePresenter(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
        this.gameDisplay.boardDisplay().on(click());
    }

    public void show(Game game) {
        this.game = game;
        gameDisplay.counterDisplay().show(BoardExplorer.countRemainMines(game.board()));
        gameDisplay.resetGame();
        gameDisplay.boardDisplay().adjustDimensionTo(this.game.board().level().size());
        gameDisplay.boardDisplay().paint(PaintOrderBuilder.boardPaintOrder(game.board(), CELL_SIZE).toArray(BoardDisplay.PaintOrder[]::new));
    }

    private BoardDisplay.Click click() {
        return (xOffset, yOffset, button) -> {
            if (endedGame()) return;
            Cell.Position position = new Cell.Position(PositionAdapter.adaptToInteger(xOffset, CELL_SIZE), PositionAdapter.adaptToInteger(yOffset, CELL_SIZE));
            game = game.add(new Game.Interaction(position, getAction(button), gameDisplay.chronometer().currentTime()));
            gameDisplay.boardDisplay().paint(getPaintOrderArrayFrom(position));
            gameDisplay.counterDisplay().show(BoardExplorer.countRemainMines(game.board()));
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
            default -> null;
        };
    }

    private boolean endedGame() {
        if (game.gameState().equals(Game.GameState.UNBEGUN)) gameDisplay.startGame();
        return game.gameState().equals(Game.GameState.WON) || game.gameState().equals(Game.GameState.LOST);
    }

    private BoardDisplay.PaintOrder[] getPaintOrderArrayFrom(Cell.Position position) {
        return PaintOrderBuilder.boardPaintOrder(
                game.board(), CELL_SIZE).toArray(BoardDisplay.PaintOrder[]::new
        );
    }
}

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
    private GameReplayer gameReplayer;
    private final GameDisplay gameDisplay;
    private final Object lock = new Object();
    private Thread replayThread;
    private volatile boolean running = false;

    public GamePresenter(GameDisplay gameDisplay, GameReplayer gameReplayer) {
        this.gameDisplay = gameDisplay;
        this.gameReplayer = gameReplayer;
        this.gameDisplay.boardDisplay().on(click());
    }

    public void showGameReplay() {
        synchronized (lock) {
            if (gameReplayer.replayState().equals(GameReplayer.ReplayState.STARTED)) return;
            gameReplayer = gameReplayer.defineGame(game);
            running = true;
        }

        replayThread = new Thread(() -> {
            for (int i = 0; i < game.interactions().size(); i++) {
                synchronized (lock) {
                    if (!running) break;
                    gameReplayer = gameReplayer.execute();
                }

                gameDisplay.boardDisplay().paint(PaintOrderBuilder.boardPaintOrder(gameReplayer.game().board(), CELL_SIZE).toArray(BoardDisplay.PaintOrder[]::new));
            }

            synchronized (lock) {
                gameReplayer = gameReplayer.reset();
            }
        });
        replayThread.start();
    }

    public void stopReplay() {
        synchronized (lock) {
            running = false;
        }

        if (replayThread != null) {
            try {
                replayThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        synchronized (lock) {
            gameReplayer = new GameReplayer.Builder().build();
        }
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
            if (getAction(button) == null) return;
            Cell.Position position = new Cell.Position(PositionAdapter.adaptToInteger(xOffset, CELL_SIZE), PositionAdapter.adaptToInteger(yOffset, CELL_SIZE));
            game = game.add(new Game.Interaction(position, getAction(button), gameDisplay.chronometer().currentTime()));
            gameDisplay.boardDisplay().paint(getPaintOrderArrayFrom(position));
            gameDisplay.counterDisplay().show(BoardExplorer.countRemainMines(game.board()));
            checkGameState(game);
        };
    }

    private void checkGameState(Game game) {
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

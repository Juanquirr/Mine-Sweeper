package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;
import software.ulpgc.minesweeper.architecture.model.builders.PaintOrderBuilder;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;

import static software.ulpgc.minesweeper.architecture.view.BoardDisplay.CELL_SIZE;

public class GameReplayer {
    private final Game game;
    private Game replayedGame;
    private final GameDisplay gameDisplay;

    public GameReplayer(Game game, GameDisplay gameDisplay) {
        this.game = game;
        this.replayedGame = GameBuilder.create().build();
        this.gameDisplay = gameDisplay;
    }

    public void execute() {
        new Thread(() -> {
            for (Game.Interaction interaction : game.interactions()) {
                try {
                    Thread.sleep(interaction.seconds());
                    replayedGame = replayedGame.add(interaction);
                    gameDisplay.boardDisplay().paint(PaintOrderBuilder.boardPaintOrder(
                            replayedGame.board(), CELL_SIZE).toArray(BoardDisplay.PaintOrder[]::new
                    ));
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    public void startReplay() {

    }

    public void stopReplay() {

    }
}

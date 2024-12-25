package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.model.GameBuilder;
import software.ulpgc.minesweeper.architecture.view.BuilderFactory;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

public class StartCommand implements Command {
    private final MainMenuPanel mainMenuPanel;
    private final BoardPresenter presenter;

    public StartCommand(MainMenuPanel mainMenuPanel, GameDisplay gameDisplay, BoardPresenter presenter) {
        this.mainMenuPanel = mainMenuPanel;
        this.presenter = presenter;
    }

    private GameBuilder gameBuilder() { return (GameBuilder) BuilderFactory.getBuilder(Game.class); }
    private BoardBuilder boardBuilder() { return (BoardBuilder) BuilderFactory.getBuilder(Board.class); }

    @Override
    public void execute() {
        presenter.show(
                gameBuilder().gameState(Game.GameState.UNBEGUN).board(
                        boardBuilder().level(mainMenuPanel.difficultyDialog().get()).build()
                ).build()
        );
    }
}

package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.builders.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

public class StartCommand implements Command {
    private final MainMenuPanel mainMenuPanel;
    private final GamePresenter presenter;

    public StartCommand(MainMenuPanel mainMenuPanel, GameDisplay gameDisplay, GamePresenter presenter) {
        this.mainMenuPanel = mainMenuPanel;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(
                GameBuilder.create().withGameState(Game.GameState.UNBEGUN).withBoard(
                        BoardBuilder.create().withLevel(mainMenuPanel.difficultyDialog().get()).build()
                ).build()
        );
    }
}

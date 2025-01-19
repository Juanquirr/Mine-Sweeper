package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.builders.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MenuDisplay;

public class StartCommand implements Command {
    private final MenuDisplay menuDisplay;
    private final GamePresenter presenter;

    public StartCommand(MenuDisplay menuDisplay, GameDisplay gameDisplay, GamePresenter presenter) {
        this.menuDisplay = menuDisplay;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(
                GameBuilder.create().withGameState(Game.GameState.UNBEGUN).withBoard(
                        BoardBuilder.create().withLevel(menuDisplay.difficultyDialog().get()).build()
                ).build()
        );
    }
}

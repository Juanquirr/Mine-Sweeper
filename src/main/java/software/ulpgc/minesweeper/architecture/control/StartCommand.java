package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.BoardBuilder;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

public class StartCommand implements Command {
    private final MainMenuPanel mainMenuPanel;
    private final BoardPresenter presenter;

    public StartCommand(MainMenuPanel mainMenuPanel, GameDisplay gameDisplay, BoardPresenter presenter) {
        this.mainMenuPanel = mainMenuPanel;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(
                new BoardBuilder()
                        .level(mainMenuPanel.difficultyDialog().get())
                        .build()
        );
    }
}

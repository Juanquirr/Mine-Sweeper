package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

public class FinishCommand implements Command {
    private final MainMenuPanel mainMenuPanel;
    private final GameDisplay gameplayDisplay;

    public FinishCommand(MainMenuPanel mainMenuPanel, GameDisplay gameplayDisplay) {
        this.mainMenuPanel = mainMenuPanel;
        this.gameplayDisplay = gameplayDisplay;
    }

    @Override
    public void execute() {
        gameplayDisplay.stopGame();
        mainMenuPanel.showPanel();
    }
}

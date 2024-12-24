package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.view.GamePlayPanel;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

public class FinishCommand implements Command {
    private final MainMenuPanel mainMenuPanel;
    private final GamePlayPanel gameplayPanel;

    public FinishCommand(MainMenuPanel mainMenuPanel, GamePlayPanel gameplayPanel) {
        this.mainMenuPanel = mainMenuPanel;
        this.gameplayPanel = gameplayPanel;
    }

    @Override
    public void execute() {
        gameplayPanel.hidePanel();
        mainMenuPanel.showPanel();
    }
}

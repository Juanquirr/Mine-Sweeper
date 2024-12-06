package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.view.GameplayPanel;
import software.ulpgc.minesweeper.architecture.view.LoadingPanel;

public class FinishCommand implements Command {
    private final LoadingPanel loadingPanel;
    private final GameplayPanel gameplayPanel;

    public FinishCommand(LoadingPanel loadingPanel, GameplayPanel gameplayPanel) {
        this.loadingPanel = loadingPanel;
        this.gameplayPanel = gameplayPanel;
    }

    @Override
    public void execute() {
        gameplayPanel.hidePanel();
        loadingPanel.showPanel();
    }
}

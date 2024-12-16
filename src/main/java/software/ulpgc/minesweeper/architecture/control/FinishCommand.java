package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.LoadingPanel;

public class FinishCommand implements Command {
    private final LoadingPanel loadingPanel;
    private final BoardDisplay gameplayPanel;

    public FinishCommand(LoadingPanel loadingPanel, BoardDisplay gameplayPanel) {
        this.loadingPanel = loadingPanel;
        this.gameplayPanel = gameplayPanel;
    }

    @Override
    public void execute() {
        gameplayPanel.hidePanel();
        loadingPanel.showPanel();
    }
}

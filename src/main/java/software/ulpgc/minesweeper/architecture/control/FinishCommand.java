package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.apps.windows.view.SwingGameplayPanel;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.LoadingPanel;
import software.ulpgc.minesweeper.apps.windows.view.SwingBoardDisplay;

public class FinishCommand implements Command {
    private final LoadingPanel loadingPanel;
    private final BoardDisplay boardDisplay;

    public FinishCommand(LoadingPanel loadingPanel, BoardDisplay gameplayPanel) {
        this.loadingPanel = loadingPanel;
        this.boardDisplay = gameplayPanel;
    }

    @Override
    public void execute() {
        ((SwingGameplayPanel) boardDisplay).setVisible(false);
        loadingPanel.showPanel();
    }
}

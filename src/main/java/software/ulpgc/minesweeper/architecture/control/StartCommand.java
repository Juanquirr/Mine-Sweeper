package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.BoardMatrix;
import software.ulpgc.minesweeper.architecture.view.GameplayPanel;
import software.ulpgc.minesweeper.architecture.view.LoadingPanel;

public class StartCommand implements Command {
    private final LoadingPanel loadingPanel;
    private final GameplayPanel gameplayPanel;

    public StartCommand(LoadingPanel loadingPanel, GameplayPanel gameplayPanel) {
        this.loadingPanel = loadingPanel;
        this.gameplayPanel = gameplayPanel;
    }

    @Override
    public void execute() {
        loadingPanel.hidePanel();
        gameplayPanel.setBoard(BoardMatrix.ofSize(loadingPanel.difficultyDialog().get()));
        gameplayPanel.showPanel();
    }
}

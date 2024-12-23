package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.BoardBuilder;
import software.ulpgc.minesweeper.architecture.view.LoadingPanel;

public class StartCommand implements Command {
    private final LoadingPanel loadingPanel;
    private final BoardPresenter presenter;

    public StartCommand(LoadingPanel loadingPanel, BoardPresenter presenter) {
        this.loadingPanel = loadingPanel;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        loadingPanel.hidePanel();
        presenter.show(new BoardBuilder().level(loadingPanel.difficultyDialog().get()).build());
    }
}

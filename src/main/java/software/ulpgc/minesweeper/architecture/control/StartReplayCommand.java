package software.ulpgc.minesweeper.architecture.control;

public class StartReplayCommand implements Command {
    private final GamePresenter presenter;

    public StartReplayCommand(GamePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.showGameReplay();
    }
}

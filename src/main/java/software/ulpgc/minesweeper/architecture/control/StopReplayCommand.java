package software.ulpgc.minesweeper.architecture.control;

public class StopReplayCommand implements Command {
    private final GamePresenter presenter;

    public StopReplayCommand(GamePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.stopReplay();
    }
}

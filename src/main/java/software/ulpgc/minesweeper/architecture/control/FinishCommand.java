package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MenuDisplay;

public class FinishCommand implements Command {
    private final MenuDisplay menuDisplay;
    private final GameDisplay gameplayDisplay;

    public FinishCommand(MenuDisplay menuDisplay, GameDisplay gameplayDisplay) {
        this.menuDisplay = menuDisplay;
        this.gameplayDisplay = gameplayDisplay;
    }

    @Override
    public void execute() {
        gameplayDisplay.stopGame();
        menuDisplay.showPanel();
    }
}

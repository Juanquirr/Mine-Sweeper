package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.apps.windows.view.MainFrame;
import software.ulpgc.minesweeper.architecture.control.GamePresenter;
import software.ulpgc.minesweeper.architecture.control.FinishCommand;
import software.ulpgc.minesweeper.architecture.control.StartCommand;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        GamePresenter presenter = new GamePresenter(mainFrame.gameplayPanel());
        mainFrame
                .addCommand("start_game", new StartCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel(), presenter))
                .addCommand("finish", new FinishCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel()))
                .setVisible(true);
    }
}

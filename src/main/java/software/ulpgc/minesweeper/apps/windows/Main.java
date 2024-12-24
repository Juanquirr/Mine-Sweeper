package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.apps.windows.view.MainFrame;
import software.ulpgc.minesweeper.architecture.control.BoardPresenter;
import software.ulpgc.minesweeper.architecture.control.FinishCommand;
import software.ulpgc.minesweeper.architecture.control.StartCommand;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        BoardPresenter presenter = new BoardPresenter(mainFrame.gameplayPanel().boardDisplay());
        mainFrame
                .addCommand("start_game", new StartCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel(), presenter))
                .addCommand("finish", new FinishCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel()))
                .setVisible(true);
    }
}

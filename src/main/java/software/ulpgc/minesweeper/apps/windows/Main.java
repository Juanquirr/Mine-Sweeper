package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.apps.windows.view.MainFrame;
import software.ulpgc.minesweeper.architecture.control.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        GamePresenter presenter = new GamePresenter(mainFrame.gameplayPanel(), new GameReplayer.Builder().build());
        mainFrame
                .addCommand("start_game", new StartCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel(), presenter))
                .addCommand("finish", new FinishCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel()))
                .addCommand("start_replay", new StartReplayCommand(presenter))
                .addCommand("stop_replay", new StopReplayCommand(presenter))
                .setVisible(true);
    }
}

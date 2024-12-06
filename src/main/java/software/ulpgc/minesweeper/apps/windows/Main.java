package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.architecture.control.Command;
import software.ulpgc.minesweeper.architecture.control.FinishCommand;
import software.ulpgc.minesweeper.architecture.control.StartCommand;
import software.ulpgc.minesweeper.architecture.model.BoardMatrix;
import software.ulpgc.minesweeper.architecture.model.BoardRow;
import software.ulpgc.minesweeper.architecture.model.Difficulty;
import software.ulpgc.minesweeper.architecture.view.MainFrame;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        Map<String, Command> commands = new HashMap<>();
        commands.put("start_game", new StartCommand(mainFrame.loadingPanel(), mainFrame.gameplayPanel()));
        commands.put("finish", new FinishCommand(mainFrame.loadingPanel(), mainFrame.gameplayPanel()));
        mainFrame.defineCommands(commands);
        mainFrame.setVisible(true);
    }
}

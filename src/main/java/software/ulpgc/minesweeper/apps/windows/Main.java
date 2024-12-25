package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.apps.windows.view.MainFrame;
import software.ulpgc.minesweeper.architecture.control.BoardPresenter;
import software.ulpgc.minesweeper.architecture.control.FinishCommand;
import software.ulpgc.minesweeper.architecture.control.PaintOrderBuilder;
import software.ulpgc.minesweeper.architecture.control.StartCommand;
import software.ulpgc.minesweeper.architecture.model.*;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;
import software.ulpgc.minesweeper.architecture.view.BuilderFactory;

public class Main {
    public static void main(String[] args) {
        BuilderFactory.registerBuilder(Board.class, BoardBuilder::new);
        BuilderFactory.registerBuilder(Cell.class, CellBuilder::new);
        BuilderFactory.registerBuilder(Game.class, GameBuilder::new);
        BuilderFactory.registerBuilder(BoardDisplay.PaintOrder.class, PaintOrderBuilder::new);
        MainFrame mainFrame = MainFrame.create();
        BoardPresenter presenter = new BoardPresenter(mainFrame.gameplayPanel().boardDisplay());
        mainFrame
                .addCommand("start_game", new StartCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel(), presenter))
                .addCommand("finish", new FinishCommand(mainFrame.mainMenuPanel(), mainFrame.gameplayPanel()))
                .setVisible(true);
    }
}

package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.control.Command;
import software.ulpgc.minesweeper.architecture.model.Level;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingMainMenuPanel mainMenuPanel;
    private final SwingGameplayDisplay gameplayPanel;
    private final Map<String, Command> commands;

    private MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        setTitle("Minesweeper");
        setResizable(false);
        CardLayout layout = new CardLayout();
        getContentPane().setLayout(layout);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(this.gameplayPanel = createGameplayPanel(), "GAME");
        add(this.mainMenuPanel = createMainMenuPanel(), "LOADING");

        layout.show(getContentPane(), "GAME");
        layout.show(getContentPane(), "LOADING");
        setSize(400, 400);
    }

    public static MainFrame create() {
        return new MainFrame();
    }

    public MainFrame addCommand(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    private SwingGameplayDisplay createGameplayPanel() {
        SwingGameplayDisplay swingGameplayPanel = new SwingGameplayDisplay();
        swingGameplayPanel.finalizeButton().addActionListener(x -> {
            commands.get("finish").execute();
            swingGameplayPanel.boardDisplay().adjustDimensionTo(new Level.Size(1, 1));
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "LOADING");
            setSize(400, 400);
            setLocationRelativeTo(null);
        });
        return swingGameplayPanel;
    }


    private SwingMainMenuPanel createMainMenuPanel() {
        SwingMainMenuPanel loadingPanel = new SwingMainMenuPanel();
        loadingPanel.startButton().addActionListener(x -> {
            commands.get("start_game").execute();
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "GAME");
            pack();
            setLocationRelativeTo(null);
        });
        return loadingPanel;
    }

    public MainMenuPanel mainMenuPanel() {
        return mainMenuPanel;
    }

    public GameDisplay gameplayPanel() {
        return gameplayPanel;
    }

    public MainFrame defineCommands(Map<String, Command>commands) {
        for (String name : commands.keySet())
            this.commands.put(name, commands.get(name));
        return this;
    }
}
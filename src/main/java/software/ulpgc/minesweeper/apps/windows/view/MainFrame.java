package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingLoadingPanel loadingPanel;
    private final SwingGameplayPanel gameplayPanel;
    private final Map<String, Command> commands;

    private MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        setTitle("Minesweeper");
        setResizable(false);
        CardLayout layout = new CardLayout();
        getContentPane().setLayout(layout);
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(this.gameplayPanel = createGameplayPanel(), "GAME");
        add(this.loadingPanel = createLoadingPanel(), "LOADING");
        layout.show(getContentPane(), "GAME");
        layout.show(getContentPane(), "LOADING");
    }

    public static MainFrame create() {
        return new MainFrame();
    }

    public MainFrame addCommand(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    private SwingGameplayPanel createGameplayPanel() {
        SwingGameplayPanel swingGameplayPanel = new SwingGameplayPanel();
        swingGameplayPanel.finalizeButton().addActionListener(e -> commands.get("finish").execute());
        return swingGameplayPanel;
    }

    private SwingLoadingPanel createLoadingPanel() {
        SwingLoadingPanel loadingPanel = new SwingLoadingPanel();
        loadingPanel.startButton().addActionListener(e -> commands.get("start_game").execute());
        return loadingPanel;
    }

    public SwingLoadingPanel loadingPanel() {
        return loadingPanel;
    }

    public SwingGameplayPanel gameplayPanel() {
        return gameplayPanel;
    }

    public MainFrame defineCommands(Map<String, Command>commands) {
        for (String name : commands.keySet())
            this.commands.put(name, commands.get(name));
        return this;
    }
}
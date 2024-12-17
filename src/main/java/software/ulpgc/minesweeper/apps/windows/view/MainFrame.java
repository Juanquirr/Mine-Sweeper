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

    public MainFrame() throws HeadlessException {
        setTitle("Minesweeper");
        setResizable(false);
        CardLayout layout = new CardLayout();
        getContentPane().setLayout(layout);
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(this.gameplayPanel = createGameplayPanel(), "GAME");
        add(this.loadingPanel = createLoadingPanel(), "LOADING");
        layout.show(getContentPane(), "GAME");
        layout.show(getContentPane(), "LOADING");
        this.commands = new HashMap<>();
    }

    private SwingGameplayPanel createGameplayPanel() {
        SwingGameplayPanel gameplayPanel = new SwingGameplayPanel();
        gameplayPanel.finishButton().addActionListener(_ -> commands.get("finish").execute());
        return gameplayPanel;
    }

    private SwingLoadingPanel createLoadingPanel() {
        SwingLoadingPanel loadingPanel = new SwingLoadingPanel();
        loadingPanel.startButton().addActionListener(_ -> commands.get("start_game").execute());
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
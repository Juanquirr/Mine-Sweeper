package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.CustomFont;
import software.ulpgc.minesweeper.architecture.control.Command;
import software.ulpgc.minesweeper.architecture.model.Level;
import software.ulpgc.minesweeper.architecture.view.GameDisplay;
import software.ulpgc.minesweeper.architecture.view.MenuDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingMenuDisplay mainMenuPanel;
    private final SwingGameDisplay gameplayPanel;
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
        pack();
    }

    public static MainFrame create() {
        return new MainFrame();
    }

    public MainFrame addCommand(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    private SwingGameDisplay createGameplayPanel() {
        SwingGameDisplay swingGameplayPanel = new SwingGameDisplay();
        swingGameplayPanel.finalizeButton().addActionListener(x -> {
            commands.get("finish").execute();
            swingGameplayPanel.boardDisplay().adjustDimensionTo(new Level.Size(1, 1));
            swingGameplayPanel.setVisible(false);
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "LOADING");
            pack();
            setLocationRelativeTo(null);
        });
        swingGameplayPanel.restartButton().addActionListener(e -> commands.get("start_game").execute());
        return swingGameplayPanel;
    }


    private SwingMenuDisplay createMainMenuPanel() {
        SwingMenuDisplay loadingPanel = new SwingMenuDisplay();
        loadingPanel.startButton().addActionListener(x -> {
            commands.get("start_game").execute();
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "GAME");
            pack();
            setLocationRelativeTo(null);
        });
        loadingPanel.startButton().setForeground(Color.white);
        loadingPanel.startButton().setFont(new CustomFont().loadFont().deriveFont(25f));
        loadingPanel.startButton().setBackground(new Color(200, 0, 0));
        return loadingPanel;
    }

    public MenuDisplay mainMenuPanel() {
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
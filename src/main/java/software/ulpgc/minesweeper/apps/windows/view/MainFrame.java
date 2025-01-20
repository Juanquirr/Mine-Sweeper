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
    private final JPanel centerPanel;
    private final JPanel toolbar;

    private MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        add(BorderLayout.NORTH, this.toolbar = createToolbar());
        add(BorderLayout.CENTER, this.centerPanel = centerPanel(this.gameplayPanel = createGameplayPanel(), this.mainMenuPanel = createMainMenuPanel()));
        toolbar.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel centerPanel(GameDisplay gameDisplay, MenuDisplay menuDisplay) {
        JPanel panel = new JPanel();
        CardLayout layout = new CardLayout();
        panel.setLayout(layout);
        panel.add((Component) gameDisplay, "GAME");
        panel.add((Component) menuDisplay, "LOADING");
        layout.show(panel, "GAME");
        layout.show(panel, "LOADING");
        return panel;
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Back");
        panel.add(button);
        button.addActionListener(x -> {
            commands.get("finish").execute();
            gameplayPanel.boardDisplay().adjustDimensionTo(new Level.Size(1, 1));
            gameplayPanel.setVisible(false);
            ((CardLayout) centerPanel.getLayout()).show(centerPanel, "LOADING");
            toolbar.setVisible(false);
            pack();
            setLocationRelativeTo(null);
        });
        return panel;
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
        swingGameplayPanel.restartButton().addActionListener(e -> commands.get("start_game").execute());
        ((SwingReplayController) swingGameplayPanel.replayController()).startButton().addActionListener(e -> commands.get("start_replay").execute());
        ((SwingReplayController) swingGameplayPanel.replayController()).stopButton().addActionListener(e -> commands.get("stop_replay").execute());
        return swingGameplayPanel;
    }

    private SwingMenuDisplay createMainMenuPanel() {
        SwingMenuDisplay loadingPanel = new SwingMenuDisplay();
        loadingPanel.startButton().addActionListener(x -> {
            commands.get("start_game").execute();
            ((CardLayout) centerPanel.getLayout()).show(centerPanel, "GAME");
            toolbar.setVisible(true);
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
}
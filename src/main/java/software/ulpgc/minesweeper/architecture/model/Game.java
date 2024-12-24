package software.ulpgc.minesweeper.architecture.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Interaction> interactions;
    public final GameState gameState;
    public final GameResult gameResult;

    public Game() {
        this.interactions = new ArrayList<>();
        this.gameState = GameState.Unbegun;
        this.gameResult = null;
    }

    public GameState gameState() {
        return gameState;
    }

    public GameResult gameResult() {
        return gameResult;
    }

    public List<Interaction> interactions() {
        return new ArrayList<>(interactions);
    }

    public Game add(Interaction interaction) {
        this.interactions.add(interaction);
        return this;
    }

    public record Interaction(Cell.Position position, Instant instant) {}

    public enum GameState {
        Unbegun, Begun
    }

    public enum GameResult {
        Won, Lost
    }
}

package software.ulpgc.minesweeper.architecture.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Interaction> interactions;
    private final Board board;
    private final GameState gameState;
    private final GameResult gameResult;

    public Game(Board board) {
        this.interactions = new ArrayList<>();
        this.board = board;
        this.gameState = GameState.Unbegun;
        this.gameResult = null;
    }

    public Game(Board board, GameState gameState, GameResult gameResult) {
        this.interactions = new ArrayList<>();
        this.board = board;
        this.gameState = gameState;
        this.gameResult = gameState.equals(GameState.Unbegun) ? null : gameResult;
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

    public Board board() {
        return board;
    }

    public record Interaction(Cell.Position position, int seconds) {}

    public enum GameState {
        Unbegun, Begun
    }

    public enum GameResult {
        Won, Lost
    }

}

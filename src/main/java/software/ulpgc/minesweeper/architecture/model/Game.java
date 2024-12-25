package software.ulpgc.minesweeper.architecture.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Interaction> interactions;
    private final Board board;
    private final GameState gameState;

    public Game(Board board) {
        this.interactions = new ArrayList<>();
        this.board = board;
        this.gameState = GameState.UNBEGUN;
    }

    public Game(Board board, GameState gameState) {
        this.interactions = new ArrayList<>();
        this.board = board;
        this.gameState = gameState;
    }

    public GameState gameState() {
        return gameState;
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

    public CellActionResult openCellAt(Cell.Position position) {
        if (!this.gameState.equals(GameState.BEGUN)) return CellActionResult.INVALID;
        if (this.board.cellAt(position).cellState().equals(Cell.CellState.OPENED)) return CellActionResult.INVALID;
        GameState newGameSate;
        if (this.board.hasMineIn(position)) {
            newGameSate = GameState.LOST;
        }
        return CellActionResult.OPENED;
    }

    public record Interaction(Cell.Position position, int seconds) {}

    public enum GameState {
        UNBEGUN, BEGUN, WON, LOST
    }

    public enum CellActionResult {
        OPENED, MINE, MARKED, UNMARKED, INVALID
    }
}

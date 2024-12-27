package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;

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
        if (gameState.equals(GameState.WON) || gameState.equals(GameState.LOST)) return this;
        interactions.add(interaction);
        if (gameState.equals(GameState.UNBEGUN)) board.initializeMinesExcluding(interaction.position());
        return openCellAt(interaction.position());
    }

    public Board board() {
        return board;
    }

    public Game openCellAt(Cell.Position position) {
        if (board.cellAt(position).cellState().equals(Cell.CellState.OPENED)) return this;
        Board newBoard = board.openCellAt(position);
        return GameBuilder.create().withBoard(newBoard).withInteractions(interactions).withGameState(determineSate(position, newBoard)).build();
    }

    private GameState determineSate(Cell.Position position, Board board) {
        return board.hasMineIn(position) ? GameState.LOST :
                (interactions.size() == board.level().width() * board.level().height() - board.level().numberOfMines() ? GameState.WON : GameState.BEGUN);
    }

    public record Interaction(Cell.Position position, long seconds) {}

    public enum GameState {
        UNBEGUN, BEGUN, WON, LOST
    }
}

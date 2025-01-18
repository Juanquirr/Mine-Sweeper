package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.model.builders.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private final List<Interaction> interactions;
    private final Board board;
    private final GameState gameState;

    public Game(Board board, GameState gameState, List<Interaction> interactions, int remainCells) {
        this.interactions = interactions;
        this.board = board;
        this.gameState = gameState;
    }

    public List<Interaction> interactions() {
        return new ArrayList<>(interactions);
    }

    public Game add(Interaction interaction) {
        interactions.add(interaction);
        if (gameState.equals(GameState.UNBEGUN)) board.initializeMinesExcluding(interaction.position());
        return openCellAt(interaction.position(), interaction.action());
    }

    public Game openCellAt(Cell.Position position, Action action) {
        Board newBoard = performActionOn(position, action);
        return GameBuilder.create().withBoard(newBoard).withRemainCells(determineRemainCells(newBoard)).withInteractions(interactions).withGameState(determineSate(position, newBoard)).build();
    }

    private int determineRemainCells(Board board) {
        return (board.cells().size() - board.mines().size()) - (int) board.cells().stream()
                .filter(c -> c.cellState().equals(Cell.CellState.OPENED) && !board.hasMineIn(c.position()))
                .count();
    }

    private List<Cell> openCell(Cell cell) {
        if (isFlagged(cell.position())) return new ArrayList<>();
        if (board.hasMineIn(cell.position())) return revealMines();
        Map<String, List<Cell.Position>> exploredCells = BoardExplorer.exploreFrom(board, cell.position());
        return board.cells().stream()
                .filter(c -> exploredCells.get("safe").contains(c.position()) || exploredCells.get("edge").contains(c.position()))
                .map(Cell::open)
                .toList();
    }

    private boolean isFlagged(Cell.Position position) {
        return board.cellAt(position).cellState().equals(Cell.CellState.FLAGGED);
    }

    private List<Cell> revealMines() {
        return board.mines().stream().map(p -> board.cellAt(p).open()).toList();
    }

    private List<Cell> flagCell(Cell cell) {
        if (isOpened(board, cell.position())) return List.of(cell);
        return List.of(cell.flag());
    }

    private Board performActionOn(Cell.Position position, Game.Action action) {
        if (isOpened(board, position)) return board;
        List<Cell> newCells = new ArrayList<>(board.cells());
        (action.equals(Action.FLAG) ? flagCell(board.cellAt(position)) : openCell(board.cellAt(position)))
                .forEach(c -> newCells.set(PositionUtilities.indexFromPosition(c.position(), board.level().width()), c));
        return BoardBuilder.create().withLevel(board.level()).withCells(newCells).withMines(board.mines()).build();
    }

    private boolean isOpened(Board board, Cell.Position position) {
        return board.cellAt(position).cellState().equals(Cell.CellState.OPENED);
    }

    private GameState determineSate(Cell.Position position, Board board) {
        return board.hasMineIn(position) && isOpened(board, position) ? GameState.LOST :
                (determineRemainCells(board) == 0 ? GameState.WON : GameState.BEGUN);
    }

    public GameState gameState() {
        return gameState;
    }

    public Board board() {
        return board;
    }

    public record Interaction(Cell.Position position, Action action, long seconds) {}

    public enum Action {
        OPEN, FLAG
    }

    public enum GameState {
        UNBEGUN, BEGUN, WON, LOST
    }
}

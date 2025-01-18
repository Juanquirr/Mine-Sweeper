package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.model.builders.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Interaction> interactions;
    private final Board board;
    private final GameState gameState;

    public Game(Board board, GameState gameState, List<Interaction> interactions, int remainCells) {
        this.interactions = interactions;
        this.board = board;
        this.gameState = gameState;
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
        interactions.add(interaction);
        if (gameState.equals(GameState.UNBEGUN)) board.initializeMinesExcluding(interaction.position());
        return openCellAt(interaction.position(), interaction.action());
    }

    public Board board() {
        return board;
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
        if (board.cellAt(cell.position()).cellState().equals(Cell.CellState.FLAGGED)) return new ArrayList<>();
        if (board.hasMineIn(cell.position())) return revealMines();
        BoardExplorer explorer = new BoardExplorer();
        explorer.exploreFrom(board, cell.position());
        return board.cells().stream()
                .filter(c -> explorer.safeCells().contains(c.position()) || explorer.edges().contains(c.position()))
                .map(Cell::open)
                .toList();
    }

    private List<Cell> revealMines() {
        return board.mines().stream()
                .map(p -> board.cellAt(p).open())
                .toList();
    }

    private List<Cell> flagCell(Cell cell) {
        if (cell.cellState().equals(Cell.CellState.OPENED)) return List.of(cell);
        return List.of(cell.cellState().equals(Cell.CellState.FLAGGED) ? cell.unflag() : cell.flag());
    }

    public Board performActionOn(Cell.Position position, Game.Action action) {
        if (board.cellAt(position).cellState().equals(Cell.CellState.OPENED)) return board;
        ArrayList<Cell> newCells = new ArrayList<>(board.cells());
        List<Cell> cells = action.equals(Action.FLAG) ?
                flagCell(board.cellAt(position)) : openCell(board.cellAt(position));
        cells.forEach(c -> newCells.set(PositionUtilities.indexFromPosition(c.position(), board.level().width()), c));
        return BoardBuilder.create().withLevel(board.level()).withCells(newCells).withMines(board.mines()).build();
    }

    private GameState determineSate(Cell.Position position, Board board) {
        return board.hasMineIn(position) && board.cellAt(position).cellState().equals(Cell.CellState.OPENED) ? GameState.LOST :
                (determineRemainCells(board) == 0 ? GameState.WON : GameState.BEGUN);
    }

    public record Interaction(Cell.Position position, Action action, long seconds) {}

    public enum Action {
        OPEN, FLAG
    }

    public enum GameState {
        UNBEGUN, BEGUN, WON, LOST
    }
}

package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder implements Builder<Game> {
    private List<Game.Interaction> interactions;
    private Board board;
    private Game.GameState gameState;
    private int remainCells;

    private GameBuilder() {
        this.interactions = new ArrayList<>();
        this.gameState = Game.GameState.UNBEGUN;
    }

    public static GameBuilder create() {
        return new GameBuilder();
    }

    public GameBuilder withInteractions(List<Game.Interaction> interactions) {
        this.interactions = new ArrayList<>(interactions);
        return this;
    }

    public GameBuilder withBoard(Board board) {
        this.board = board;
        return this;
    }

    public GameBuilder withGameState(Game.GameState gameState) {
        this.gameState = gameState;
        return this;
    }

    public GameBuilder withRemainCells(int remainCells) {
        this.remainCells = remainCells;
        return this;
    }

    @Override
    public Game build() {
        return new Game(board, gameState, interactions, remainCells);
    }
}

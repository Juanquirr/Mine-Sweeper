package software.ulpgc.minesweeper.architecture.model.builders;

import software.ulpgc.minesweeper.architecture.model.Board;
import software.ulpgc.minesweeper.architecture.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameBuilder implements Builder<Game> {
    private List<Game.Interaction> interactions;
    private Board board;
    private Game.GameState gameState;

    private GameBuilder() {
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

    @Override
    public Game build() {
        Game game = new Game(board, gameState);
        if (!Objects.isNull(interactions)) interactions.forEach(game::add);
        return game;
    }
}

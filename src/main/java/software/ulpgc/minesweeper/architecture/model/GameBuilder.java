package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.view.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameBuilder implements Builder<Game> {
    private List<Game.Interaction> interactions;
    private Board board;
    private Game.GameState gameState;

    public GameBuilder interactions(List<Game.Interaction> interactions) {
        this.interactions = new ArrayList<>(interactions);
        return this;
    }

    public GameBuilder board(Board board) {
        this.board = board;
        return this;
    }

    public GameBuilder gameState(Game.GameState gameState) {
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

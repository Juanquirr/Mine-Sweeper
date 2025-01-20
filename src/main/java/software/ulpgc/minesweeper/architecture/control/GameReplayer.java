package software.ulpgc.minesweeper.architecture.control;

import software.ulpgc.minesweeper.architecture.model.Game;
import software.ulpgc.minesweeper.architecture.model.builders.BoardBuilder;
import software.ulpgc.minesweeper.architecture.model.builders.GameBuilder;

import java.util.ArrayList;
import java.util.List;

public class GameReplayer {
    private final List<Game.Interaction> interactions;
    private final Game game;
    private final int index;
    private final ReplayState replayState;

    private GameReplayer(List<Game.Interaction> interactions, Game game, int index, ReplayState replayState) {
        this.interactions = interactions;
        this.game = game;
        this.index = index;
        this.replayState = replayState;
    }

    public GameReplayer execute() {
        if (interactions.isEmpty()) return this;
        try {
            Thread.sleep(interactions.get(index).seconds() * 10);
        } catch (InterruptedException ignored) {}
        return new Builder()
                .withInteractions(interactions)
                .withGame(game.add(interactions.get(index)))
                .withIndex(index + 1)
                .withReplayState(replayState)
                .build();
    }

    public GameReplayer defineGame(Game game) {
        return new GameReplayer(
                game.interactions(),
                GameBuilder.create()
                        .withBoard(
                                BoardBuilder.create()
                                        .withLevel(game.board().level())
                                        .withMines(game.board().mines()).build()
                        ).build(), 0, ReplayState.STARTED
        );
    }

    public Game game() {
        return game;
    }

    public ReplayState replayState() {
        return replayState;
    }

    public GameReplayer reset() {
        return new Builder().build();
    }

    public enum ReplayState {
        STARTED, STOPPED
    }

    public static class Builder {
        private List<Game.Interaction> interactions;
        private Game game;
        private int index;
        private ReplayState replayState;

        public Builder() {
            this.interactions = new ArrayList<>();
            this.game = GameBuilder.create().build();
            this.index = 0;
            this.replayState = ReplayState.STOPPED;
        }

        public Builder withInteractions(List<Game.Interaction> interactions) {
            this.interactions = interactions;
            return this;
        }

        public Builder withGame(Game game) {
            this.game = game;
            return this;
        }

        public Builder withIndex(int index) {
            this.index = index;
            return this;
        }

        public Builder withReplayState(ReplayState replayState) {
            this.replayState = replayState;
            return this;
        }

        public GameReplayer build() {
            return new GameReplayer(interactions, game, index, replayState);
        }
    }
}

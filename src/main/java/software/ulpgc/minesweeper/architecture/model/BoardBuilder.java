package software.ulpgc.minesweeper.architecture.model;

import software.ulpgc.minesweeper.architecture.view.Builder;

public class BoardBuilder implements Builder<Board> {
    private Level level;

    public BoardBuilder level(Level level) {
        this.level = level;
        return this;
    }

    public Board build() {
        return new Board(level);
    }
}

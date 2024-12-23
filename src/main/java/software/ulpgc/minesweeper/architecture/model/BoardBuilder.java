package software.ulpgc.minesweeper.architecture.model;

public class BoardBuilder {
    private Level level;

    public BoardBuilder level(Level level) {
        this.level = level;
        return this;
    }

    public Board build() {
        return new Board(level);
    }
}

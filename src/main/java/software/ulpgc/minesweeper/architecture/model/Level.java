package software.ulpgc.minesweeper.architecture.model;

public enum Level {
    BEGINNER(5, 5, 4),
    INTERMEDIATE(10, 10, 8),
    EXPERT(20, 20, 50);

    private final int height;
    private final int width;
    private final int mines;

    Level(int height, int width, int mines) {
        this.mines = mines;
        this.height = height;
        this.width = width;
    }

    public int numberOfMines() {
        return mines;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }
     public Size size() { return new Size(width, height); }

    public record Size(int width, int height) {}
}

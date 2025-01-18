package software.ulpgc.minesweeper.architecture.model;

public enum Level {
    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(30, 16, 99);

    private final int height;
    private final int width;
    private final int mines;

    Level(int width, int height, int mines) {
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

    @Override
    public String toString() {
        return "Level{" +
                "height=" + height +
                ", width=" + width +
                ", mines=" + mines +
                '}';
    }
}

package software.ulpgc.minesweeper.architecture.model;

public enum Difficulty {
    BEGINNER(5, 5, 4),
    INTERMEDIATE(10, 10, 8),
    EXPERT(20, 20, 50);

    private final int height;
    private final int width;
    private final int mines;

    Difficulty(int height, int width, int mines) {
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
}

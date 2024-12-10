package software.ulpgc.minesweeper.architecture.model;

public enum Difficulty {
    EASY(5, 5, 4),
    MEDIUM(10, 10, 8),
    HARD(20, 20, 16);

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

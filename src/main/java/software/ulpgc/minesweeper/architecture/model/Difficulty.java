package software.ulpgc.minesweeper.architecture.model;

public enum Difficulty {
    EASY(5, 5, 2),
    MEDIUM(10, 10, 4),
    HARD(20, 20, 8),
    EXPERT(0, 0, 0);

    private int height;
    private int width;
    private int bombsCounter;

    Difficulty(int height, int width, int bombsCounter) {
        this.bombsCounter = bombsCounter;
        this.height = height;
        this.width = width;
    }

    public int getBombsCounter() {
        return bombsCounter;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Difficulty setCustomBombsCounter(int bombsCounter) {
        if (this != EXPERT) throw new RuntimeException();
        this.bombsCounter = bombsCounter;
        return this;
    }
}

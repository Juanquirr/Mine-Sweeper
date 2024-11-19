package software.ulpgc.minesweeper.view;

public enum Difficulty {
    EASY(4),
    MEDIUM(6),
    HARD(8),
    EXPERT(0);

    private int bombsCounter;

    Difficulty(int bombsCounter) {
        this.bombsCounter = bombsCounter;
    }

    public int getBombsCounter() {
        return bombsCounter;
    }

    public Difficulty setCustomBombsCounter(int bombsCounter) {
        this.bombsCounter = bombsCounter;
        return this;
    }
}

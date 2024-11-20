package software.ulpgc.minesweeper.view;

public enum Difficulty {
    EASY(10),
    MEDIUM(40),
    HARD(99),
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

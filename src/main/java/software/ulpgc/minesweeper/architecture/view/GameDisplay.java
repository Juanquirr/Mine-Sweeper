package software.ulpgc.minesweeper.architecture.view;

public interface GameDisplay {
    void startGame();
    void stopGame();
    BoardDisplay boardDisplay();
    Chronometer chronometer();
}

package software.ulpgc.minesweeper.architecture.view;

public interface GameDisplay {
    void startGame();

    void stopGame();

    void resetGame();

    void showWinDisplay();

    void showLostDisplay();

    BoardDisplay boardDisplay();

    Chronometer chronometer();

    CounterDisplay counterDisplay();

    ReplayController replayController();
}

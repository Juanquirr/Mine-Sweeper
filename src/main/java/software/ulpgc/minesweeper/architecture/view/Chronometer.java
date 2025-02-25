package software.ulpgc.minesweeper.architecture.view;

public interface Chronometer {
    void start();

    void stop();

    void reset();

    long currentTime();
}

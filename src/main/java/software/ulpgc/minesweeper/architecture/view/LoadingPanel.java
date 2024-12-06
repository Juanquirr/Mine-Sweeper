package software.ulpgc.minesweeper.architecture.view;

public interface LoadingPanel {
    LoadingPanel showPanel();
    LoadingPanel hidePanel();
    DifficultyDialog difficultyDialog();
}

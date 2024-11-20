package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.model.BoardMatrix;
import software.ulpgc.minesweeper.model.BoardRow;
import software.ulpgc.minesweeper.view.Difficulty;
import software.ulpgc.minesweeper.view.MainFrame;

public class Main {
    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        BoardMatrix bm = BoardMatrix.ofSize(8, 8, Difficulty.EASY);

        for (BoardRow row : bm)
            System.out.println(row);
    }
}

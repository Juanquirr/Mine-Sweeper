package software.ulpgc.minesweeper.apps.windows;

import software.ulpgc.minesweeper.model.BoardMatrix;
import software.ulpgc.minesweeper.model.BoardRow;

public class Main {
    public static void main(String[] args) {
        /*
        MainFrame mineSweeperFrame = new MainFrame();
        MainPanel mainPanel = new MainPanel();
        mineSweeperFrame.add(mainPanel.getPanel());
        mineSweeperFrame.revalidate();
        mineSweeperFrame.repaint();
        mainPanel.getPanel().setVisible(true);
        */
        BoardMatrix bm = BoardMatrix.ofSize(2, 2);
        for (BoardRow row : bm)
            System.out.println(row);
    }
}

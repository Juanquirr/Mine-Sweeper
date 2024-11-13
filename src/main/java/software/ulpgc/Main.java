package software.ulpgc;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MineBoard mineBoard = new MineBoard();
        StartButton startButton = new StartButton();
        mineBoard.add(StartButton.initialToggle());
        mineBoard.add(StartButton.createBoard());
    }


}

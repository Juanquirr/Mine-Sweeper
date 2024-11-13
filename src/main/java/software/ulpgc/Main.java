package software.ulpgc;

public class Main {
    public static void main(String[] args) {
        MineSweeperFrame mineSweeperFrame = new MineSweeperFrame();
        MainPanel mainPanel = new MainPanel();
        mineSweeperFrame.add(mainPanel.getPanel());
        mineSweeperFrame.revalidate();
        mineSweeperFrame.repaint();
        mainPanel.getPanel().setVisible(true);
    }
}

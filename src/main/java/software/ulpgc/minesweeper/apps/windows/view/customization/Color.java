package software.ulpgc.minesweeper.apps.windows.view.customization;

public enum Color {
    ONE(java.awt.Color.BLUE),
    TWO(java.awt.Color.GREEN),
    THREE(java.awt.Color.MAGENTA),
    FOUR(java.awt.Color.ORANGE),
    UnopenedCell(new java.awt.Color(0, 100, 0)),
    SafeCell(new java.awt.Color(101, 67, 33)),
    EdgeCell(new java.awt.Color(81, 54, 26)),
    MineCell(new java.awt.Color(178, 34, 34)),
    DefaultButtonColor(new java.awt.Color(0, 144, 0)),
    HoverButtonColor(new java.awt.Color(0, 200, 50)),
    SelectedButtonColor(new java.awt.Color(101, 67, 33)),
    White(java.awt.Color.WHITE);

    private final java.awt.Color color;

    Color(java.awt.Color color) {
        this.color = color;
    }

    public java.awt.Color getColor() {
        return color;
    }
}

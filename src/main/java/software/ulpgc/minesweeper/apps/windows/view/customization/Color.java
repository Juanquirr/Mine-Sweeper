package software.ulpgc.minesweeper.apps.windows.view.customization;

public enum Color {
    ONE(new java.awt.Color(51, 165, 255)),
    TWO(new java.awt.Color(51, 255, 175)),
    THREE(new java.awt.Color(100, 255, 51)),
    FOUR(new java.awt.Color(240, 255, 51)),
    FIVE(new java.awt.Color(255, 156, 51)),
    SIX(new java.awt.Color(255, 57, 51)),
    SEVEN(new java.awt.Color(202, 51, 255)),
    EIGHT(new java.awt.Color(104, 51, 255)),
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

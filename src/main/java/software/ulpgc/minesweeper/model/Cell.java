package software.ulpgc.minesweeper.model;

public class Cell {
    private final Cell.TaleSate state;

    public Cell() {
        this.state = TaleSate.NonSelected;
    }

    public TaleSate state() {
        return state;
    }

    public enum TaleSate {
        NonSelected, Selected
    }

    @Override
    public String toString() {
        return "Cell{" +
                "state=" + state +
                '}';
    }
}

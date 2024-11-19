package software.ulpgc.minesweeper.model;

public class Cell {
    private Cell.TaleSate state;

    public Cell() {
        this.state = TaleSate.NonSelected;
    }

    public TaleSate state() {
        return state;
    }

    public void setBomb() {
        state = TaleSate.HasBomb;
    }

    public enum TaleSate {
        NonSelected, Selected, HasBomb
    }

    @Override
    public String toString() {
        return "Cell{" +
                "state=" + state +
                '}';
    }
}

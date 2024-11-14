package software.ulpgc.minesweeper.model;

public class Tale {
    private final Tale.TaleSate state;

    public Tale() {
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
        return "Tale{" +
                "state=" + state +
                '}';
    }
}

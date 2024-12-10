package software.ulpgc.minesweeper.architecture.model;

public record Position(int x, int y) {
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return x * 10 + y;
    }
}

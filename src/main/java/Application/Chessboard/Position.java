package Application.Chessboard;


public class Position {
    private char x;
    private int y;
    private Ownership owner;

    public Position(char x, int y) {
        this.x = x;
        this.y = y;
        this.owner = Ownership.NEUTRAL;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ownership getOwner() {
        return owner;
    }

    public void setOwner(Ownership owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "[" + x +","+ y + "]";
    }
}


package Application.Chessboard;


import Application.ChessPieces.Chessman;
import Application.ChessPieces.Phantom;

public class Position {
    private int x;
    private int y;
    private Chessman chessman;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.chessman = new Phantom();
        chessman.setPosition(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Chessman getChessman() {
        return chessman;
    }

    public void setChessman(Chessman chessman) {
        this.chessman = chessman;
    }

    @Override
    public String toString() {
        return "[" + x +","+ y + "]";
    }
}


package Application.Chessboard;


import Application.ChessPieces.Chessman;
import Application.ChessPieces.Phantom;

public class Position {
    private char x;
    private int y;
    private Chessman chessman;

    public Position(char x, int y) {
        this.x = x;
        this.y = y;
        this.chessman = new Phantom();
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
        return "[" + x +","+ y + "]"; // here, instead of this I can use "[] + chessman + "]"
    }
}


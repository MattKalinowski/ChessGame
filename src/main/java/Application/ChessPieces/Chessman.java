package Application.ChessPieces;

import Application.Chessboard.Position;

public interface Chessman {
    void move(char x, int y);
    Position getPosition();
    void setPosition(Position position);
    Team getTeam();
    void setCaptured();
}

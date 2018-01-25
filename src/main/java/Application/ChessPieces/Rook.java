package Application.ChessPieces;

import Application.Chessboard.Ownership;
import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.*;

public class Rook implements Chessman {

    private Position position;
    private Team team;
    private boolean captured;

    public Rook(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (!captured && inBounds(x,y) && isNotAlly(x,y,team) && isPermeableAdjacently(x, y, position))
            moveScript(x,y);
    }

    private void moveScript(char x, int y) {
        if (((x != position.getX() && y == position.getY()) || (x == position.getX() && y != position.getY()))) {
            position.setOwner(Ownership.NEUTRAL);
            setPosition(BOARD.getPosition(x, y));
            BOARD.getPosition(x, y).setOwner(getOwnership(team));
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setCaptured() {
        this.captured = true;
    }

    @Override
    public String toString() {
        return team.toString().toLowerCase() + " rook";
    }
}

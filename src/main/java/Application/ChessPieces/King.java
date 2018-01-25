package Application.ChessPieces;

import Application.Chessboard.Ownership;
import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.getOwnership;
import static Application.Chessboard.Utils.inBounds;
import static Application.Chessboard.Utils.isNotAlly;

public class King implements Chessman {

    private Position position;
    private Team team;
    private boolean captured;

    public King(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (!captured && inBounds(x,y) && isNotAlly(x,y,team))
        moveScript(x,y);
    }

    private void moveScript(char x, int y) {
        int distanceX = Math.abs(position.getX() - x);
        int distanceY = Math.abs(position.getY() - y);
        if ((distanceX == 1 || distanceY == 1 || (distanceX == 1 && distanceY == 1))) {
            position.setOwner(Ownership.NEUTRAL);
            setPosition(BOARD.getPosition(x,y));
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
        return team.toString().toLowerCase() + " king";
    }
}

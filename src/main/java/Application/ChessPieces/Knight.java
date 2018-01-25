package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Ownership.NEUTRAL;
import static Application.Chessboard.Utils.getOwnership;
import static Application.Chessboard.Utils.inBounds;
import static Application.Chessboard.Utils.isNotAlly;

public class Knight implements Chessman {

    private Position position;
    private Team team;
    private boolean captured;

    public Knight(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (!captured && inBounds(x,y) && isNotAlly(x,y,team))
            moveScript(x,y);
    }

    private void moveScript(char x, int y) {
        if ((Math.abs(position.getX() - x) == 2 && Math.abs(position.getY() - y) == 1)
                || ((Math.abs(position.getX() - x) == 1 && Math.abs(position.getY() - y) == 2))) {
            position.setOwner(NEUTRAL);
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
        return team.toString().toLowerCase() + " knight";
    }
}

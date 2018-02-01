package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.*;

public class Bishop implements Chessman {

    private Position position;
    private Team team;

    public Bishop(Team team) {
        this.team = team;
    }

    public void move(int x, int y) {
        if (inBounds(x,y) && isNotAlly(x,y,team) && isPermeableDiagonally(x, y, position))
            moveScript(x,y);
    }

    private void moveScript(int x, int y) {
        Position target = BOARD.getPosition(x, y);
        if (((Math.abs(position.getX() - x)) == (Math.abs(position.getY() - y)))) {
            relocate(this, position, target);
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

    @Override
    public String toString() {
        return "[" + team.toString().charAt(0) + "B]";

    }
}

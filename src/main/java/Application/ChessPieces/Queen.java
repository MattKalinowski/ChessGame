package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.*;

public class Queen implements Chessman {

    private Position position;
    private Team team;

    public Queen(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (inBounds(x,y) && isNotAlly(x,y,team)) {
            moveScript(x,y);
        }
    }

    private void moveScript(char x, int y) {
        Position target = BOARD.getPosition(x,y);
        if ((Math.abs(position.getX() - x) == Math.abs(position.getY() - y)) && isPermeableDiagonally(x, y, position)) {
            position.setChessman(new Phantom());
            setPosition(target);
            target.setChessman(this);
        } else if (((x != position.getX() && y == position.getY()) || (x == position.getX() && y != position.getY()))
                && isPermeableAdjacently(x, y, position)) {
            position.setChessman(new Phantom());
            setPosition(target);
            target.setChessman(this);
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
    return "[" + team.toString().charAt(0) + "Q]";
    }
}

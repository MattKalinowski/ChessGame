package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.inBounds;
import static Application.Chessboard.Utils.isNotAlly;
import static Application.Chessboard.Utils.relocate;

public class Knight implements Chessman {

    private Position position;
    private Team team;

    public Knight(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (inBounds(x,y) && isNotAlly(x,y,team))
            moveScript(x,y);
    }

    private void moveScript(char x, int y) {
        Position target = BOARD.getPosition(x,y);
        if ((Math.abs(position.getX() - x) == 2 && Math.abs(position.getY() - y) == 1)
                || ((Math.abs(position.getX() - x) == 1 && Math.abs(position.getY() - y) == 2))) {
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
        return "[" + team.toString().charAt(0) + "N]";
    }
}

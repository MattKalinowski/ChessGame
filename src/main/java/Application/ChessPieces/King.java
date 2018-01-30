package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.inBounds;
import static Application.Chessboard.Utils.isNotAlly;

public class King implements Chessman {

    private Position position;
    private Team team;

    public King(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (inBounds(x,y) && isNotAlly(x,y,team))
        moveScript(x,y);
    }

    private void moveScript(char x, int y) {
        Position target = BOARD.getPosition(x,y);
        int distanceX = Math.abs(position.getX() - x);
        int distanceY = Math.abs(position.getY() - y);
        if ((distanceX == 1 || distanceY == 1 || (distanceX == 1 && distanceY == 1))) {
            position.setChessman(new Phantom());
            position.getChessman().setPosition(position);
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
        return "[" + team.toString().charAt(0) + "K]";
    }
}

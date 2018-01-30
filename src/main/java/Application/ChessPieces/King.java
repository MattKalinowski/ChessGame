package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.ChessPieces.Team.BLACK;
import static Application.ChessPieces.Team.WHITE;
import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.*;

public class King implements Chessman {

    private Position position;
    private Team team;
    private boolean hasMoved;

    public King(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        if (inBounds(x,y))
        moveScript(x,y);
    }

    private void moveScript(char x, int y) {
        Position target = BOARD.getPosition(x,y);
        int distanceX = Math.abs(position.getX() - x);
        int distanceY = Math.abs(position.getY() - y);
        if ((distanceX == 1 || distanceY == 1 || (distanceX == 1 && distanceY == 1)) && isNotAlly(x,y,team)) {
            relocate(this, position, target);
        }
        if (isRook(target) && isPermeableAdjacently(x,y,position) && !hasMoved && target.getY() == position.getY()) {
            if (target.getX() == 'a') castling(x,y, 'c', 'd');
            if (target.getX() == 'h') castling(x,y,'g','f');
        }
        hasMoved = true;
    }

    private void castling(char x, int y, char kingX, char rookX) {
        Position rookPosition = BOARD.getPosition(x,y);
        BOARD.getPosition(kingX,y).setChessman(this);
        BOARD.getPosition(rookX,y).setChessman(new Rook(team));
        position.setChessman(new Phantom());
        position.getChessman().setPosition(position);
        rookPosition.setChessman(new Phantom());
        rookPosition.getChessman().setPosition(rookPosition);

    }

    private boolean isRook(Position target) {
        return target.getChessman().getClass().getSimpleName().equals("Rook");
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

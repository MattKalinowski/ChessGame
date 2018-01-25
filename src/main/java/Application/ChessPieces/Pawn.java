package Application.ChessPieces;

import Application.Chessboard.Ownership;
import Application.Chessboard.Position;

import static Application.ChessPieces.Pawn.Direction.*;
import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.getOwnership;

public class Pawn implements Chessman {

    private Position position;
    private Team team;
    private Direction direction;
    private boolean captured;

    public Pawn(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        int up = 1, down = -1;
        if (direction == null) setDirection();
        if (!captured)
        switch (direction) {
            case UP: moveScript(x, y, up);
            break;
            case DOWN: moveScript(x, y, down);
            break;
        }
    }

    private void moveScript(char x, int y, int direction) {
        if (Math.abs(position.getX() - x) == 1 && (y == position.getY() + direction)
                && (BOARD.getPosition(x,y).getOwner().toString().equals(enemyTeam().toString()))) {
            position.setOwner(Ownership.NEUTRAL);
            setPosition(BOARD.getPosition(x, y));
            BOARD.getPosition(x, y).setOwner(getOwnership(team));
        }
        if ((position.getX() == x) && (y == position.getY() + direction)
                && (BOARD.getPosition(x,y).getOwner().toString().equals(Ownership.NEUTRAL.toString()))) {
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

    private Team enemyTeam() {
        if (Team.WHITE == team) return Team.BLACK;
        else return Team.WHITE;
    }

    private void setDirection() {
        int row = position.getY();
        int upperRow = 7;
        int lowerRow = 2;
        if (row == upperRow) {
            direction = DOWN;
        }
        if (row == lowerRow) {
            direction = UP;
        }
    }

    @Override
    public String toString() {
        return team.toString().toLowerCase() + " pawn";
    }

    enum Direction {
        UP, DOWN
    }
}

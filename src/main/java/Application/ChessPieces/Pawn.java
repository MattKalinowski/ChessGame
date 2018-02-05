package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.ChessPieces.Pawn.Direction.*;
import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.enemyTeam;
import static Application.Chessboard.Utils.isPermeableAdjacently;
import static Application.Chessboard.Utils.relocate;

public class Pawn implements Chessman {

    private Position position;
    private Team team;
    private Direction direction;

    public Pawn(Team team) {
        this.team = team;
    }

    public void move(int x, int y) {
        int up = -1, down = 1;
        switch (direction) {
            case UP: moveScript(x, y, up);
            break;
            case DOWN: moveScript(x, y, down);
            break;
        }
        promotion();
    }

    private void moveScript(int x, int y, int direction) {
        Position target = BOARD.getPosition(x, y);
        if (Math.abs(position.getX() - x) == 1 && (y == position.getY() + direction)
                && (target.getChessman().getTeam() == enemyTeam(team))) {
            relocate(this, position, target);
        }
        if ((position.getX() == x) && (y == position.getY() + direction)
                && (target.getChessman().getTeam() == Team.NEUTRAL)) {
            relocate(this, position, target);
        }
        if (((this.direction == UP && position.getY() == 6) || (this.direction == DOWN && position.getY() == 1))
                && (y == position.getY() + (2 * direction)) && x == position.getX()
                && isPermeableAdjacently(x,y,position) && target.getChessman().getTeam() == Team.NEUTRAL) {
            relocate(this, position, target);
        }
    }

    private void setDirection() {
        int row = position.getY();
        int upperRow = 1;
        int lowerRow = 6;
        if (row == upperRow) {
            direction = DOWN;
        }
        if (row == lowerRow) {
            direction = UP;
        }
    }

    private void promotion() {
        if (direction == UP && position.getY() == 0) {
            position.setChessman(new Queen(team));
            position.getChessman().setPosition(position);
        }
        if (direction == DOWN && position.getY() == 7) {
            position.setChessman(new Queen(team));
            position.getChessman().setPosition(position);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        if (direction == null) setDirection();
    }

    public Team getTeam() {
        return team;
    }

    public int getDirection() {
        switch (direction) {
            case UP: return -1;
            case DOWN: return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[" + team.toString().charAt(0) + "P]";
    }

    enum Direction {
        UP, DOWN
    }
}

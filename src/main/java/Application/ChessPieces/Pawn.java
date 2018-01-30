package Application.ChessPieces;

import Application.Chessboard.Position;

import static Application.ChessPieces.Pawn.Direction.*;
import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.isPermeableAdjacently;
import static Application.Chessboard.Utils.relocate;

public class Pawn implements Chessman {

    private Position position;
    private Team team;
    private Direction direction;

    public Pawn(Team team) {
        this.team = team;
    }

    public void move(char x, int y) {
        int up = 1, down = -1;
        if (direction == null) setDirection();
        switch (direction) {
            case UP: moveScript(x, y, up);
            break;
            case DOWN: moveScript(x, y, down);
            break;
        }
        evolve();
    }

    private void moveScript(char x, int y, int direction) {
        Position target = BOARD.getPosition(x, y);
        if (Math.abs(position.getX() - x) == 1 && (y == position.getY() + direction)
                && (target.getChessman().getTeam() == enemyTeam())) {
            relocate(this, position, target);
        }
        if ((position.getX() == x) && (y == position.getY() + direction)
                && (target.getChessman().getTeam() == Team.NEUTRAL)) {
            relocate(this, position, target);
        }
        if (((this.direction == UP && position.getY() == 2) || (this.direction == DOWN && position.getY() == 7))
                && (y == position.getY() + (2 * direction)) && isPermeableAdjacently(x,y,position)
                && target.getChessman().getTeam() == Team.NEUTRAL) {
            relocate(this, position, target);
        }
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

    private void evolve() {
        if (direction == UP && position.getY() == 8) {
            position.setChessman(new Queen(team));
            position.getChessman().setPosition(position);
        }
        if (direction == DOWN && position.getY() == 1) {
            position.setChessman(new Queen(team));
            position.getChessman().setPosition(position);
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
        return "[" + team.toString().charAt(0) + "P]";
    }

    enum Direction {
        UP, DOWN
    }
}

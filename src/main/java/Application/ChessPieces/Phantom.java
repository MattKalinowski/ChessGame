package Application.ChessPieces;

import Application.Chessboard.Position;

public class Phantom implements Chessman {
    private Position position;
    private Team team;

    public Phantom() {
        this.team = Team.NEUTRAL;
    }

    public void move(char x, int y) {
        System.out.println("This field is empty.");
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
        return "[__]";
    }
}

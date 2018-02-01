package Application.Chessboard;

import Application.ChessPieces.Chessman;
import Application.ChessPieces.Phantom;
import Application.ChessPieces.Team;


import static Application.Chessboard.Board.BOARD;

public class Utils {

    public static boolean inBounds(int x, int y) {
        return ((x >= 0 && x <= 7) && (y >= 0 && y <= 7));
    }

    public static boolean isNotAlly(int x, int y, Team team) {
        return Board.BOARD.getPosition(x,y).getChessman().getTeam() != team;
    }

    public static boolean isPermeableAdjacently(int x, int y, Position position) {
        Position[][] board = BOARD.getInstance();
        if (position.getX() - x != 0) {
            Position[] row = board[y];
            int start = Math.min(position.getX(), x) + 1;
            int end = Math.max(position.getX(), x) - 1;
            for (int i = start; i <= end; i++) {
                if(row[i].getChessman().getTeam() != Team.NEUTRAL)
                    return false;
            }
        }
        if (position.getY() - y != 0) {
            int j = x;
            int start = Math.min(y, position.getY()) + 1;
            int end = Math.max(y, position.getY()) - 1;
            for (int i = start; i <= end; i++) {
                if (board[i][j].getChessman().getTeam() != Team.NEUTRAL)
                    return false;
            }
        }
        return true;
    }

    public static boolean isPermeableDiagonally(int x, int y, Position position) {
        Position[][] board = BOARD.getInstance();
        int startY = Math.min(y, position.getY()) + 1;
        int startX = Math.min(x, position.getX()) + 1;
        int endY = Math.max(y, position.getY()) - 1;
        int endX = Math.max(x, position.getX()) - 1;
        int q = endX;
        int p = startX;
        if (startY < endY) {
            for (int i = startY; i <= endY; i++) {
                for (int j = startX; j <= endX; j++) {
                    if (board[i][q].getChessman().getTeam() != Team.NEUTRAL)
                        return false;
                }
                q--;
            }
        } else {
            for (int i = startY; i <= endY; i++) {
                for (int j = startX; j <= endX; j++) {
                    if (board[i][p].getChessman().getTeam() != Team.NEUTRAL)
                        return false;
                }
                p++;
            }
        }

        return true;
    }

    public static void relocate(Chessman chessman, Position currentPosition, Position targetPosition) {
        currentPosition.setChessman(new Phantom());
        currentPosition.getChessman().setPosition(currentPosition);
        chessman.setPosition(targetPosition);
        targetPosition.setChessman(chessman);
    }

    public static Team enemyTeam(Team team) {
        if (Team.WHITE == team) return Team.BLACK;
        else return Team.WHITE;
    }

}

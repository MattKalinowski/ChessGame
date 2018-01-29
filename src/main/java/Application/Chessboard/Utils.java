package Application.Chessboard;

import Application.ChessPieces.Team;


import static Application.Chessboard.Board.BOARD;

public class Utils {

    public static boolean inBounds(char x, int y) {
        return ((x >= 'a' && x <= 'h') && (y >= 1 && y <= 8));
    }

    public static boolean isNotAlly(char x, int y, Team team) {
        return Board.BOARD.getPosition(x,y).getChessman().getTeam() != team;
    }

    public static boolean isPermeableAdjacently(char x, int y, Position position) {
        Position[][] board = BOARD.getInstance();
        int rowIndex = Math.abs(y-8);
        if (position.getX() - x != 0) {
            Position[] row = board[rowIndex];
            int start = Math.min(position.getX(), x) - 97 + 1;
            int end = Math.max(position.getX(), x) - 97 - 1;
            for (int i = start; i < end; i++) {
                if(row[i].getChessman().getTeam() != Team.NEUTRAL)
                    return false;
            }
        }
        if (position.getY() - y != 0) {
            int column = x - 97;
            int start = Math.min(rowIndex, Math.abs(position.getY() - 8)) + 1;
            int end = Math.max(rowIndex, Math.abs(position.getY() - 8)) - 1;
            for (int i = start; i <= end; i++) {
                if (board[i][column].getChessman().getTeam() != Team.NEUTRAL)
                    return false;
            }
        }
        return true;
    }

    public static boolean isPermeableDiagonally(char x, int y, Position position) {
        Position[][] board = BOARD.getInstance();
        int targetRow = Math.abs(y-8);
        int targetColumn = x - 97;
        int currentRow = Math.abs(position.getY()-8);
        int currentColumn = position.getX() - 97;
        int startY = Math.min(targetRow, currentRow) + 1;
        int startX = Math.min(targetColumn, currentColumn) + 1;
        int endY = Math.max(targetRow, currentRow) - 1;
        int endX = Math.max(targetColumn, currentColumn) - 1;
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

}

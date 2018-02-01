package Application.Game;

import Application.ChessPieces.Pawn;
import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.enemyTeam;

class CheckEngine {

    Game game;

    CheckEngine(Game game) {
        this.game = game;
    }

    boolean isKingExposed(Position kingPosition) {
        int kingX = kingPosition.getX() - 97;
        int kingY = Math.abs(kingPosition.getY() - 8);
        int row = 0;
        for (Position[] i : BOARD.getInstance()) {
            int column = 0;
            for (Position j : i) {
                if (j.getChessman().getTeam() == enemyTeam(game.getCurrentPlayer())) {
                    if (j.getChessman().getClass().getSimpleName().equals("Pawn")) {
                        Pawn pawn = (Pawn)j.getChessman();
                        int direction = pawn.getDirection() * (-1);
                        return inRangeOfPawn(column, row, kingX, kingY, direction);
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Knight")) {
                        return inRangeOfKnight(column, row, kingX, kingY);
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Bishop")) {
                        return inRangeOfBishop(column, row, kingX, kingY, kingPosition, j);
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Rook")) {
                        return inRangeOfRook(column, row, kingX, kingY);
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Queen")) {
                        return inRangeOfQueen(column, row, kingX, kingY);
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("King")) {
                        return inRangeOfKing(column, row, kingX, kingY);
                    }
                }
                column++;
            }
            row++;
        }
        return false;
    }

    // direction is reversed because of reversed indexing on Y axis, in case of any issues this is the place for check
    private boolean inRangeOfPawn(int x, int y, int kingX, int kingY, int direction) {
        return Math.abs(x - kingX) == 1 && (kingY == y + direction);
    }

    private boolean inRangeOfKnight(int x, int y, int kingX, int kingY) {
        return (Math.abs(x - kingX) == 2 && Math.abs(y - kingY) == 1)
                || ((Math.abs(x - kingX) == 1 && Math.abs(y - kingY) == 2));
    }

    private boolean inRangeOfBishop(int x, int y, int kingX, int kingY, Position kingPosition, Position position) {
        return true;
    }

    private boolean inRangeOfRook(int x, int y, int kingX, int kingY) {
        return false;
    }

    private boolean inRangeOfQueen(int x, int y, int kingX, int kingY) {
        return false;
    }

    private boolean inRangeOfKing(int x, int y, int kingX, int kingY) {
        return false;
    }

}

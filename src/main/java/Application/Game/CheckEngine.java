package Application.Game;

import Application.ChessPieces.Pawn;
import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Utils.enemyTeam;
import static Application.Chessboard.Utils.isPermeableAdjacently;
import static Application.Chessboard.Utils.isPermeableDiagonally;

class CheckEngine {

    private Game game;

    CheckEngine(Game game) {
        this.game = game;
    }

    boolean isKingExposed(Position kingPosition) {
        boolean exposed = false;
        int kingX = kingPosition.getX();
        int kingY = kingPosition.getY();
        int row = 0;
        for (Position[] i : BOARD.getInstance()) {
            int column = 0;
            for (Position j : i) {
                if (j.getChessman().getTeam() == enemyTeam(game.getCurrentPlayer())) {
                    if (j.getChessman().getClass().getSimpleName().equals("Pawn")) {
                        Pawn pawn = (Pawn)j.getChessman();
                        int direction = pawn.getDirection();
                        exposed = inRangeOfPawn(column, row, kingX, kingY, direction);
                        if (exposed) return exposed;
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Knight")) {
                        exposed = inRangeOfKnight(column, row, kingX, kingY);
                        if (exposed) return exposed;
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Bishop")) {
                        exposed = inRangeOfBishop(column, row, kingX, kingY, j);
                        if (exposed) return exposed;
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Rook")) {
                        exposed = inRangeOfRook(column, row, kingX, kingY, j);
                        if (exposed) return exposed;
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("Queen")) {
                        exposed = inRangeOfQueen(column, row, kingX, kingY, j);
                        if (exposed) return exposed;
                    }
                    if (j.getChessman().getClass().getSimpleName().equals("King")) {
                        exposed = inRangeOfKing(column, row, kingX, kingY);
                        if (exposed) return exposed;
                    }
                }
                column++;
            }
            row++;
        }
        return exposed;
    }

    private boolean inRangeOfPawn(int x, int y, int kingX, int kingY, int direction) {
        return Math.abs(x - kingX) == 1 && (kingY == y + direction);
    }

    private boolean inRangeOfKnight(int x, int y, int kingX, int kingY) {
        return (Math.abs(x - kingX) == 2 && Math.abs(y - kingY) == 1)
                || ((Math.abs(x - kingX) == 1 && Math.abs(y - kingY) == 2));
    }

    private boolean inRangeOfBishop(int x, int y, int kingX, int kingY, Position position) {
        return (Math.abs(x - kingX) == Math.abs(y - kingY)) && isPermeableDiagonally(kingX, kingY, position);
    }

    private boolean inRangeOfRook(int x, int y, int kingX, int kingY, Position position) {
        return ((kingX != x && kingY == y) || (kingX == x && kingY != y)) && isPermeableAdjacently(kingX,kingY,position);
    }

    private boolean inRangeOfQueen(int x, int y, int kingX, int kingY, Position position) {
        if ((Math.abs(x - kingX) == Math.abs(y - kingY)) && isPermeableDiagonally(kingX,kingY,position)) {
            return true;
        }
        if (((kingX != x && kingY == y) || (kingX == x && kingY != y)) && isPermeableAdjacently(kingX,kingY,position)) {
            return true;
        }
        return false;
    }

    private boolean inRangeOfKing(int x, int y, int kingX, int kingY) {
        return (Math.abs(x - kingX) == 1 && Math.abs(y - kingY) == 0)
                || (Math.abs(x - kingX) == 0 && Math.abs(y - kingY) == 1)
                || ((Math.abs(x - kingX) == 1) && (Math.abs(y - kingY) == 1));
    }

}

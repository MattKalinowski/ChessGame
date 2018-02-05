package Application.Game;

import Application.ChessPieces.Chessman;
import Application.Chessboard.Position;

import static Application.Chessboard.Board.BOARD;

class MateEngine {

    private Game game;
    private boolean checkmateStatus;

    MateEngine(Game game) {
        this.game = game;
        this.checkmateStatus = true;
    }

    boolean getCheckmateStatus() {
        checkPossibleCombinations();
        return checkmateStatus;
    }

    private void checkPossibleCombinations() {
        outerloop:
        for (Position[] i : BOARD.getInstance()) {
            for (Position j : i) {
                if (j.getChessman().getTeam() == game.getCurrentPlayer()) {
                    tryPossibleMoves(j.getChessman());
                    if (!checkmateStatus) break outerloop;
                }
            }
        }
    }

    private void tryPossibleMoves(Chessman chessman) {
        int y = 0;
        for (Position[] i : BOARD.getInstance()) {
            int x = 0;
            for (Position j : i) {
                Position oldPosition = chessman.getPosition();
                Chessman newPositionChessman = BOARD.getPosition(x,y).getChessman();
                chessman.move(x,y);
                Position newPosition = chessman.getPosition();
                if (oldPosition != newPosition) {
                    if (!game.inCheck()) {
                        checkmateStatus = false;
                    }
                    chessman.setPosition(oldPosition);
                    oldPosition.setChessman(chessman);
                    newPosition.setChessman(newPositionChessman);
                    newPositionChessman.setPosition(newPosition);
                }
                x++;
            }
            y++;
        }
    }

}

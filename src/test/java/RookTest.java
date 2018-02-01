import Application.ChessPieces.Pawn;
import Application.ChessPieces.Phantom;
import Application.ChessPieces.Rook;
import Application.ChessPieces.Team;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.Chessboard.Board.BOARD;
import static org.junit.Assert.assertEquals;

public class RookTest {

    @Test
    public void testHorizontalMovement() {
        Rook rook = new Rook(Team.WHITE);
        Position position = BOARD.getPosition(0,7);
        rook.setPosition(position);
        rook.move(3,7);
        BOARD.getPosition(3,7).setChessman(new Phantom());
        assertEquals("[3,7]", rook.getPosition().toString());
    }

    @Test
    public void testVerticalMovement() {
        Rook rook = new Rook(Team.WHITE);
        Position position = BOARD.getPosition(0,7);
        rook.setPosition(position);
        rook.move(0,4);
        assertEquals("[0,4]", rook.getPosition().toString());
    }


    @Test
    public void testMovementWithObstacle() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition(1,6);
        rook.setPosition(rookPosition);
        BOARD.getPosition(1,4).setChessman(new Pawn(Team.BLACK));
        rook.move(1,3);
        BOARD.getPosition(1,4).setChessman(new Phantom());
        assertEquals("[1,6]", rook.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition(2,6);
        rook.setPosition(rookPosition);
        BOARD.getPosition(2,4).setChessman(new Pawn(Team.BLACK));
        rook.move(2,4);
        assertEquals("[2,4]", rook.getPosition().toString());
    }

    @Test
    public void testMovementToAllyPosition() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition(3,6);
        rook.setPosition(rookPosition);
        BOARD.getPosition(3,4).setChessman(new Pawn(Team.WHITE));
        rook.move(3,4);
        BOARD.getPosition(3,4).setChessman(new Phantom());
        assertEquals("[3,6]", rook.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition(4,6);
        rook.setPosition(rookPosition);
        rook.move(4, -1);
        assertEquals("[4,6]", rook.getPosition().toString());
    }

}

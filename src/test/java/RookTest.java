import Application.ChessPieces.Rook;
import Application.ChessPieces.Team;
import Application.Chessboard.Board;
import Application.Chessboard.Ownership;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Ownership.BLACK;
import static Application.Chessboard.Ownership.NEUTRAL;
import static org.junit.Assert.assertEquals;

public class RookTest {

    @Test
    public void testHorizontalMovement() {
        Rook rook = new Rook(Team.WHITE);
        Position position = BOARD.getPosition('a', 1);
        rook.setPosition(position);
        rook.move('d', 1);
        BOARD.getPosition('d',1).setOwner(NEUTRAL);
        assertEquals("[d,1]", rook.getPosition().toString());
    }

    @Test
    public void testVerticalMovement() {
        Rook rook = new Rook(Team.WHITE);
        Position position = BOARD.getPosition('a', 1);
        rook.setPosition(position);
        rook.move('a', 4);
        assertEquals("[a,4]", rook.getPosition().toString());
    }

    @Test
    public void testMovementWhileCaptured() {
        Rook rook = new Rook(Team.WHITE);
        Position position = BOARD.getPosition('a', 1);
        rook.setPosition(position);
        rook.setCaptured();
        rook.move('d', 1);
        assertEquals("[a,1]", rook.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacle() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition('b', 2);
        rook.setPosition(rookPosition);
        BOARD.getPosition('b', 4).setOwner(BLACK);
        rook.move('b', 5);
        BOARD.getPosition('b', 4).setOwner(NEUTRAL);
        assertEquals("[b,2]", rook.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition('c', 2);
        rook.setPosition(rookPosition);
        BOARD.getPosition('c', 4).setOwner(BLACK);
        rook.move('c', 4);
        assertEquals("[c,4]", rook.getPosition().toString());
    }

    @Test
    public void testMovementToAllyPosition() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition('d', 2);
        rook.setPosition(rookPosition);
        BOARD.getPosition('d', 4).setOwner(Ownership.WHITE);
        rook.move('d', 4);
        BOARD.getPosition('d', 4).setOwner(Ownership.NEUTRAL);
        assertEquals("[d,2]", rook.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Rook rook = new Rook(Team.WHITE);
        Position rookPosition = BOARD.getPosition('e', 2);
        rook.setPosition(rookPosition);
        rook.move('e', 9);
        assertEquals("[e,2]", rook.getPosition().toString());
    }

}

import Application.ChessPieces.King;
import Application.Chessboard.Board;
import Application.Chessboard.Ownership;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.ChessPieces.Team.WHITE;
import static Application.Chessboard.Ownership.BLACK;
import static Application.Chessboard.Ownership.NEUTRAL;
import static org.junit.Assert.assertEquals;

public class KingTest {

    @Test
    public void testAdjacentMovement() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition('d', 4);
        king.setPosition(position);
        king.move('d', 5);
        assertEquals("[d,5]", king.getPosition().toString());
    }

    @Test
    public void testDiagonalMovement() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition('d', 4);
        king.setPosition(position);
        king.move('c', 5);
        assertEquals("[c,5]", king.getPosition().toString());
    }

    @Test
    public void testMovementWhileCaptured() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition('a', 8);
        king.setPosition(position);
        king.setCaptured();
        king.move('a', 7);
        assertEquals("[a,8]", king.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition('h', 4);
        king.setPosition(position);
        king.move('i', 4);
        assertEquals("[h,4]", king.getPosition().toString());
    }

    @Test
    public void testMovementOutOfRange() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition('d', 4);
        king.setPosition(position);
        king.move('d', 6);
        assertEquals("[d,4]", king.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() { // <-- ten
        King king = new King(WHITE);
        Position kingPosition = Board.BOARD.getPosition('g', 4);
        king.setPosition(kingPosition);
        Board.BOARD.getPosition('g', 5).setOwner(BLACK);
        king.move('g', 5);
        Board.BOARD.getPosition('g', 5).setOwner(NEUTRAL);
        assertEquals("[g,5]", king.getPosition().toString());
    }

    @Test
    public void testMovementBlockedByAlly() { // <-- i ten
        King king = new King(WHITE);
        Position kingPosition = Board.BOARD.getPosition('f', 4);
        king.setPosition(kingPosition);
        Board.BOARD.getPosition('f',5).setOwner(Ownership.WHITE);
        king.move('f', 5);
        Board.BOARD.getPosition('f',5).setOwner(Ownership.NEUTRAL);
        assertEquals("[f,4]", king.getPosition().toString());
    }

}

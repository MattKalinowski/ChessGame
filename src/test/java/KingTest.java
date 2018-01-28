import Application.ChessPieces.King;
import Application.ChessPieces.Pawn;
import Application.ChessPieces.Phantom;
import Application.ChessPieces.Team;
import Application.Chessboard.Board;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.ChessPieces.Team.WHITE;
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
        Board.BOARD.getPosition('g', 5).setChessman(new Pawn(Team.BLACK));
        king.move('g', 5);
        Board.BOARD.getPosition('g', 5).setChessman(new Phantom());
        assertEquals("[g,5]", king.getPosition().toString());
    }

    @Test
    public void testMovementBlockedByAlly() { // <-- i ten
        King king = new King(WHITE);
        Position kingPosition = Board.BOARD.getPosition('f', 4);
        king.setPosition(kingPosition);
        Board.BOARD.getPosition('f',5).setChessman(new Pawn(Team.WHITE));
        king.move('f', 5);
        Board.BOARD.getPosition('f',5).setChessman(new Phantom());
        assertEquals("[f,4]", king.getPosition().toString());
    }

}

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
        Position position = Board.BOARD.getPosition(3, 4);
        king.setPosition(position);
        king.move(3,3);
        assertEquals("[3,3]", king.getPosition().toString());
    }

    @Test
    public void testDiagonalMovement() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition(3,4);
        king.setPosition(position);
        king.move(2,3);
        assertEquals("[2,3]", king.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition(7, 4);
        king.setPosition(position);
        king.move(8, 4);
        assertEquals("[7,4]", king.getPosition().toString());
    }

    @Test
    public void testMovementOutOfRange() {
        King king = new King(WHITE);
        Position position = Board.BOARD.getPosition(3, 4);
        king.setPosition(position);
        king.move(3,2);
        assertEquals("[3,4]", king.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        King king = new King(WHITE);
        Position kingPosition = Board.BOARD.getPosition(6, 4);
        king.setPosition(kingPosition);
        Board.BOARD.getPosition(6,3).setChessman(new Pawn(Team.BLACK));
        king.move(6,3);
        Board.BOARD.getPosition(6,3).setChessman(new Phantom());
        assertEquals("[6,3]", king.getPosition().toString());
    }

    @Test
    public void testMovementBlockedByAlly() {
        King king = new King(WHITE);
        Position kingPosition = Board.BOARD.getPosition(5, 4);
        king.setPosition(kingPosition);
        Board.BOARD.getPosition(5,3).setChessman(new Pawn(Team.WHITE));
        king.move(5,3);
        Board.BOARD.getPosition(5,3).setChessman(new Phantom());
        assertEquals("[5,4]", king.getPosition().toString());
    }

}

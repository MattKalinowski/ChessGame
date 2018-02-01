import Application.ChessPieces.Bishop;
import Application.ChessPieces.Pawn;
import Application.ChessPieces.Phantom;
import Application.ChessPieces.Team;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.ChessPieces.Team.WHITE;
import static Application.Chessboard.Board.BOARD;
import static org.junit.Assert.assertEquals;

public class BishopTest {

    @Test
    public void testMovementOne() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition(5, 2);
        bishop.setPosition(position);
        bishop.move(3, 0);
        assertEquals("[3,0]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementTwo() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition(5, 2);
        bishop.setPosition(position);
        bishop.move(7, 0);
        assertEquals("[7,0]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacle() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition(7, 1);
        bishop.setPosition(position);
        BOARD.getPosition(5,3).setChessman(new Pawn(WHITE));
        bishop.move(4, 4);
        BOARD.getPosition(5,3).setChessman(new Phantom());
        assertEquals("[7,1]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        Bishop bishop = new Bishop(Team.BLACK);
        Position position = BOARD.getPosition(7, 2);
        bishop.setPosition(position);
        bishop.move(6,3);
        assertEquals("[6,3]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementToAllyPosition() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition(2,0);
        bishop.setPosition(position);
        BOARD.getPosition(3,1).setChessman(new Pawn(WHITE));
        bishop.move(3,1);
        BOARD.getPosition(3,1).setChessman(new Phantom());
        assertEquals("[2,0]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition(2,0);
        bishop.setPosition(position);
        bishop.move(0, -2);
        assertEquals("[2,0]", bishop.getPosition().toString());
    }

}

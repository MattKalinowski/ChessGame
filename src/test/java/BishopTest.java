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
        Position position = BOARD.getPosition('f', 6);
        bishop.setPosition(position);
        bishop.move('d', 8);
        assertEquals("[d,8]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementTwo() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition('f', 6);
        bishop.setPosition(position);
        bishop.move('h', 8);
        assertEquals("[h,8]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacle() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition('h', 7);
        bishop.setPosition(position);
        bishop.move('e', 4);
        assertEquals("[h,7]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        Bishop bishop = new Bishop(Team.BLACK);
        Position position = BOARD.getPosition('h', 6);
        bishop.setPosition(position);
        bishop.move('g', 5);
        assertEquals("[g,5]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementToAllyPosition() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition('c', 8);
        bishop.setPosition(position);
        BOARD.getPosition('d',7).setChessman(new Pawn(WHITE));
        bishop.move('d', 7);
        BOARD.getPosition('d',7).setChessman(new Phantom());
        assertEquals("[c,8]", bishop.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Bishop bishop = new Bishop(WHITE);
        Position position = BOARD.getPosition('c', 8);
        bishop.setPosition(position);
        bishop.move('a', 10);
        assertEquals("[c,8]", bishop.getPosition().toString());
    }

}

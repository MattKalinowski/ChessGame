import Application.ChessPieces.Knight;
import Application.ChessPieces.Pawn;
import Application.ChessPieces.Phantom;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.ChessPieces.Team.BLACK;
import static Application.ChessPieces.Team.WHITE;
import static Application.Chessboard.Board.BOARD;
import static org.junit.Assert.assertEquals;

public class KnightTest {

    @Test
    public void testFreeMovementUp() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition(0,7);
        knight.setPosition(position);
        knight.move(1,5);
        BOARD.getPosition(1,5).setChessman(new Phantom());
        assertEquals("[1,5]", knight.getPosition().toString());
    }

    @Test
    public void testFreeMovementRight() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition(0,7);
        knight.setPosition(position);
        knight.move(2,6);
        assertEquals("[2,6]", knight.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition(0,7);
        knight.setPosition(position);
        knight.move(2, 8);
        assertEquals("[0,7]", knight.getPosition().toString());
    }

    @Test
    public void testMovementOutOfRange() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition(0,7);
        knight.setPosition(position);
        knight.move(1,6);
        assertEquals("[0,7]", knight.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition(4,7);
        knight.setPosition(position);
        BOARD.getPosition(5,5).setChessman(new Pawn(BLACK));
        knight.move(5,5);
        BOARD.getPosition(5,5).setChessman(new Phantom());
        assertEquals("[5,5]", knight.getPosition().toString());
    }

    @Test
    public void testMovementBlockedByAlly() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition(4,3);
        knight.setPosition(position);
        BOARD.getPosition(3,1).setChessman(new Pawn(WHITE));
        knight.move(3,1);
        BOARD.getPosition(3,1).setChessman(new Phantom());
        assertEquals("[4,3]", knight.getPosition().toString());
    }

}

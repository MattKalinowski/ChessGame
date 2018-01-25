import Application.ChessPieces.Knight;
import Application.ChessPieces.Team;
import Application.Chessboard.Board;
import Application.Chessboard.Ownership;
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
        Position position = BOARD.getPosition('a', 1);
        knight.setPosition(position);
        knight.move('b', 3);
        BOARD.getPosition('b',3).setOwner(Ownership.NEUTRAL);
        assertEquals("[b,3]", knight.getPosition().toString());
    }

    @Test
    public void testFreeMovementRight() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('a', 1);
        knight.setPosition(position);
        knight.move('c', 2);
        assertEquals("[c,2]", knight.getPosition().toString());
    }

    @Test
    public void testMovementWhileCaptured() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('a', 1);
        knight.setPosition(position);
        knight.setCaptured();
        knight.move('b', 3);
        assertEquals("[a,1]", knight.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('a', 1);
        knight.setPosition(position);
        knight.move('c', 0);
        assertEquals("[a,1]", knight.getPosition().toString());
    }

    @Test
    public void testMovementOutOfRange() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('a', 1);
        knight.setPosition(position);
        knight.move('b', 2);
        assertEquals("[a,1]", knight.getPosition().toString());
    }

    @Test
    public void testMovementToCaptureEnemy() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('e', 1);
        knight.setPosition(position);
        BOARD.getPosition('f',3).setOwner(Ownership.BLACK);
        knight.move('f', 3);
        BOARD.getPosition('f',3).setOwner(Ownership.NEUTRAL);
        assertEquals("[f,3]", knight.getPosition().toString());
    }

    @Test
    public void testMovementBlockedByAlly() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('e', 5);
        knight.setPosition(position);
        BOARD.getPosition('d',7).setOwner(Ownership.WHITE);
        knight.move('d', 7);
        BOARD.getPosition('d',7).setOwner(Ownership.NEUTRAL);
        assertEquals("[e,5]", knight.getPosition().toString());
    }

}

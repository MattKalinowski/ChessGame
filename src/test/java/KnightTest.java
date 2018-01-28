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
        Position position = BOARD.getPosition('a', 1);
        knight.setPosition(position);
        knight.move('b', 3);
        BOARD.getPosition('b',3).setChessman(new Phantom());
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
        BOARD.getPosition('f',3).setChessman(new Pawn(BLACK));
        knight.move('f', 3);
        BOARD.getPosition('f',3).setChessman(new Phantom());
        assertEquals("[f,3]", knight.getPosition().toString());
    }

    @Test
    public void testMovementBlockedByAlly() {
        Knight knight = new Knight(WHITE);
        Position position = BOARD.getPosition('e', 5);
        knight.setPosition(position);
        BOARD.getPosition('d',7).setChessman(new Pawn(WHITE));
        knight.move('d', 7);
        BOARD.getPosition('d',7).setChessman(new Phantom());
        assertEquals("[e,5]", knight.getPosition().toString());
    }

}

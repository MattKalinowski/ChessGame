import Application.ChessPieces.Pawn;
import Application.ChessPieces.Phantom;
import Application.ChessPieces.Queen;
import Application.Chessboard.Position;
import org.junit.Test;

import static Application.ChessPieces.Team.BLACK;
import static Application.ChessPieces.Team.WHITE;
import static Application.Chessboard.Board.BOARD;
import static org.junit.Assert.assertEquals;

public class QueenTest {

    @Test
    public void testAdjacentMovementOne() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 2);
        queen.setPosition(position);
        queen.move('e', 1);
        BOARD.getPosition('e',1).setChessman(new Phantom());
        assertEquals("[e,1]", queen.getPosition().toString());
    }

    @Test
    public void testAdjacentMovementTwo() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 2);
        queen.setPosition(position);
        queen.move('d', 2);
        BOARD.getPosition('d',2).setChessman(new Phantom());
        assertEquals("[d,2]", queen.getPosition().toString());
    }

    @Test
    public void testDiagonalMovementOne() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 2);
        queen.setPosition(position);
        queen.move('d', 1);
        BOARD.getPosition('d',1).setChessman(new Phantom());
        assertEquals("[d,1]", queen.getPosition().toString());
    }

    @Test
    public void testDiagonalMovementTwo() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 2);
        queen.setPosition(position);
        queen.move('d', 3);
        BOARD.getPosition('d',3).setChessman(new Phantom());
        assertEquals("[d,3]", queen.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacleOne() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 3);
        queen.setPosition(position);
        BOARD.getPosition('d',4).setChessman(new Pawn(WHITE));
        queen.move('c', 5);
        BOARD.getPosition('d',4).setChessman(new Phantom());
        assertEquals("[e,3]", queen.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacleTwo() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 3);
        queen.setPosition(position);
        BOARD.getPosition('e',4).setChessman(new Pawn(WHITE));
        queen.move('e', 5);
        BOARD.getPosition('e',4).setChessman(new Phantom());
        assertEquals("[e,3]", queen.getPosition().toString());
    }

    @Test
    public void testMovementToBeatEnemy() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 3);
        queen.setPosition(position);
        BOARD.getPosition('e',4).setChessman(new Pawn(BLACK));
        queen.move('e', 4);
        BOARD.getPosition('e',4).setChessman(new Phantom());
        assertEquals("[e,4]", queen.getPosition().toString());
    }

    @Test
    public void testMovementToAllyPosition() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 3);
        queen.setPosition(position);
        BOARD.getPosition('e',4).setChessman(new Pawn(WHITE));
        queen.move('e', 4);
        BOARD.getPosition('e',4).setChessman(new Phantom());
        assertEquals("[e,3]", queen.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition('e', 2);
        queen.setPosition(position);
        queen.move('e', 0);
        assertEquals("[e,2]", queen.getPosition().toString());
    }

}

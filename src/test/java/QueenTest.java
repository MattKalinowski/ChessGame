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
        Position position = BOARD.getPosition(4,6);
        queen.setPosition(position);
        queen.move(4,7);
        BOARD.getPosition(4,7).setChessman(new Phantom());
        assertEquals("[4,7]", queen.getPosition().toString());
    }

    @Test
    public void testAdjacentMovementTwo() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,6);
        queen.setPosition(position);
        queen.move(3,6);
        BOARD.getPosition(3,6).setChessman(new Phantom());
        assertEquals("[3,6]", queen.getPosition().toString());
    }

    @Test
    public void testDiagonalMovementOne() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,6);
        queen.setPosition(position);
        queen.move(3,7);
        BOARD.getPosition(3,7).setChessman(new Phantom());
        assertEquals("[3,7]", queen.getPosition().toString());
    }

    @Test
    public void testDiagonalMovementTwo() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,6);
        queen.setPosition(position);
        queen.move(3,5);
        BOARD.getPosition(3,5).setChessman(new Phantom());
        assertEquals("[3,5]", queen.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacleOne() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,5);
        queen.setPosition(position);
        BOARD.getPosition(3,4).setChessman(new Pawn(WHITE));
        queen.move(2,3);
        BOARD.getPosition(3,4).setChessman(new Phantom());
        assertEquals("[4,5]", queen.getPosition().toString());
    }

    @Test
    public void testMovementWithObstacleTwo() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,5);
        queen.setPosition(position);
        BOARD.getPosition(4,4).setChessman(new Pawn(WHITE));
        queen.move(4,3);
        BOARD.getPosition(4,4).setChessman(new Phantom());
        assertEquals("[4,5]", queen.getPosition().toString());
    }

    @Test
    public void testMovementToBeatEnemy() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,5);
        queen.setPosition(position);
        BOARD.getPosition(4,4).setChessman(new Pawn(BLACK));
        queen.move(4,4);
        BOARD.getPosition(4,4).setChessman(new Phantom());
        assertEquals("[4,4]", queen.getPosition().toString());
    }

    @Test
    public void testMovementToAllyPosition() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,5);
        queen.setPosition(position);
        BOARD.getPosition(4,4).setChessman(new Pawn(WHITE));
        queen.move(4,4);
        BOARD.getPosition(4,4).setChessman(new Phantom());
        assertEquals("[4,5]", queen.getPosition().toString());
    }

    @Test
    public void testMovementOutOfBounds() {
        Queen queen = new Queen(WHITE);
        Position position = BOARD.getPosition(4,6);
        queen.setPosition(position);
        queen.move(4, -1);
        assertEquals("[4,6]", queen.getPosition().toString());
    }

}

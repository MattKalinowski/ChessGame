
import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Position;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    @Test
    public void testSingleForwardMovementWithNoObstacles() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position position = Board.BOARD.getPosition('a', 7);
        pawn.setPosition(position);
        pawn.move('a', 6);
        assertEquals("[a,6]", pawn.getPosition().toString());
    }

    @Test
    public void testSingleForwardMovementWithObstacle() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition('d', 2);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition('d', 3).setChessman(new Pawn(Team.WHITE));
        whitePawn.move('d', 3);
        Board.BOARD.getPosition('d', 3).setChessman(new Phantom());
        assertEquals("[d,2]", whitePawn.getPosition().toString());
    }

    @Test
    public void testDoubleForwardMovementWithNoObstacles() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position position = Board.BOARD.getPosition('b', 2);
        pawn.setPosition(position);
        pawn.move('b', 4);
        assertEquals("[b,4]", pawn.getPosition().toString());
    }

    @Test
    public void testDoubleForwardMovementWithObstacle() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition('b', 2);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition('b', 3).setChessman(new Pawn(Team.WHITE));
        whitePawn.move('b', 4);
        Board.BOARD.getPosition('b', 3).setChessman(new Phantom());
        assertEquals("[b,2]", whitePawn.getPosition().toString());
    }

    @Test
    public void promotionToQueenTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position position = Board.BOARD.getPosition('a', 2);
        Position position2 = Board.BOARD.getPosition('a', 7);
        pawn.setPosition(position);
        pawn.move('a', 3);
        pawn.setPosition(position2);
        pawn.move('a',8);
        Board.BOARD.getPosition('a',3).setChessman(new Phantom());
        assertEquals("[WQ]", Board.BOARD.getPosition('a',8).getChessman().toString());
    }

    @Test
    public void testCrossMovementLeftToCaptureEnemy() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition('d', 2);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition('c', 3).setChessman(new Pawn(Team.BLACK));
        whitePawn.move('c', 3);
        Board.BOARD.getPosition('c', 3).setChessman(new Phantom());
        assertEquals("[c,3]", whitePawn.getPosition().toString());
    }

    @Test
    public void testCrossMovementRightToBeatEnemy() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition('d', 2);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition('e', 3).setChessman(new Pawn(Team.BLACK));
        whitePawn.move('e', 3);
        Board.BOARD.getPosition('e', 3).setChessman(new Phantom());
        assertEquals("[e,3]", whitePawn.getPosition().toString());
    }

    @Test
    public void testCrossMovementLeftBlockedByAlly() {
        Pawn whitePawn1 = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition('d', 2);
        whitePawn1.setPosition(positionWhite);
        Board.BOARD.getPosition('c', 3).setChessman(new Pawn(Team.WHITE));
        whitePawn1.move('c', 3);
        Board.BOARD.getPosition('c', 3).setChessman(new Phantom());
        assertEquals("[d,2]", whitePawn1.getPosition().toString());
    }

    @Test
    public void testCrossMovementRightBlockedByAlly() {
        Pawn whitePawn1 = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition('d', 2);
        whitePawn1.setPosition(positionWhite);
        Board.BOARD.getPosition('e', 3).setChessman(new Pawn(Team.WHITE));
        whitePawn1.move('e', 3);
        Board.BOARD.getPosition('e', 3).setChessman(new Phantom());
        assertEquals("[d,2]", whitePawn1.getPosition().toString());
    }

}

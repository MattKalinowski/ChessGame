
import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Position;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    @Test
    public void testSingleForwardMovementWithNoObstacles() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position position = Board.BOARD.getPosition(0,1);
        pawn.setPosition(position);
        pawn.move(0,2);
        assertEquals("[0,2]", pawn.getPosition().toString());
    }

    @Test
    public void testSingleForwardMovementWithObstacle() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition(3,6);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition(3,5).setChessman(new Pawn(Team.WHITE));
        whitePawn.move(3,5);
        Board.BOARD.getPosition(3,5).setChessman(new Phantom());
        assertEquals("[3,6]", whitePawn.getPosition().toString());
    }

    @Test
    public void testDoubleForwardMovementWithNoObstacles() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position position = Board.BOARD.getPosition(1,6);
        pawn.setPosition(position);
        pawn.move(1,4);
        assertEquals("[1,4]", pawn.getPosition().toString());
    }

    @Test
    public void testDoubleForwardMovementWithObstacle() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition(1,6);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition(1,5).setChessman(new Pawn(Team.WHITE));
        whitePawn.move(1,4);
        Board.BOARD.getPosition(1,5).setChessman(new Phantom());
        assertEquals("[1,6]", whitePawn.getPosition().toString());
    }

    @Test
    public void promotionToQueenTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position position = Board.BOARD.getPosition(0,6);
        Position position2 = Board.BOARD.getPosition(0,1);
        pawn.setPosition(position);
        pawn.move(0,5);
        pawn.setPosition(position2);
        pawn.move(0,0);
        Board.BOARD.getPosition(0,5).setChessman(new Phantom());
        assertEquals("[WQ]", Board.BOARD.getPosition(0,0).getChessman().toString());
    }

    @Test
    public void testCrossMovementLeftToCaptureEnemy() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition(3,6);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition(2,5).setChessman(new Pawn(Team.BLACK));
        whitePawn.move(2,5);
        Board.BOARD.getPosition(2,5).setChessman(new Phantom());
        assertEquals("[2,5]", whitePawn.getPosition().toString());
    }

    @Test
    public void testCrossMovementRightToBeatEnemy() {
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition(3,6);
        whitePawn.setPosition(positionWhite);
        Board.BOARD.getPosition(4,5).setChessman(new Pawn(Team.BLACK));
        whitePawn.move(4,5);
        Board.BOARD.getPosition(4,5).setChessman(new Phantom());
        assertEquals("[4,5]", whitePawn.getPosition().toString());
    }

    @Test
    public void testCrossMovementLeftBlockedByAlly() {
        Pawn whitePawn1 = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition(3,6);
        whitePawn1.setPosition(positionWhite);
        Board.BOARD.getPosition(2,5).setChessman(new Pawn(Team.WHITE));
        whitePawn1.move(2,5);
        Board.BOARD.getPosition(2,5).setChessman(new Phantom());
        assertEquals("[3,6]", whitePawn1.getPosition().toString());
    }

    @Test
    public void testCrossMovementRightBlockedByAlly() {
        Pawn whitePawn1 = new Pawn(Team.WHITE);
        Position positionWhite = Board.BOARD.getPosition(3,6);
        whitePawn1.setPosition(positionWhite);
        Board.BOARD.getPosition(4,5).setChessman(new Pawn(Team.WHITE));
        whitePawn1.move(4,5);
        Board.BOARD.getPosition(4,5).setChessman(new Phantom());
        assertEquals("[3,6]", whitePawn1.getPosition().toString());
    }

}

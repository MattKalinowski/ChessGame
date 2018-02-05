import Application.ChessPieces.Chessman;
import Application.ChessPieces.Pawn;
import Application.ChessPieces.Phantom;
import Application.ChessPieces.Team;
import Application.Chessboard.Board;
import Application.Chessboard.Position;
import org.junit.Assert;
import org.junit.Test;

import static Application.Chessboard.Utils.*;

public class UtilsTest {

    @Test
    public void inBoundsMethodTest() {
        Assert.assertFalse(inBounds(1,8));
    }

    @Test
    public void isNotAllyMethodTest() {
        Position position = Board.BOARD.getPosition(0,0);
        position.setChessman(new Pawn(Team.WHITE));
        Assert.assertFalse(isNotAlly(0,0,Team.WHITE));
        position.setChessman(new Phantom());
    }

    @Test
    public void isPermeableDiagonallyMethodVectorOneTest() {
        Position position = Board.BOARD.getPosition(3,3);
        position.setChessman(new Pawn(Team.WHITE));
        Assert.assertFalse(isPermeableDiagonally(1,1,Board.BOARD.getPosition(5,5)));
        position.setChessman(new Phantom());
    }

    @Test
    public void isPermeableDiagonallyMethodVectorTwoTest() {
        Position position = Board.BOARD.getPosition(3,3);
        position.setChessman(new Pawn(Team.WHITE));
        Assert.assertFalse(isPermeableDiagonally(5,1,Board.BOARD.getPosition(1,5)));
        position.setChessman(new Phantom());
    }

    @Test
    public void isPermeableAdjacentlyMethodXAxisTest() {
        Position position = Board.BOARD.getPosition(3,3);
        position.setChessman(new Pawn(Team.WHITE));
        Assert.assertFalse(isPermeableAdjacently(1,3,Board.BOARD.getPosition(5,3)));
        position.setChessman(new Phantom());
    }

    @Test
    public void isPermeableAdjacentlyMethodYAxisTest() {
        Position position = Board.BOARD.getPosition(3,3);
        position.setChessman(new Pawn(Team.WHITE));
        Assert.assertFalse(isPermeableAdjacently(3,1,Board.BOARD.getPosition(3,5)));
        position.setChessman(new Phantom());
    }

    @Test
    public void relocateMethodTest() {
        Position currentPosition = Board.BOARD.getPosition(3, 3);
        Position targetPosition = Board.BOARD.getPosition(4, 3);
        Chessman chessman = new Pawn(Team.WHITE);
        chessman.setPosition(currentPosition);
        relocate(chessman, currentPosition, targetPosition);
        Assert.assertTrue(currentPosition.getChessman() instanceof Phantom);
        Assert.assertEquals("[WP]",targetPosition.getChessman().toString());
    }
}

package Application.Game;

import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Ownership;
import Application.Chessboard.Position;

import java.util.ArrayList;
import java.util.List;

import static Application.Chessboard.Board.showBoard;
import static Application.Chessboard.Utils.captureEnemy;

public class Game {

    private List<Chessman> whiteTeam = new ArrayList<Chessman>();
    private List<Chessman> blackTeam = new ArrayList<Chessman>();
    private List<Chessman> capturedWhites = new ArrayList<Chessman>();
    private List<Chessman> capturedBlacks = new ArrayList<Chessman>();

    public void play() {

        setupGame(whiteTeam, blackTeam);

        System.out.println();


        System.out.println();

        showBoard();
    }

    private static void makeMove() { // Finish this method and setup the whole game play

    }

    private static void setupGame(List<Chessman> whiteTeam, List<Chessman> blackTeam) {
        newTeam(whiteTeam, Team.WHITE);
        newTeam(blackTeam, Team.BLACK);
        arrangePieces(whiteTeam, blackTeam, Board.BOARD.getInstance());
    }

    private static void newTeam(List<Chessman> set, Team team) {
        for (int i = 0; i < 8; i++) {
            set.add(new Pawn(team));
        }
        set.add(new Rook(team));
        set.add(new Knight(team));
        set.add(new Bishop(team));
        set.add(new Queen(team));
        set.add(new King(team));
        set.add(new Bishop(team));
        set.add(new Knight(team));
        set.add(new Rook(team));
    }

    private static void arrangePieces(List<Chessman> team1, List<Chessman> team2, Position[][] board) {
        int row = 0;
        for (Position[] i : board) {
            int column = 0;
            for (Position j : i) {
                int pawnIndex = column;
                int nonPawnIndex = column + 8;
                if (row == 0) {
                    team1.get(nonPawnIndex).setPosition(j);
                    j.setOwner(Ownership.WHITE);
                }
                if (row == 1) {
                    team1.get(pawnIndex).setPosition(j);
                    j.setOwner(Ownership.WHITE);
                }
                if (row == 6) {
                    team2.get(pawnIndex).setPosition(j);
                    j.setOwner(Ownership.BLACK);
                }
                if (row == 7) {
                    team2.get(nonPawnIndex).setPosition(j);
                    j.setOwner(Ownership.BLACK);
                }
                column++;
            }
            row++;
        }

    }

    private static void previewPiecePositions(List<Chessman> team1, List<Chessman> team2) {
        for (Chessman c : team1) {
            System.out.println(c + " is on position " + c.getPosition());
        }
        System.out.println();
        for (Chessman c : team2) {
            System.out.println(c + " is on position " + c.getPosition());
        }
    }

    private static void showCapturedPieces(List<Chessman> captured) {
        for (Chessman c : captured) {
            System.out.print(c + " ");
        }
    }

}

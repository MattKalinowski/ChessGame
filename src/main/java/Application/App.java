package Application;


import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Ownership;
import Application.Chessboard.Position;

import java.util.ArrayList;
import java.util.List;

import static Application.Chessboard.Utils.captureEnemy;

public class App {
    public static void main(String[] args) {

        List<Chessman> whiteTeam = new ArrayList<Chessman>();
        newTeam(whiteTeam, Team.WHITE);
        List<Chessman> blackTeam = new ArrayList<Chessman>();
        newTeam(blackTeam, Team.BLACK);

        arrangePieces(whiteTeam, blackTeam, Board.BOARD.getInstance());

        for (Chessman c : whiteTeam) {
            System.out.println( c + " is on position " + c.getPosition());
        }
        System.out.println();
        for (Chessman c : blackTeam) {
            System.out.println( c + " is on position " + c.getPosition());
        }

        List<Chessman> capturedWhites = new ArrayList<Chessman>();
        List<Chessman> capturedBlacks = new ArrayList<Chessman>();

        System.out.println();



        /*
          TEST CASES******************************************************************************
        */

        System.out.println();
        System.out.println(whiteTeam.get(14) + " is on position " + whiteTeam.get(14).getPosition());
        Board.BOARD.getPosition('d',7).setOwner(Ownership.NEUTRAL);
        whiteTeam.get(14).move('d',3);

        System.out.println(whiteTeam.get(14) + " moved to position " + whiteTeam.get(14).getPosition());



        System.out.println();
        System.out.println(blackTeam.get(2) + " is on position " + blackTeam.get(2).getPosition());
        blackTeam.get(2).move('d',3);
        captureEnemy(whiteTeam, 14, capturedWhites); // how to put this method inside an object?

        System.out.println(blackTeam.get(2) + " moved to position " + blackTeam.get(2).getPosition());

        // captured chessman is still reachable
        System.out.println();
        System.out.println(whiteTeam.get(14) + " is on position " + whiteTeam.get(14).getPosition());
        whiteTeam.get(14).move('c',3);

        System.out.println(whiteTeam.get(14) + " moved to position " + whiteTeam.get(14).getPosition());

        System.out.println();

        System.out.println("Captured whites: ");

        for (Chessman c : capturedWhites) {
            System.out.print(c + " ");
        }

        //******************************************************************************************




        System.out.println();

        showBoard();

    }

    private static void showBoard() {
        for (Position[] i : Board.BOARD.getInstance()) {
            for (Position j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    private static void newTeam(List<Chessman> set, Team team) {
        for (int i = 0; i < 8; i++) {
            set.add(new Pawn(team));
        }
        for (int i = 0; i < 2; i++) {
            set.add(new Rook(team));
            set.add(new Knight(team));
            set.add(new Bishop(team));
        }
        set.add(new Queen(team));
        set.add(new King(team));
    }

    private static void arrangePieces(List<Chessman> team1, List<Chessman> team2, Position[][] board) {
        int row = 0;
        int column;
        for (Position[] v : board) {
            column = 0;
            for (Position h : v) {
                if (row == 0) {
                    if (column == 0)
                        team1.get(8).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 1)
                        team1.get(9).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 2)
                        team1.get(10).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 3)
                        team1.get(14).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 4)
                        team1.get(15).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 5)
                        team1.get(13).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 6)
                        team1.get(12).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                    if (column == 7)
                        team1.get(11).setPosition(h);
                        h.setOwner(Ownership.WHITE);
                }
                if (row == 1) {
                    team1.get(column).setPosition(h);
                    h.setOwner(Ownership.WHITE);
                }
                if (row == 6) {
                    team2.get(column).setPosition(h);
                    h.setOwner(Ownership.BLACK);
                }
                if (row == 7) {
                    if (column == 0)
                        team2.get(8).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 1)
                        team2.get(9).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 2)
                        team2.get(10).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 3)
                        team2.get(14).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 4)
                        team2.get(15).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 5)
                        team2.get(13).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 6)
                        team2.get(12).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                    if (column == 7)
                        team2.get(11).setPosition(h);
                        h.setOwner(Ownership.BLACK);
                }
                column++;
            }
            row++;
        }
    }

}

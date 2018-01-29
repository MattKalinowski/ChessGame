package Application.Game;

import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorCompletionService;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Board.showBoard;

public class Game {

    private Team currentPlayer = Team.WHITE;
    private List<Chessman> whiteTeam = new ArrayList<Chessman>();
    private List<Chessman> blackTeam = new ArrayList<Chessman>();
    private List<Chessman> capturedWhites = new ArrayList<Chessman>();
    private List<Chessman> capturedBlacks = new ArrayList<Chessman>();

    public void play() {

        setupGame(whiteTeam, blackTeam);
        while (true) {
            showBoard();
            System.out.println("\nCurrent player: " + currentPlayer);
            makeMove();
            setCurrentPlayer();
        }
    }

    private void setCurrentPlayer() {
        switch (currentPlayer) {
            case BLACK: currentPlayer = Team.WHITE;
                break;
            case WHITE: currentPlayer = Team.BLACK;
                break;
        }
    }

    private void makeMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Move(xy/xy): ");
        String move = sc.next();
        char x1 = move.charAt(0);
        int y1 = Integer.parseInt(move.substring(1,2));
        char x2 = move.charAt(3);
        int y2 = Integer.parseInt(move.substring(4));
        executeMove(x1, y1, x2, y2);

    }

    private void executeMove(char fromX, int fromY, char toX, int toY) {
        BOARD.getPosition(fromX,fromY).getChessman().move(toX,toY);
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
                    team2.get(nonPawnIndex).setPosition(j);
                    j.setChessman(team2.get(nonPawnIndex));
                }
                if (row == 1) {
                    team2.get(pawnIndex).setPosition(j);
                    j.setChessman(team2.get(pawnIndex));
                }
                if (row == 6) {
                    team1.get(pawnIndex).setPosition(j);
                    j.setChessman(team1.get(pawnIndex));
                }
                if (row == 7) {
                    team1.get(nonPawnIndex).setPosition(j);
                    j.setChessman(team1.get(nonPawnIndex));
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

}

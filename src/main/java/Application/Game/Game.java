package Application.Game;

import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Board.showBoard;
import static Application.Chessboard.Utils.enemyTeam;

public class Game {

    private List<Chessman> whiteTeam = new ArrayList<Chessman>();
    private List<Chessman> blackTeam = new ArrayList<Chessman>();
    private Team currentPlayer = Team.WHITE;

    public void play() {
        setupGame(whiteTeam, blackTeam);
        while (true) {
            showBoard();
            log("\nCurrent player: " + currentPlayer);
            if (inCheck()) {
                if (isMate()) {
                    log("Checkmate! Player " + enemyTeam(currentPlayer) + " wins.");
                    break;
                }
                log("Check!");
            }
            if (isStalemate()) {
                log("Stalemate! The game ends as a draw.");
                break;
            }
            makeMove();
        }
    }

    private void makeMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Move(xy/xy): ");
        String move = sc.next();
        if (move.matches("[a-h][1-8]/[a-h][1-8]")) {
            int x1, y1, x2, y2;
            x1 = move.charAt(0) - 97;
            y1 = Math.abs(Integer.parseInt(move.substring(1, 2)) - 8);
            x2 = move.charAt(3) - 97;
            y2 = Math.abs(Integer.parseInt(move.substring(4)) - 8);
            if (currentPlayer == BOARD.getPosition(x1,y1).getChessman().getTeam())
            executeMove(x1, y1, x2, y2);
        } else {
            log("Command should match a pattern xy/xy, based on coordinates on the chessboard.");
        }
    }

    private void executeMove(int fromX, int fromY, int toX, int toY) {
        Chessman chessman = BOARD.getPosition(fromX,fromY).getChessman();
        int previousX = chessman.getPosition().getX();
        int previousY = chessman.getPosition().getY();
        Chessman targetChessman = BOARD.getPosition(toX,toY).getChessman();
        chessman.move(toX,toY);
        if (!inCheck() && !(chessman.getPosition().getX() == previousX && chessman.getPosition().getY() == previousY)) {
            switchCurrentPlayer();
        } else {
            Position currentField = BOARD.getPosition(fromX,fromY);
            Position targetField = BOARD.getPosition(toX,toY);
            chessman.setPosition(currentField);
            currentField.setChessman(chessman);
            targetField.setChessman(targetChessman);
            targetField.getChessman().setPosition(targetField);
        }
    }

    boolean inCheck() {
        int kingIndex = 12;
        Position whiteKingPosition = whiteTeam.get(kingIndex).getPosition();
        Position blackKingPosition = blackTeam.get(kingIndex).getPosition();
        CheckEngine checkEngine = new CheckEngine(this);
        switch (currentPlayer) {
            case WHITE: return checkEngine.isKingExposed(whiteKingPosition);
            case BLACK: return checkEngine.isKingExposed(blackKingPosition);
        }
        return false;
    }

    //method under construction
    private boolean isMate() {
        MateEngine mateEngine = new MateEngine(this);
        return mateEngine.getCheckmateStatus();
    }

    //method under construction
    private boolean isStalemate() {
        return false;
    }

    private void switchCurrentPlayer() {
        switch (currentPlayer) {
            case BLACK: currentPlayer = Team.WHITE;
                break;
            case WHITE: currentPlayer = Team.BLACK;
                break;
        }
    }

    private void setupGame(List<Chessman> whiteTeam, List<Chessman> blackTeam) {
        newTeam(whiteTeam, Team.WHITE);
        newTeam(blackTeam, Team.BLACK);
        arrangePieces(whiteTeam, blackTeam, Board.BOARD.getInstance());
    }

    private void newTeam(List<Chessman> set, Team team) {
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

    private void arrangePieces(List<Chessman> team1, List<Chessman> team2, Position[][] board) {
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

    Team getCurrentPlayer() {
        return currentPlayer;
    }

    private void log(String message) {
        System.out.println(message);
    }
}

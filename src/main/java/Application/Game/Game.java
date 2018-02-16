package Application.Game;

import Application.ChessPieces.*;
import Application.Chessboard.Board;
import Application.Chessboard.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Application.ChessPieces.Team.*;
import static Application.Chessboard.Board.BOARD;
import static Application.Chessboard.Board.showBoard;
import static Application.Chessboard.Utils.enemyTeam;

public class Game {

    private List<Chessman> whiteTeam = new ArrayList<>();
    private List<Chessman> blackTeam = new ArrayList<>();
    private Team currentPlayer = WHITE;

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
            if (isMate()) {
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
            int charSurplus = 97;
            int reversedYAxisAdjustment = 8;
            x1 = move.charAt(0) - charSurplus;
            y1 = Math.abs(Integer.parseInt(move.substring(1, 2)) - reversedYAxisAdjustment);
            x2 = move.charAt(3) - charSurplus;
            y2 = Math.abs(Integer.parseInt(move.substring(4)) - reversedYAxisAdjustment);
            if (currentPlayer == BOARD.getPosition(x1,y1).getChessman().getTeam())
            executeMove(x1, y1, x2, y2);
        } else {
            log("Command should match a pattern xy/xy, based on coordinates on the chessboard.");
        }
    }

    private void executeMove(int fromX, int fromY, int toX, int toY) {
        Chessman chessman = BOARD.getPosition(fromX,fromY).getChessman();
        Chessman targetFieldChessman = BOARD.getPosition(toX,toY).getChessman();
        chessman.move(toX,toY);
        if (!inCheck() && !(chessman.getPosition().getX() == fromX && chessman.getPosition().getY() == fromY)) {
            switchCurrentPlayer();
        } else {
            Position currentField = BOARD.getPosition(fromX,fromY);
            Position targetField = BOARD.getPosition(toX,toY);
            setPosition(currentField, chessman);
            setPosition(targetField,targetFieldChessman);
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

    private boolean isMate() {
        MateEngine mateEngine = new MateEngine(this);
        return mateEngine.getCheckmateStatus();
    }

    private void switchCurrentPlayer() {
        switch (currentPlayer) {
            case BLACK: currentPlayer = WHITE;
                break;
            case WHITE: currentPlayer = BLACK;
                break;
        }
    }

    private void setupGame(List<Chessman> whiteTeam, List<Chessman> blackTeam) {
        newTeam(whiteTeam, WHITE);
        newTeam(blackTeam, BLACK);
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
        int y = 0;
        for (Position[] i : board) {
            int x = 0;
            for (Position j : i) {
                int pawnIndex = x;
                int nonPawnIndex = x + 8;
                if (y == 0) {
                    setPosition(j,team2.get(nonPawnIndex));
                }
                if (y == 1) {
                    setPosition(j,team2.get(pawnIndex));
                }
                if (y == 6) {
                    setPosition(j,team1.get(pawnIndex));
                }
                if (y == 7) {
                    setPosition(j,team1.get(nonPawnIndex));
                }
                x++;
            }
            y++;
        }

    }

    private void setPosition(Position position, Chessman chessman) {
        position.setChessman(chessman);
        chessman.setPosition(position);
    }

    Team getCurrentPlayer() {
        return currentPlayer;
    }

    private void log(String message) {
        System.out.println(message);
    }
}

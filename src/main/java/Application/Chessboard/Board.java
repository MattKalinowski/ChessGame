package Application.Chessboard;


public enum Board {

    BOARD;

    private Position[][] board;

    Board() {
        this.board = new Position[8][8];
        initPositions();
    }

    public Position[][] getInstance() {
        return board;
    }

    private void initPositions() {
        char x;
        int y = 8;
        int row = 0;
        int column;
        for (Position[] v : board) {
            x = 'a';
            column = 0;
            for (Position h : v) {
                h = new Position(x, y);
                board[row][column] = h;
                column++;
                x++;
            }
            y--;
            row++;
        }
    }

    public Position getPosition(char x, int y) {
        for (Position[] v : board) {
            for (Position h : v) {
                if (h.getX() == x && h.getY() == y)
                    return h;
            }
        }
        return null;
    }

    public static void showBoard() {
        for (Position[] i : Board.BOARD.getInstance()) {
            for (Position j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
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
        Position[][] board = BOARD.getInstance();
        char x = 97;

        System.out.print(" ");
        for (int i = 0; i < 8; i++) {
            System.out.print("  " + (x++) + "  ");
        }

        System.out.println();
        for (int i = 0; i < board.length; i++) {
            Position[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                Position position = row[j];
                if (j == 0) {
                    System.out.print(position.getY() + " ");
                }
                System.out.print(position.getChessman() + " ");
            }
            System.out.println();
        }

    }

}
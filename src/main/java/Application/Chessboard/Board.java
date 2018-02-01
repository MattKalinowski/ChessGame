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
        int x;
        int y = 0;
        for (Position[] i : board) {
            x = 0;
            for (Position j : i) {
                j = new Position(x, y);
                board[y][x] = j;
                x++;
            }
            y++;
        }
    }

    public Position getPosition(int x, int y) {
        for (Position[] i : board) {
            for (Position j : i) {
                if (j.getX() == x && j.getY() == y)
                    return j;
            }
        }
        return null;
    }

    public static void showBoard() {
        Position[][] board = BOARD.getInstance();
        char x = 'a';
        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < 8; i++) {
            System.out.print("  " + (x++) + "  ");
        }
        System.out.println();
        for (Position[] i : board) {
            for (int j = 0; j < i.length; j++) {
                Position position = i[j];
                if (j == 0) {
                    System.out.print(Math.abs((position.getY()) - 8) + " ");
                }
                System.out.print(position.getChessman() + " ");
            }
            System.out.println();
        }

    }

}
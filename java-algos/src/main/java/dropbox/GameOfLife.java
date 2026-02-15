package dropbox;

public class GameOfLife {

    private static final int[][] DIRS = new int[][]{
            {1, -1}, {1, 0}, {1, 1}, //down
            {0, -1}, {0, 1}, //left right
            {-1, -1}, {-1, 0}, {-1, 1}, //up
    };


    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[] updated = new int[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int ones = countOnes(i, j, board, rows, cols);

                if (board[i][j] == 1) {
                    updated[i * cols + j] = (ones < 2 || ones > 3) ? 0 : 1;
                } else {
                    updated[i * cols + j] = ones == 3 ? 1 : 0;

                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = updated[i * cols + j];
            }
        }
    }

    private static int countOnes(int row, int col, int[][] board, int rows, int cols) {
        int counter = 0;
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board[newRow][newCol] == 1) {
                counter++;
            }
        }
        return counter;
    }

    static void main() {
        int[][] board = {
                {0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}
        };

        gameOfLife(board);

    }
}


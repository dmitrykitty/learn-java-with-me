package dropbox;

public class GameOfLifeInplace {
    private static final int[][] DIRS = new int[][]{
            {1, -1}, {1, 0}, {1, 1}, //down
            {0, -1}, {0, 1}, //left right
            {-1, -1}, {-1, 0}, {-1, 1}, //up
    };


    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int ones = countOnes(i, j, board, rows, cols);

                if ((board[i][j] == 1 && (ones == 2 || ones == 3)) || (board[i][j] == 0 && ones == 3)){
                    board[i][j] += 2;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = board[i][j] > 1 ? 1 : 0;
            }
        }
    }

    private int countOnes(int row, int col, int[][] board, int rows, int cols){
        int counter = 0;
        for(int[] dir : DIRS){
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    board[newRow][newCol] % 2 == 1){
                counter++;
            }
        }
        return counter;
    }
}

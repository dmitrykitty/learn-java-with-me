package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class LT695_MaxAreaOfIsland {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    result = Math.max(result, drown(i, j, grid));
                }
            }
        }

        return result;
    }


    private int drown(int row, int col, int[][] grid){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0){
            return 0;
        }

        int result = 1;
        grid[row][col] = 0;

        for(int[] dir : DIRS){
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            result += drown(newRow, newCol, grid);
        }

        return result;
    }


    public int maxAreaOfIsland2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    result = Math.max(result, calculateResult(i, j, cols, grid));
                }
            }
        }
        return result;
    }

    private int calculateResult(int i, int j, int cols, int[][] grid){
        int currentResult = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(i * cols + j);
        grid[i][j] = 0;

        while(!q.isEmpty()){
            currentResult++;
            int coord = q.poll();
            int row = coord / cols;
            int col = coord % cols;

            for(int[] dir : DIRS){
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(isValid(newRow, newCol, grid)){
                    q.add(newRow * cols + newCol);
                    grid[newRow][newCol] = 0;
                }
            }
        }
        return currentResult;
    }

    private boolean isValid(int row, int col, int[][] grid){
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] != 0;
    }
}

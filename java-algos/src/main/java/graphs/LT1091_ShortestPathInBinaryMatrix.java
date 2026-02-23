package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class LT1091_ShortestPathInBinaryMatrix {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1){
            return -1;
        }
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Integer> q = new ArrayDeque<>();
        int distance = 1;

        q.add(0);
        grid[0][0] = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int coord = q.poll();
                int row = coord / cols;
                int col = coord % cols;

                if(row == rows - 1 && col == cols - 1) return distance;

                for(int[] dir : DIRS){
                    int newRow = dir[0] + row;
                    int newCol = dir[1] + col;
                    int newCoord = newRow * cols + newCol;

                    if(isValid(newRow, newCol, grid)){
                        q.add(newCoord);
                        grid[newRow][newCol] = 1;
                    }
                }
            }
            distance++;
        }
        return -1;
    }

    private boolean isValid(int row, int col, int[][] grid){
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] != 1;
    }
}

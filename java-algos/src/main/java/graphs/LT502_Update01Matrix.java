package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class LT502_Update01Matrix {
    private static int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[][] distances = new int[rows][cols];
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(mat[i][j] == 0) q.add(i * cols + j);
                else distances[i][j] = Integer.MAX_VALUE;
            }
        }

        while(!q.isEmpty()){
            int coord = q.poll();
            int row = coord / cols;
            int col = coord % cols;

            for(int[] dir : DIRS){
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(isValid(newRow, newCol, mat) && distances[newRow][newCol] == Integer.MAX_VALUE){
                    distances[newRow][newCol] = distances[row][col] + 1;
                    q.add(newRow * cols + newCol);
                }
            }
        }

        return distances;
    }

    private boolean isValid(int row, int col, int[][] mat){
        return row >= 0 && col >= 0 && row < mat.length && col < mat[0].length;
    }
}

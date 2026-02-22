package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class LT1926_NearestExitFromEntranceInMaze {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<Integer> q = new ArrayDeque<>();
        int[][] distances = new int[rows][cols];

        q.add(entrance[0] * cols + entrance[1]);
        maze[entrance[0]][entrance[1]] = '+';

        while(!q.isEmpty()){
            int coord = q.poll();
            int row = coord / cols;
            int col = coord % cols;

            int dist = distances[row][col];

            for(int[] dir : DIRS){
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;
                int newCoord = newRow * cols + newCol;

                if(isEnd(newRow, newCol, maze)){
                    if(row != entrance[0] || col != entrance[1]) return distances[row][col];
                    continue;
                }

                if(maze[newRow][newCol] == '.'){
                    q.add(newCoord);
                    maze[newRow][newCol] = '+';
                    distances[newRow][newCol] = dist + 1;
                }
            }
        }
        return -1;
    }

    private boolean isEnd(int row, int col, char[][] mat){
        return row < 0 || col < 0 || row >= mat.length || col >= mat[0].length;
    }
}

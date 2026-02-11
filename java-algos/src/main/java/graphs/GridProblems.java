package graphs;

import java.util.*;

public class GridProblems {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean isPath(int startRow, int startCol, int endRow, int endCol, int[][] grid) {
        if (startRow < 0 || startRow >= grid.length || startCol < 0 || startCol >= grid[0].length) {
            return false;
        }

        if (grid[startRow][startCol] == 1) {
            return false;
        }

        if (startRow == endRow && startCol == endCol) {
            return true;
        }

        grid[startRow][startCol] = 1;

        for (int[] dir : DIRS) {
            int newRow = startRow + dir[0];
            int newCol = startCol + dir[1];

            if (isPath(newRow, newCol, endRow, endCol, grid)) {
                return true;
            }
        }
        return false;
    }

    public int lengthOfShortestPath(int[][] grid, int startRow, int startCol, int endRow, int endCol) {
        if (startRow < 0 || startCol < 0 || startRow >= grid.length || startCol >= grid[0].length ||
                grid[startRow][startCol] == 1) {
            return -1;
        }

        if (startRow == endRow && startCol == endCol) {
            return 0;
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        visited[startRow][startCol] = true;
        q.add(new int[]{startRow, startCol, 0});

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];
            int dist = node[2];
            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length ||
                        grid[newRow][newCol] == 1 ||  visited[newRow][newCol]) {
                    continue;
                }
                if (newRow == endRow && newCol == endCol) {
                    return dist + 1;
                }
                visited[newRow][newCol] = true;
                q.add(new int[]{newRow, newCol, dist + 1});
            }
        }
        return -1;
    }


    public List<int[]> findShortestPath(int[][] grid, int startRow, int startCol, int endRow, int endCol) {
        if (startRow < 0 || startCol < 0 || startRow >= grid.length || startCol >= grid[0].length ||
                grid[startRow][startCol] == 1) {
            return List.of();
        }

        int[][][] parents = new int[grid.length][grid[0].length][2];
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        //start parent
        parents[startRow][startCol] = new int[]{-1, -1};
        boolean found = false;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];

            if (row == endRow && col == endCol) {
                found = true;
                break;
            }

            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length ||
                        grid[newRow][newCol] == 1 || visited[newRow][newCol]) {
                    continue;
                }
                visited[newRow][newCol] = true;
                parents[newRow][newCol] = new int[]{row, col};
                q.add(new int[]{newRow, newCol});
            }
        }
        if (!found) {
            return List.of();
        }

        return getPath(parents, endRow, endCol);
    }

    private List<int[]> getPath(int[][][] parentMap, int row, int col) {
        List<int[]> path = new ArrayList<>();

        while (row != -1 && col != -1) {
            path.add(new int[]{row, col});
            int[] prev =  parentMap[row][col];

            row = prev[0];
            col = prev[1];
        }
        return path.reversed();
    }
}






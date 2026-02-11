package graphs;

import java.util.*;

/**
 * A comprehensive utility class for solving graph-based problems on 2D grids.
 * Provides implementations for reachability, shortest path discovery,
 * and connected components analysis using DFS and BFS.
 */
public class GridProblems {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Determines if a path exists between two points in a grid using Depth-First Search (DFS).
     * <p>
     * The algorithm explores as deep as possible along each branch before backtracking.
     * It employs in-place modification of the grid to mark visited cells, significantly
     * reducing memory overhead.
     * </p>
     * <b>Algorithm Mechanics:</b>
     * <ul>
     * <li>Base cases handle out-of-bounds indices and obstacles (represented by 1).</li>
     * <li>Visits are recorded by setting the cell value to 1 to prevent infinite recursion.</li>
     * <li>Recursive calls explore cardinal directions with early exit (short-circuiting)
     * once the target is found.</li>
     * </ul>
     * @param startRow Starting row index.
     * @param startCol Starting column index.
     * @param endRow   Target row index.
     * @param endCol    Target column index.
     * @param grid      2D matrix where 0 is walkable and 1 is an obstacle/visited.
     * @return {@code true} if a path exists; {@code false} otherwise.
     * @implNote Time Complexity: O(N * M), Space Complexity: O(N * M) due to the recursion stack.
     */
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

    /**
     * Calculates the minimum number of steps to reach a target using Breadth-First Search (BFS).
     * <p>
     * BFS is optimal for finding the shortest path in unweighted graphs as it explores
     * nodes in layers (level-order traversal).
     * </p>
     * <b>Algorithm Mechanics:</b>
     * <ul>
     * <li>A {@code Queue} manages the frontier of explored cells.</li>
     * <li>A {@code visited} boolean matrix prevents cycles without modifying input data.</li>
     * <li>The first time the target is reached, the distance is guaranteed to be minimal.</li>
     * </ul>
     * @param grid     The 2D environment.
     * @param startRow Starting row index.
     * @param startCol Starting column index.
     * @param endRow   Target row index.
     * @param endCol    Target column index.
     * @return The number of edges in the shortest path, or -1 if unreachable.
     * @implNote Time Complexity: O(N * M), Space Complexity: O(N * M) to store the queue.
     */
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


    /**
     * Finds and reconstructs the full shortest path between two points.
     * <p>
     * Extends the standard BFS by utilizing a parent-tracking mechanism to reconstruct
     * the sequence of coordinates from destination back to source.
     * </p>
     * <b>Algorithm Mechanics:</b>
     * <ul>
     * <li>A 3D array {@code parents[row][col][2]} stores the coordinates of the node
     * from which each cell was first visited.</li>
     * <li>Once the target is found, {@link #getPath} traverses these pointers backward.</li>
     * </ul>
     * @return A {@code List} of {@code int[]} coordinates representing the path,
     * or an empty list if no path exists.
     */
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

    /**
     * Performs a backtracking traversal from the end coordinate to the start using the parent map.
     * @param parentMap 3D array storing predecessor coordinates.
     * @param row       Final row index.
     * @param col       Final column index.
     * @return Reconstructed path in source-to-destination order.
     */
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

    /**
     * Identifies the number of distinct connected components (islands) in a binary grid.
     * <p>
     * This problem is solved using the "Sink Island" technique. For every unvisited land
     * cell (1), it triggers a Flood Fill (DFS) to recursively convert all connected land
     * into water (0).
     * </p>
     * @param grid 2D matrix where 1 represents land and 0 represents water.
     * @return The total count of islands discovered.
     * @implNote Time Complexity: O(R * C), Space Complexity: O(R * C) in the worst case
     * for the recursion stack.
     */
    public int getNumberOfIslands(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    fillIsland(grid, i, j);
                }
            }
        }
        return count;

    }

    private void fillIsland(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||  grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0;
        for(int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            fillIsland(grid, newRow, newCol);
        }
    }
}






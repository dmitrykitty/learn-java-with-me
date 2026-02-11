package graphs;

import java.util.List;

public class GridRunner {
    static void main() {
        int[][] grid1 = {
                {0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0}
        };
        // Start: (0,0), Cel: (5,5)

        GridProblems gp =  new GridProblems();
        int len = gp.lengthOfShortestPath(grid1, 0, 0, 5, 5);
        System.out.println(len);

        List<int[]> shortestPath = gp.findShortestPath(grid1, 0, 0, 5, 5);
        printResult(shortestPath);
    }

    private static void printResult(List<int[]> path){
        for (int[] row : path){
            System.out.println("(" + row[0] + "," + row[1] + ")");
        }
    }
}

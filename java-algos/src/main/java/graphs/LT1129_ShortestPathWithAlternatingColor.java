package graphs;

import java.util.*;

/**
 * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1.
 * Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
 * You are given two arrays redEdges and blueEdges where:

 * redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
 * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
 * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

 * Example 1:
 * Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
 * Output: [0,1,-1]

 * Example 2:
 * Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
 * Output: [0,1,-1]
 */
public class LT1129_ShortestPathWithAlternatingColor {
    private static final int RED = 0;
    private static final int BLUE = 1;
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] adj = new List[2][n];

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < n; j++){
                adj[i][j] = new ArrayList<>();
            }
        }

        for(int[] redEdge : redEdges){
            adj[RED][redEdge[0]].add(redEdge[1]);
        }

        for(int[] blueEdge : blueEdges){
            adj[BLUE][blueEdge[0]].add(blueEdge[1]);
        }

        int[] dists = new int[n];
        Arrays.fill(dists, -1);
        dists[0] = 0;
        //[COLOR, NODE, DIST]
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[2][n];

        q.add(new int[]{RED, 0, 0});
        q.add(new int[]{BLUE, 0, 0});
        visited[RED][0] = true;
        visited[BLUE][0] = true;

        while(!q.isEmpty()){
            int[] data = q.poll();

            int nextColor = 1 - data[0];
            int node = data[1];
            int dist = data[2];

            for(int neigh : adj[nextColor][node]){
                if(!visited[nextColor][neigh]){
                    q.add(new int[]{nextColor, neigh, dist + 1});
                    visited[nextColor][neigh] = true;

                    if(dists[neigh] == -1){
                        dists[neigh] = dist + 1;
                    }
                }
            }
        }

        return dists;
    }
}

package graphs;

import java.util.*;

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes
 * a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge,
 * and no vertex has an edge to itself.

 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source
 * to destination, or false otherwise.

 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2

 * Example 2:
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 */
public class LT1971_FindIfPathExistsInGraph {
    public boolean validPathDFS(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];

        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }


        for(int[] pair : edges){
            adj.get(pair[0]).add(pair[1]);
            adj.get(pair[1]).add(pair[0]);
        }

        return dfs(source, destination, adj, visited);
    }

    private boolean dfs(int source, int destination, List<List<Integer>> adj, boolean[] visited){
        if(visited[source]){
            return false;
        }

        if(source == destination){
            return true;
        }

        visited[source] = true;

        for(int num: adj.get(source)){
            if(dfs(num, destination, adj, visited)){
                return true;
            }
        }

        return false;

    }



    public boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];

        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] pair : edges){
            adj.get(pair[0]).add(pair[1]);
            adj.get(pair[1]).add(pair[0]);
        }

        Queue<Integer> q = new ArrayDeque<>();

        q.add(source);
        visited[source] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == destination){
                return true;
            }

            for(int nb : adj.get(cur)){
                if(!visited[nb]){
                    q.add(nb);
                    visited[nb] = true;
                }
            }
        }
        return false;
    }


    private static class DSU{
        int[] parent;
        int[] rank;

        public DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++){
                makeSet(i);
            }
        }

        public void makeSet(int v){
            parent[v] = v;
            rank[v] = 0;
        }

        public int findSet(int v){
            if(parent[v] == v){
                return v;
            }
            return parent[v] = findSet(parent[v]);
        }

        public void unionSets(int a, int b){
            a = findSet(a);
            b = findSet(b);

            if(a != b){
                if(rank[a] < rank[b]){
                    parent[a] = b;
                } else if (rank[a] > rank[b]){
                    parent[b] = a;
                } else {
                    parent[b] = a;
                    rank[a]++;
                }
            }
        }
    }
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU dsu = new DSU(n);
        for(int[] pair : edges){
            dsu.unionSets(pair[0], pair[1]);
        }

        return dsu.findSet(source) == dsu.findSet(destination);
    }
}

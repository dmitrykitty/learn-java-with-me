package graphs;

import java.util.*;

public class LT323_NumberOfConnectedComponentsInUndirectedGraph {
    public int countComponentsBFS(int n, int[][] edges) {
        boolean[] visited = new boolean[n];

        List<List<Integer>> graph = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int result = 0;
        for(int i = 0; i < n; i++){
            result += bfs(i, graph, visited);
        }
        return result;
    }


    private int bfs(int node, List<List<Integer>> graph, boolean[] visited){
        if(visited[node]){
            return 0;
        }
        Queue<Integer> q = new ArrayDeque<>();

        q.add(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int neighbor : graph.get(cur)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        return 1;
    }

    //O(V + E)
    //mem O(V + E)
    public int countComponentsDFS(int n, int[][] edges) {
        boolean[] visited = new boolean[n];

        List<List<Integer>> adj = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int components = 0;
        //O(V + E)
        //mem O(V)
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                components++;
                dfs(i, visited, adj);
            }
        }

        return components;
    }

    private void dfs(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;
        for(int neighbor : adj.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor, visited, adj);
            }
        }
    }

    //O(V + E * alpha(V))
    //mem O(V)
    public int countComponentsDSU(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        //O(V + E * alpha(V)) where alfa - Ackermann function (often 1)
        //V because of initialization of array
        for(int[] edge : edges){
            dsu.unionSets(edge[0], edge[1]);
        }
        return dsu.getComponentsAmount();
    }

    private static class DSU {
        private final int[] parent;
        private final int[] rank;
        private int count;


        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;

            for (int i = 0; i < n; i++) {
                makeSet(i);
            }
        }

        private void makeSet(int v){
            parent[v] = v;
        }

        private int find(int v){
            if(parent[v] == v){
                return v;
            }
            return parent[v] = find(parent[v]);
        }

        //dynamically counting amount of components. After each successful union -> count-- because two prev
        //components v and w already is one component
        public void unionSets(int v, int w){
            v = find(v);
            w = find(w);

            //already the same component
            if(v == w){
                return;
            }

            if(rank[v] < rank[w]){
                parent[v] = w;
            } else if(rank[v] > rank[w]){
                parent[w] = v;
            } else {
                parent[v] = w;
                rank[w]++;
            }
            count--;
        }

        public int getComponentsAmount(){
            return count;
        }

    }
}

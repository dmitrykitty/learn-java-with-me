package graphs;

import java.util.ArrayList;
import java.util.List;

public class LT2368_ReachableNodesWithRestrictions {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<List<Integer>> adj = new ArrayList<>(n);
        boolean[] visited = new boolean[n];

        for(int node : restricted){
            visited[node] = true;
        }

        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        return dfs(0, adj, visited);
    }


    private int dfs(int node, List<List<Integer>> adj, boolean[] visited){
        if(visited[node]){
            return 0;
        }

        int count = 1;
        visited[node] = true;

        for(int neigh : adj.get(node)){
            count += dfs(neigh, adj, visited);
        }

        return count;
    }

    public int reachableNodes2(int n, int[][] edges, int[] restricted) {
        boolean[] isRestricted = new boolean[n];
        for (int r : restricted) {
            isRestricted[r] = true;
        }
        DSU dsu = new DSU(n);


        for(int[] edge : edges){
            if(!isRestricted[edge[0]] && !isRestricted[edge[1]]){
                dsu.union(edge[0], edge[1]);
            }
        }

        return dsu.getSize(0);
    }


    private static class DSU{
        int[] parent;
        int[] size;

        public DSU(int n){
            parent = new int[n];
            size = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int v){
            if(parent[v] == v){
                return v;
            }
            return parent[v] = find(parent[v]);
        }

        public void union(int v, int w){
            v = find(v);
            w = find(w);

            if(v == w){
                return;
            }

            if(size[v] >= size[w]){
                parent[w] = v;
                size[v] += size[w];
            } else {
                parent[v] = w;
                size[w] += size[v];
            }
        }

        public int getSize(int v){
            return size[find(v)];
        }
    }
}

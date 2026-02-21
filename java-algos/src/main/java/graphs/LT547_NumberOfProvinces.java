package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LT547_NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {

        List<List<Integer>> adj = new ArrayList<>(isConnected.length);
        boolean[] visited = new boolean[isConnected.length];

        for(int i = 0; i < isConnected.length; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < i; j++){
                if(isConnected[i][j] == 1){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        int result = 0;

        for(int i = 0; i < isConnected.length; i++){
            if(!visited[i]){
                result++;
                dfs(i, visited, adj);
            }
        }

        return result;
    }

    private void dfs(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;
        for(int neighbor : adj.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor, visited, adj);
            }
        }
    }


    public int findCircleNumBFS(int[][] isConnected) {

        List<List<Integer>> adj = new ArrayList<>(isConnected.length);
        boolean[] visited = new boolean[isConnected.length];

        for(int i = 0; i < isConnected.length; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < i; j++){
                if(isConnected[i][j] == 1){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        int result = 0;

        for(int i = 0; i < isConnected.length; i++){
            if(!visited[i]){
                result++;

                Queue<Integer> q = new ArrayDeque<>();
                visited[i] = true;
                q.add(i);

                while(!q.isEmpty()){
                    int cur = q.poll();
                    for(int neighbor : adj.get(cur)){
                        if(!visited[neighbor]){
                            visited[neighbor] = true;
                            q.add(neighbor);
                        }
                    }
                }

            }
        }

        return result;
    }


    public int findCircleNumDSU(int[][] isConnected) {

        DSU dsu = new DSU(isConnected.length);

        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < i; j++){
                if(isConnected[i][j] == 1){
                    dsu.unionSets(i, j);
                }
            }
        }

        return dsu.getCount();
    }

    private static class DSU{
        private final int[] parent;
        private final int[] rank;
        private int count;

        public DSU(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;

            rank = new int[n];
            count = n;
        }

        private int find(int v){
            if(parent[v] == v){
                return v;
            }
            return parent[v] = find(parent[v]);
        }

        public void unionSets(int v, int w){
            v = find(v);
            w = find(w);

            if(v == w){
                return;
            }

            if(rank[v] > rank[w]){
                parent[w] = v;
            } else if(rank[v] < rank[w]){
                parent[v] = w;
            } else {
                parent[w] = v;
                rank[v]++;
            }
            count--;
        }

        public int getCount(){
            return count;
        }
    }
}

package graphs;

public class DSU {
    int[] parent;
    int[] rank;
    public DSU(int N) {
        parent = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            make_set(i);
        }
    }
    public void make_set(int v){
        parent[v] = v;
        rank[v] = 0;
    }

    public int find_set(int v){
        if(parent[v] == v) {
            return v;
        }
//        int root = v;
//        while (parent[root] != root) root = parent[root];
//        while (parent[v] != v) {
//            int next = parent[v];
//            parent[v] = root; // Every node on the path now points to the root
//            v = next;
//        }

        return parent[v] = find_set(parent[v]);
    }

    public void union_sets(int a, int b){
        a = find_set(a);
        b = find_set(b);

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

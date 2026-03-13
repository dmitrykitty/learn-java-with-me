package dijkstra;

import java.util.*;

public class LT743_NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        //V + E
        List<List<int[]>> adj = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            adj.add(i, new ArrayList<>());
        }

        for (int[] time : times) {
            adj.get(time[0] - 1).add(new int[]{time[2], time[1] - 1});
        }

        int[] dists = new int[n];
        Arrays.fill(dists, Integer.MAX_VALUE);

        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        dists[k - 1] = 0;
        minHeap.add(new int[]{0, k - 1});

        //(V+E)logV
        while (!minHeap.isEmpty()) {
            int[] currPair = minHeap.poll();
            int currDist = currPair[0];
            int currNode = currPair[1];

            if (currDist > dists[currNode]) continue;

            for (int[] neigh : adj.get(currNode)) {
                int newDist = currDist + neigh[0];

                if (newDist < dists[neigh[1]]) {
                    minHeap.add(new int[]{newDist, neigh[1]});
                    dists[neigh[1]] = newDist;
                }
            }

        }

        int max = 0;
        for (int dist : dists) {
            if (dist != Integer.MAX_VALUE) max = Math.max(max, dist);
            else return -1;
        }

        return max;
    }

    static void main() {
        LT743_NetworkDelayTime n = new LT743_NetworkDelayTime();
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        n.networkDelayTime(times, 4, 2);
    }
}

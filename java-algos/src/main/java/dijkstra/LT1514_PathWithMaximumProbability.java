package dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LT1514_PathWithMaximumProbability {
    private static record Pair(
            int node,
            double prob
    )
    {};


    public double maxProbability(int n, int[][] edges, double[] succProb, int startNode, int endNode) {
        List<List<Pair>> adj = new ArrayList<>(n); //{toNode, prob}

        for(int i = 0; i < n; i++){
            adj.add(i, new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){
            adj.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        double[] probs = new double[n];
        Queue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.prob(), a.prob()));

        maxHeap.add(new Pair(startNode, 1));
        probs[startNode] = 1;

        while(!maxHeap.isEmpty()){
            Pair curPair = maxHeap.poll();
            if(curPair.prob() < probs[curPair.node()]) continue;

            for(Pair pr : adj.get(curPair.node())){
                double newProb = curPair.prob() * pr.prob();
                if(newProb > probs[pr.node()]){
                    maxHeap.add(new Pair(pr.node(), newProb));
                    probs[pr.node()] = newProb;

                }
            }
        }

        return probs[endNode];
    }
}

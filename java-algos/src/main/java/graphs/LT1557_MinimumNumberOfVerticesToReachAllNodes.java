package graphs;

import java.util.ArrayList;
import java.util.List;

public class LT1557_MinimumNumberOfVerticesToReachAllNodes {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] in = new boolean[n];

        for(List<Integer> edge : edges){
            in[edge.get(1)] = true;
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(!in[i]){
                result.add(i);
            }
        }
        return result;
    }
}

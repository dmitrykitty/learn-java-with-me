package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class LT1337_TheKWeakestRowsInMatrix {
    public int[] kWeakestRows(int[][] mat, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return Integer.compare(b[1], a[1]);
            return Integer.compare(b[0], a[0]);
        });

        for(int i = 0; i < mat.length; i++){
            int solders = countSolders(mat[i]);
            maxHeap.add(new int[]{solders, i});
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }

        int[] result = new int[k];
        for(int i = k - 1; i >= 0; i--){
            result[i] = maxHeap.poll()[1];
        }

        return result;
    }

    private int countSolders(int[] row){
        int l = 0;
        int r = row.length;

        while(l < r){
            int mid = l + (r - l)/2;
            if(row[mid] == 1) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}

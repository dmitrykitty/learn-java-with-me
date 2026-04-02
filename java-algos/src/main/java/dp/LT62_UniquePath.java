package dp;

import java.util.Arrays;

public class LT62_UniquePath {
    public int uniquePaths(int m, int n) {
        int[] first = new int[n];
        Arrays.fill(first, 1);
        int[] second = new int[n];


        for(int i = 1; i < m; i++){
            second[0] = 1;
            for(int j = 1; j < n; j++){
                second[j] = second[j - 1] + first[j];
            }
            first = second;
        }
        return first[n - 1];
    }
}

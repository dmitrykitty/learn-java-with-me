package prefixsum;

public class LT304_RangeSumQuery2D {
    private final int[][] prefix;

    public LT304_RangeSumQuery2D(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        prefix = new int[n][m];
        prefix[0][0] = matrix[0][0];

        for(int i = 1; i < m; i++){
            prefix[0][i] = prefix[0][i - 1] + matrix[0][i];
        }

        for(int i = 1; i < n; i++){
            prefix[i][0] = prefix[i - 1][0] + matrix[i][0];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1 == 0 && col1 == 0) return prefix[row2][col2];

        int fullSum = prefix[row2][col2];
        if(row1 == 0){
            return fullSum - prefix[row2][col1 - 1];
        }
        if(col1 == 0){
            return fullSum - prefix[row1 - 1][col2];
        }

        return fullSum - prefix[row1 - 1][col2] - prefix[row2][col1 - 1] + prefix[row1 - 1][col1 - 1];
    }
}

package arrays;

public class LT1886_DetermineWhetherMatrixCanBeObtainedByRotation {
    public boolean findRotation(int[][] mat, int[][] target) {
        int ns = mat.length;
        int nt = target.length;

        if(ns != nt) return false;

        return rotateAndCompare(mat, target, ns);

    }

    private boolean rotateAndCompare(int[][] mat, int[][] target, int n){
        boolean noRotate = true;
        boolean rotate90 = true;
        boolean rotate180 = true;
        boolean rotate270 = true;
        int amountOfFalses = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(noRotate && mat[i][j] != target[i][j]){
                    noRotate = false;
                    amountOfFalses++;
                }
                if(rotate90 && mat[i][j] != target[j][n - i - 1]){
                    rotate90 = false;
                    amountOfFalses++;
                }
                if(rotate180 && mat[i][j] != target[n - i - 1][n - j - 1]){
                    rotate180 = false;
                    amountOfFalses++;
                }
                if(rotate270 && mat[i][j] != target[n - j - 1][i]){
                    rotate270 = false;
                    amountOfFalses++;
                }
                if(amountOfFalses == 4)return false;
            }
        }
        return true;
    }
}

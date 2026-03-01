package backtracking;

import java.util.*;

public class LT51_NQueens {
    public static class SolutionNaive{
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();
            List<String> acc = new ArrayList<>();
            Set<Integer> placed = new HashSet<>();
            backtrack(result, acc, placed, n, 0);

            return result;
        }

        private void backtrack(List<List<String>> result, List<String> acc, Set<Integer> placed, int n, int row){
            if(row >= n && acc.size() < n) return;

            if(acc.size() == n){
                result.add(new ArrayList<>(acc));
                return;
            }

            for(int col = 0; col < n; col++){
                if(acc.size() < row) break;
                if(isConflict(row, col, n, placed)) continue;

                String currentRow = getRow(col, n);
                int coord = getCoord(row, col, n);
                acc.add(currentRow);
                placed.add(coord);
                backtrack(result, acc, placed, n, row + 1);
                placed.remove(coord);
                acc.removeLast();
            }
        }

        private boolean isConflict(int newRow, int newCol, int n, Set<Integer> placed){
            for(int coord : placed){
                int row = coord / n;
                int col = coord % n;

                if(newCol == col || Math.abs(newRow - row) == Math.abs(newCol - col)){
                    return true;
                }
            }
            return false;
        }

        private String getRow(int before, int n){
            int after = n - before - 1;

            String dotsBefore = ".".repeat(before);
            String dotsAfter = ".".repeat(after);

            return dotsBefore + "Q" + dotsAfter;
        }

        private int getCoord(int row, int col, int n){
            return row * n + col;
        }
    }

    public static class SolutionQuick{
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();

            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');

            boolean[] vert = new boolean[n];
            boolean[] diagUp = new boolean[2*n - 1]; //row + col
            boolean[] diagDown = new boolean[2*n - 1]; //row - col + n

            backtrack(result, board, vert, diagUp, diagDown, n, 0);
            return result;
        }

        private void backtrack(List<List<String>> result, char[][] board, boolean[] vert, boolean[] diagUp, boolean[] diagDown, int n, int row){
            if(row == n){
                result.add(getCombination(board, n));
                return;
            }

            for(int col = 0; col < n; col++){
                int dUp = row + col;
                int dDown = row - col + n - 1;

                if(vert[col] || diagUp[dUp] || diagDown[dDown]) continue;

                vert[col] = diagUp[dUp] = diagDown[dDown] = true;
                board[row][col] = 'Q';
                backtrack(result, board, vert, diagUp, diagDown, n, row + 1);
                board[row][col] = '.';
                vert[col] = diagUp[dUp] = diagDown[dDown] = false;
            }
        }

        private List<String> getCombination(char[][] board, int n){
            List<String> comb = new ArrayList<>();
            for(int i = 0; i < n; i++){
                comb.add(new String(board[i]));
            }
            return comb;
        }
    }

    static void main() {
        SolutionNaive solve = new SolutionNaive();
        List<List<String>> result = solve.solveNQueens(4);
    }
}

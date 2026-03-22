package dp;

/**
 * Ninja Training:
 * Given a 2D array points where points[day][activity] represents the merit points
 * earned by performing one of 3 activities on a given day, find the maximum total
 * merit points Ninja can earn over n days.
 * <p>
 * Rules:
 * - On each day, exactly one activity must be performed.
 * - The same activity cannot be chosen on two consecutive days.
 * <p>
 * Goal:
 * - Maximize the total number of merit points collected across all days.
 * <p>
 * Example:
 * points = {
 * {1, 2, 5},
 * {3, 1, 1},
 * {3, 3, 3}
 * }
 * <p>
 * One optimal choice is:
 * - Day 0 -> activity 2 (5 points)
 * - Day 1 -> activity 0 (3 points)
 * - Day 2 -> activity 1 or 2 (3 points)
 * <p>
 * Total = 11
 * <p>
 * Typical DP idea:
 * dp[day][last] = maximum merit points achievable up to 'day',
 * where 'last' represents the activity performed on the previous day
 * (or a special value meaning "no previous activity").
 */
public class NinjaTraining {
    public int ninjaTrainingTab(int n, int[][] points) {
        int[][] tab = new int[n][3];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                tab[i][0] = points[i][0];
                tab[i][1] = points[i][1];
                tab[i][2] = points[i][2];
            } else {
                tab[i][0] = Math.max(tab[i - 1][1], tab[i - 1][2]) + points[i][0];
                tab[i][1] = Math.max(tab[i - 1][0], tab[i - 1][2]) + points[i][1];
                tab[i][2] = Math.max(tab[i - 1][0], tab[i - 1][1]) + points[i][2];
            }
        }

        return Math.max(Math.max(tab[n - 1][0], tab[n - 1][1]), tab[n - 1][2]);
    }

    public int ninjaTrainingOpt(int n, int[][] points) {
        int[][] tab = new int[2][3];

        for (int i = 0; i < 3; i++) tab[0][i] = points[0][i];

        for (int i = 1; i < n; i++) {
            tab[1][0] = Math.max(tab[0][1], tab[0][2]) + points[i][0];
            tab[1][1] = Math.max(tab[0][0], tab[0][2]) + points[i][1];
            tab[1][2] = Math.max(tab[0][0], tab[0][1]) + points[i][2];

            int[] temp = tab[0];
            tab[0] = tab[1];
            tab[1] = temp;
        }
        return Math.max(Math.max(tab[0][0], tab[0][1]), tab[0][2]);
    }

    static void main() {
        NinjaTraining ninjaTraining = new NinjaTraining();
        System.out.println(ninjaTraining.ninjaTrainingTab(4, new int[][]{
                {1, 5, 2},
                {3, 3, 1},
                {8, 2, 4},
                {25, 5, 3}
        }));

        System.out.println(ninjaTraining.ninjaTrainingOpt(4, new int[][]{
                {1, 5, 2},
                {3, 3, 1},
                {8, 2, 4},
                {25, 5, 3}
        }));
    }
}

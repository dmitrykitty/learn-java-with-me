package graphs;

import java.util.ArrayList;
import java.util.List;

public class FinsIsPathExists {
    /*
    a-d
    a:b
    c:d
    ...
     */
    public static String TescoAllowedPath(String[] strArr) {
        if (strArr == null || strArr.length == 0) return "false";

        char[] startEndPair = getPair(strArr[0], true);
        int start = getScaledCode(startEndPair[0]);
        int end = getScaledCode(startEndPair[1]);

        if (start == end) return "true";

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < strArr.length; i++) {
            char[] pair = getPair(strArr[i], false);
            int startPoint = getScaledCode(pair[0]);
            int endPoint = getScaledCode(pair[1]);

            adj.get(startPoint).add(endPoint);
        }

        boolean[] visited = new boolean[26];
        return String.valueOf(dfs(adj, visited, start, end));
    }

    private static boolean dfs(List<List<Integer>> adj, boolean[] visited, int start, int end) {
        if (start == end) return true;
        if (visited[start]) return false;

        visited[start] = true;

        for (int neigh : adj.get(start)) {
            if (dfs(adj, visited, neigh, end)) return true;
        }

        return false;
    }

    private static char[] getPair(String str, boolean isStartEnd) {
        String splitChar = isStartEnd ? "-" : ":";
        String[] stringPair = str.split(splitChar);
        return new char[]{stringPair[0].charAt(0), stringPair[1].charAt(0)};
    }

    private static int getScaledCode(char ch) {
        return ch - 'a';
    }
}


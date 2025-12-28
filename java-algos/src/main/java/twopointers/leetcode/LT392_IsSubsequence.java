package twopointers.leetcode;

public class LT392_IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int w = 0;
        int n = s.length();
        if(s.isEmpty()){
            return true;
        }
        if(t.isEmpty()){
            return false;
        }
        for(int i = 0; i < t.length(); i++){
            if(s.charAt(w) == t.charAt(i)){
                w++;
                if(w >= n) return true;
            }
        }
        return false;
    }
}

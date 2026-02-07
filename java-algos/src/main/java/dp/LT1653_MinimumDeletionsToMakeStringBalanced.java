package dp;

/**
 * You are given a string s consisting only of characters 'a' and 'b'.
 * You can delete any number of characters in s to make s balanced.
 * s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 * Return the minimum number of deletions needed to make s balanced.

 * Example 1:
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").

 * Example 2:
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 */
public class LT1653_MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        if(s.length() == 1){
            return 0;
        }

        int afterAcount = 0;
        for(char c : s.toCharArray()){
            if(c == 'a'){
                afterAcount++;
            }
        }

        if(afterAcount == 0 || afterAcount == s.length()){
            return 0;
        }

        int beforeBcount = 0;
        int resultSum = beforeBcount + afterAcount;

        for(char c : s.toCharArray()){
            if(c == 'a'){
                afterAcount--;
            } else {
                beforeBcount++;
            }
            resultSum = Math.min(resultSum, beforeBcount + afterAcount);
        }

        return resultSum;

    }
}

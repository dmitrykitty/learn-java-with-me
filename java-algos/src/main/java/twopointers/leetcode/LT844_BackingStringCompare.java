package twopointers.leetcode;

/**
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.

 * Example 1:
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".

 * Example 2:
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".

 * Example 3:
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 */
public class LT844_BackingStringCompare {

    /**
     * @param s The first string to compare.
     * @param t The second string to compare.
     * @return {@code true} if the final strings are equal, {@code false} otherwise.
     * Space Complexity: O(1) as we only use a few integer variables.
     * Time Complexity: O(N + M) where N and M are the lengths of strings s and t.
     */
    public boolean backspaceCompare(String s, String t) {
        int si = s.length() - 1;
        int ti = t.length() - 1;

        //continue until at least one string has chars to check
        while (si >= 0 || ti >= 0) {
            int scounter = 0;

            //moving pointer si until it possible
            while (si >= 0) {
                if (s.charAt(si) == '#') {
                    scounter++;
                } else {
                    scounter--;
                }
                if (scounter < 0) {
                    break;
                }
                si--;
            }

            //moving pointer ti until it possible
            int tcounter = 0;
            while (ti >= 0) {
                if (t.charAt(ti) == '#') {
                    tcounter++;
                } else {
                    tcounter--;
                }
                if (tcounter < 0) {
                    break;
                }
                ti--;
            }

            //if at least one string already finished -> strings has different final length
            if ((ti >= 0) != (si >= 0)) {
                return false;
            }

            //comparing two unremoved chars
            if (ti >= 0 && si >= 0) {
                if (s.charAt(si) != t.charAt(ti)) {
                    return false;
                }
                si--;
                ti--;
            }
        }
        return true;
    }


    public boolean backspaceCompareWithStack(String s, String t){
        //TODO: provide solution using stack insted two pointers;
        return false;
    }

}


package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LT3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) return 0;
        Set<Character> windowState = new HashSet<>();

        int begin = 0;
        int result = 1;

        for(int end = 0; end < s.length(); end++){
            char lastChar = s.charAt(end);
            if(windowState.contains(lastChar)){
                result = Math.max(windowState.size(), result);
                while(windowState.contains(lastChar)){
                    windowState.remove(s.charAt(begin));
                    begin++;
                }
            }
            windowState.add(lastChar);
        }
        return Math.max(result, windowState.size());
    }

    public int lengthOfLongestSubstringOPT(String s) {
        if (s == null || s.isEmpty()) return 0;

        Set<Character> window = new HashSet<>();
        int begin = 0;
        int best = 0;

        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            while (window.contains(c)) {
                window.remove(s.charAt(begin));
                begin++;
            }

            window.add(c);
            best = Math.max(best, end - begin + 1);
        }

        return best;
    }
}

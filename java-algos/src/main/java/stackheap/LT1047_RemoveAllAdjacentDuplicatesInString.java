package stackheap;

import java.util.ArrayDeque;
import java.util.Deque;

public class LT1047_RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != currentChar) {
                stack.push(currentChar);
            } else {
                stack.pop();
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    public String removeDuplicates1(String s) {
        StringBuilder sb = new StringBuilder();

        for(char currentChar: s.toCharArray()) {
            int len = sb.length();
            if(sb.isEmpty() || sb.charAt(len - 1) != currentChar) {
                sb.append(currentChar);
            } else {
                sb.deleteCharAt(len - 1);
            }
        }
        return sb.toString();
    }

}

package stackheap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Example 1:
 * Input: s = "()"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * <p>
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * <p>
 * Example 4:
 * Input: s = "([])"
 * Output: true
 * <p>
 * Example 5:
 * Input: s = "([)]"
 * Output: false
 */
public class LT20_ValidParentheses {

    public boolean isValid(String s) {

        if(s.length() % 2 != 0) return false;

        Map<Character, Character> bracketsPairs = Map.of('(', ')', '[', ']', '{', '}');
        Deque<Character> stack = new ArrayDeque<>();

        for (char currentChar : s.toCharArray()) {
            if (bracketsPairs.containsKey(currentChar)) {
                stack.push(currentChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char openBracket = stack.pop();

                if (bracketsPairs.get(openBracket) != currentChar) {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) return false;
        return true;
    }

    public boolean isValid2(String s) {
        if(s.length() % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();

        for(char currentChar : s.toCharArray()) {
            if(currentChar == '('){
                stack.push(')');
            } else if (currentChar == '{') {
                stack.push('}');
            } else if (currentChar == '[') {
                stack.push(']');
            } else
                if(stack.isEmpty() || stack.peek() != currentChar){
                    return false;
                }
        }
        return stack.isEmpty();
    }
}

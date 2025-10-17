package strings.practise;

public class UniqueChars {
    public static String getUniqueChars(String text) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        int flag = 0;
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            for (int j = 0; j < result.length(); j++) {
                if (result.charAt(j) == currentChar) {
                    flag = 1;
                    break;
                }
            }
            if (!(flag == 1 || currentChar == ' ')) {
                result.append(currentChar);
            }
            flag = 0;
        }
        return result.toString();
    }
}

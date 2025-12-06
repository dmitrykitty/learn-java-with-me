package com.dnikitin.strings.practise;

public class Polindrom {
    public static boolean isPolindrom(String text){
        return text.contentEquals(new StringBuilder(text).reverse());
    }

    public static boolean isPolindrom2(String text){
        int charsToCheck = text.length() / 2;
        for (int i = 0; i < charsToCheck; i++) {
            if (text.charAt(i) != text.charAt(text.length() - i - 1)) {
                return false;
            }
        }
        return true;

    }
}

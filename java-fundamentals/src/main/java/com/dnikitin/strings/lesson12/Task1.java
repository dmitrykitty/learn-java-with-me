package com.dnikitin.strings.lesson12;
public class Task1 {
    void main(String[] args) {
        String text = "Hello :(";
        System.out.println(replaceSmiles(text));
    }

    public static String replaceSmiles(String text){
        return text.replace(":(", ":)");
    }
}

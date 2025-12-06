package com.dnikitin.strings.lesson12;

public class Task4 {
    void main(String[] args) {
        String text = "H,.!?dgdlg ..!ddd";
        System.out.println(countChars(text, ",!.?"));
    }

    static int countChars(String text, String value){
        int count = 0;
        for(int i = 0; i < text.length(); i++){
            for(int j = 0; j < value.length(); j++)
                if(text.charAt(i) == value.charAt(j)){
                    count++;
                    break;
                }
        }
        return count;
    }
}

package com.insano10.puzzlers.strings;

public class ReverseString {

    public static String reverse(String str) {

        if(str == null) {
            throw new IllegalArgumentException("Cannot reverse null string");
        }
        if(str.isEmpty()) {
            return str;
        }

        char[] chars = str.toCharArray();

        int endIdx =  chars.length/2;
        int swapOffset = chars[chars.length-1] == '\0' ? chars.length - 2 : chars.length - 1; //don't swap the null terminator

        for(int i=0 ; i< endIdx ; i++) {

            char tmp = chars[i];
            chars[i] = chars[i + swapOffset];
            chars[i + swapOffset] = tmp;

            swapOffset -= 2;
        }

        return new String(chars);
    }
}

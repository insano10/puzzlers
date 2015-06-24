package com.insano10.puzzlers.strings;

import java.util.HashSet;
import java.util.Set;

public class StringHasAllUniqueCharacters {


    public static boolean hasAllUniqueChars(String str) {

        if (str == null) {
            throw new IllegalArgumentException("String input is null");
        }

        Set<Character> charsSeen = new HashSet<>();

        for (char c : str.toCharArray()) {

            if (charsSeen.contains(c)) {
                return false;
            } else {
                charsSeen.add(c);
            }
        }

        return true;
    }

    public static boolean hasAllUniqueCharsNoAdditionalDataStructure(String str) {

        if (str == null) {
            throw new IllegalArgumentException("String input is null");
        }

        char[] chars = str.toCharArray();

        for(int i=0 ; i < chars.length-1 ; i++) {

            if(containsChar(chars, i+1, chars[i])) {
                return false;
            }
        }

        return true;
    }

    private static boolean containsChar(char[] array, int searchFromIdx, char c) {

        for (int i = searchFromIdx; i < array.length; i++) {

            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }

}

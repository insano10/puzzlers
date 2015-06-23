package com.insano10.puzzlers.strings;

import java.util.HashSet;
import java.util.Set;

public class StringHasAllUniqueCharacters {


    public static boolean hasAllUniqueChars(String str) {

        if(str == null) {
            throw new IllegalArgumentException("String input is null");
        }

        Set<Character> charsSeen = new HashSet<>();

        for (char c : str.toCharArray()) {

            if(charsSeen.contains(c)) {
                return false;
            } else {
                charsSeen.add(c);
            }
        }

        return true;
    }

}

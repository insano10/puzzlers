package com.insano10.puzzlers.strings;

import com.insano10.puzzlers.sorting.QuickSort;

import java.util.Arrays;

public class StringAnagrams {

    public static boolean areAnagrams(String str1, String str2) {

        if(str1 == null || str2 == null) {
            throw new IllegalArgumentException("Cannot process null string");
        }

        Character[] str1CharArray = getCharacterArray(str1);
        Character[] str2CharArray = getCharacterArray(str2);

        QuickSort.sortWithExtraDataStructures(str1CharArray);
        QuickSort.sortWithExtraDataStructures(str2CharArray);

        return Arrays.equals(str1CharArray, str2CharArray);
    }

    private static Character[] getCharacterArray(String str)
    {
        return str.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
    }
}

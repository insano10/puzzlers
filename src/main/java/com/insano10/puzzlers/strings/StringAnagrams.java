package com.insano10.puzzlers.strings;

import com.insano10.puzzlers.sorting.QuickSort;

import java.util.Arrays;

public class StringAnagrams {

    public static boolean areAnagrams(String str1, String str2) {

        if(str1 == null || str2 == null) {
            throw new IllegalArgumentException("Cannot process null string");
        }

        char[] str1CharArray = str1.toCharArray();
        char[] str2CharArray = str2.toCharArray();

        QuickSort.sortWithExtraDataStructures(str1CharArray);
        QuickSort.sortWithExtraDataStructures(str2CharArray);

        return Arrays.equals(str1CharArray, str2CharArray);
    }
}

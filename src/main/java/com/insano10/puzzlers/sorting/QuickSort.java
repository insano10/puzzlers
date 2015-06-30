package com.insano10.puzzlers.sorting;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static void sortWithExtraDataStructures(char[] array) {

        if(array == null) {
            throw new IllegalArgumentException("Array is null");
        }

        //base case
        if (array.length <= 1) {
            return;
        }

        //choose a pivot point
        int pivotIndex = choosePivotIndex(array);

        //ensure all chars left of pivot are smaller, and right are larger
        int smallLength = 0;
        int equalLength = 0;
        int greaterLength = 0;
        char[] smaller = new char[array.length];
        char[] equal = new char[array.length];
        char[] greater = new char[array.length];

        for (int i = 0; i < array.length; i++) {

            if (array[i] < array[pivotIndex]) {
                smaller[smallLength] = array[i];
                smallLength++;
            } else if (array[i] == array[pivotIndex]) {
                equal[equalLength] = array[i];
                equalLength++;
            } else {
                greater[greaterLength] = array[i];
                greaterLength++;
            }
        }

        //resize the arrays
        smaller = resize(smaller, smallLength);
        equal = resize(equal, equalLength);
        greater = resize(greater, greaterLength);

        //run quicksort on both sides of the pivot
        sortWithExtraDataStructures(smaller);
        sortWithExtraDataStructures(greater);

        //recombine the arrays
        int totalIdx = 0;
        for (int i = 0; i < smaller.length; i++) {
            array[totalIdx] = smaller[i];
            totalIdx++;
        }
        for (int i = 0; i < equal.length; i++) {
            array[totalIdx] = equal[i];
            totalIdx++;
        }
        for (int i = 0; i < greater.length; i++) {
            array[totalIdx] = greater[i];
            totalIdx++;
        }
    }

    public static List<Character> sortWithArrayLists(List<Character> characterList) {

        if(characterList == null) {
            throw new IllegalArgumentException("Array is null");
        }

        //base case
        if (characterList.size() <= 1) {
            return characterList;
        }

        //choose a pivot point
        int pivotIndex = choosePivotIndex(characterList);

        //ensure all chars left of pivot are smaller, and right are larger
        List<Character> smaller = new ArrayList<>(characterList.size());
        List<Character>  equal = new ArrayList<>(characterList.size());
        List<Character>  greater = new ArrayList<>(characterList.size());

        for (int i = 0; i < characterList.size(); i++) {

            if (characterList.get(i) < characterList.get(pivotIndex)) {
                smaller.add(characterList.get(i));
            } else if (characterList.get(i) == characterList.get(pivotIndex)) {
                equal.add(characterList.get(i));
            } else {
                greater.add(characterList.get(i));
            }
        }

        //run quicksort on both sides of the pivot
        smaller = sortWithArrayLists(smaller);
        greater = sortWithArrayLists(greater);

        //recombine the arrays
        smaller.addAll(equal);
        smaller.addAll(greater);

        return smaller;
    }

    private static int choosePivotIndex(char[] array) {

        return array.length / 2;
    }

    private static int choosePivotIndex(List<Character> characterList) {

        return characterList.size() / 2;
    }

    private static char[] resize(char[] array, int newSize) {

        char[] reSized = new char[newSize];
        for (int i = 0; i < newSize; i++) {

            reSized[i] = array[i];
        }
        return reSized;
    }
}

package com.insano10.puzzlers.sorting;

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

    private static int choosePivotIndex(char[] array) {

        return array.length / 2;
    }

    private static char[] resize(char[] array, int newSize) {

        char[] reSized = new char[newSize];
        for (int i = 0; i < newSize; i++) {

            reSized[i] = array[i];
        }
        return reSized;
    }
}

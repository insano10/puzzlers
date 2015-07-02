package com.insano10.puzzlers.sorting;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static void sortWithExtraDataStructures(char[] array) {

        if (array == null) {
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

        if (characterList == null) {
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
        List<Character> equal = new ArrayList<>(characterList.size());
        List<Character> greater = new ArrayList<>(characterList.size());

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

    public static void sortInPlace(char[] array) {

        if (array == null) {
            throw new IllegalArgumentException("Array is null");
        }

        //sort the whole length of the array
        quickSortInner(array, 0, array.length - 1);
    }

    private static void quickSortInner(char[] array, int startIndex, int endIndex) {

        //as long as there is more than a single element to sort...
        if(endIndex - startIndex >= 1) {

            int pivotIndex = choosePivotIndex(array, startIndex, endIndex);
            int left = startIndex;
            int right = endIndex;

            //walk the 'left' and 'right' pointers inwards towards the pivot keeping elements less than 'left' to the left
            //and elements greater than 'right' to the right
            while(left <= right) {

                while(array[left] < array[pivotIndex]) {
                    left += 1;
                }
                while(array[right] > array[pivotIndex]) {
                    right -= 1;
                }

                //if you cannot walk the pointers in any further then swap those elements
                // - the 'right' element must be less than the pivot otherwise you could walk further
                // - the 'left' element must be greater than the pivot for the same reason
                if(left <= right) {
                    char tmp = array[left];
                    array[left] = array[right];
                    array[right] = tmp;
                    left += 1;
                    right -= 1;
                }
            }

            //continue sorting the 2 halves of the array ('left' is now higher than 'right')
            //this will continue until the 2 halves only have a single element each
            quickSortInner(array, startIndex, right);
            quickSortInner(array, left, endIndex);
        }
    }

    private static int choosePivotIndex(char[] array) {

        return array.length / 2;
    }

    private static int choosePivotIndex(char[] array, int startIndex, int endIndex) {

        return startIndex + (endIndex - startIndex)/2;
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

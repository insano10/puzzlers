package com.insano10.puzzlers.sorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class QuickSort {

    public static <T extends Comparable<T>> void sortWithExtraDataStructures(T[] array) {


        if (array == null) {
            throw new IllegalArgumentException("Array is null");
        }

        //base case
        if (array.length <= 1) {
            return;
        }

        Class<T> arrayClass = (Class<T>)array.getClass().getComponentType();

        //choose a pivot point
        int pivotIndex = choosePivotIndex(array);

        //ensure all elements left of pivot are smaller, and right are larger
        int smallLength = 0;
        int equalLength = 0;
        int greaterLength = 0;
        T[] smaller = (T[])Array.newInstance(arrayClass, array.length);
        T[] equal = (T[])Array.newInstance(arrayClass, array.length);
        T[] greater = (T[])Array.newInstance(arrayClass, array.length);

        for (int i = 0; i < array.length; i++) {

            if (array[i].compareTo(array[pivotIndex]) < 0) {
                smaller[smallLength] = array[i];
                smallLength++;
            } else if (array[i].equals(array[pivotIndex])) {
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

    public static <T extends Comparable<T>> List<T> sortWithArrayLists(List<T> list) {

        if (list == null) {
            throw new IllegalArgumentException("Array is null");
        }

        //base case
        if (list.size() <= 1) {
            return list;
        }

        //choose a pivot point
        int pivotIndex = choosePivotIndex(list);

        //ensure all elements left of pivot are smaller, and right are larger
        List<T> smaller = new ArrayList<>(list.size());
        List<T> equal = new ArrayList<>(list.size());
        List<T> greater = new ArrayList<>(list.size());

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).compareTo(list.get(pivotIndex)) < 0) {
                smaller.add(list.get(i));
            } else if (list.get(i).equals(list.get(pivotIndex))) {
                equal.add(list.get(i));
            } else {
                greater.add(list.get(i));
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

    public static <T extends Comparable<T>> void sortInPlace(T[] array) {

        if (array == null) {
            throw new IllegalArgumentException("Array is null");
        }

        //sort the whole length of the array
        quickSortInner(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSortInner(T[] array, int startIndex, int endIndex) {

        //as long as there is more than a single element to sort...
        if(endIndex - startIndex >= 1) {

            int pivotIndex = choosePivotIndex(startIndex, endIndex);
            int left = startIndex;
            int right = endIndex;

            //walk the 'left' and 'right' pointers inwards towards the pivot keeping elements less than 'left' to the left
            //and elements greater than 'right' to the right
            while(left <= right) {

                while(array[left].compareTo(array[pivotIndex]) < 0) {
                    left += 1;
                }
                while(array[right].compareTo(array[pivotIndex]) > 0) {
                    right -= 1;
                }

                //if you cannot walk the pointers in any further then swap those elements
                // - the 'right' element must be less than the pivot otherwise you could walk further
                // - the 'left' element must be greater than the pivot for the same reason
                if(left <= right) {
                    T tmp = array[left];
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

    private static <T extends Comparable<T>> int choosePivotIndex(T[] array) {

        return array.length / 2;
    }

    private static int choosePivotIndex(int startIndex, int endIndex) {

        return startIndex + (endIndex - startIndex)/2;
    }

    private static <T extends Comparable<T>> int choosePivotIndex(List<T> list) {

        return list.size() / 2;
    }

    private static <T extends Comparable<T>> T[] resize(T[] array, int newSize) {

        Class<T> arrayClass = (Class<T>)array.getClass().getComponentType();
        T[] reSized = (T[])Array.newInstance(arrayClass, newSize);
        System.arraycopy(array, 0, reSized, 0, newSize);
        return reSized;
    }
}

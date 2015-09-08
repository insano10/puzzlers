package com.insano10.puzzlers.sorting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InsertionSortTest
{

    @Test
    public void shouldSortArrayInPlace() throws Exception
    {
        int[] array = new int[] {6, 3, 4, 7, 1, 2, 8};
        int[] sortedArray = new int[] {1, 2, 3, 4, 6, 7, 8};

        InsertionSort.insertionSort(array);

        assertThat(array).containsExactly(sortedArray);
    }

    @Test
    public void shouldSortEmptyArray() throws Exception
    {
        int[] array = new int[0];
        int[] sortedArray = new int[0];

        InsertionSort.insertionSort(array);

        assertThat(array).containsExactly(sortedArray);
    }

    @Test
    public void shouldSortArrayWithSingleElement() throws Exception
    {
        int[] array = new int[] {6};
        int[] sortedArray = new int[] {6};

        InsertionSort.insertionSort(array);

        assertThat(array).containsExactly(sortedArray);
    }
}
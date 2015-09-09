package com.insano10.puzzlers.sorting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MergeSortTest
{
    @Test
    public void shouldSortArrayWithEvenNumberOfElements() throws Exception
    {
        int[] array = new int[]{5, 2, 9, 6, 3, 1, 2, 5, 7, 8};
        int[] sortedArray = new int[]{1, 2, 2, 3, 5, 5, 6, 7, 8, 9};

        assertThat(MergeSort.sort(array)).isEqualTo(sortedArray);
    }

    @Test
    public void shouldSortArrayWithOddNumberOfElements() throws Exception
    {
        int[] array = new int[]{5, 2, 9, 6, 3, 1, 5, 7, 8};
        int[] sortedArray = new int[]{1, 2, 3, 5, 5, 6, 7, 8, 9};

        assertThat(MergeSort.sort(array)).isEqualTo(sortedArray);
    }

    @Test
    public void shouldSortEmptyArray() throws Exception
    {
        int[] array = new int[0];
        int[] sortedArray = new int[0];

        assertThat(MergeSort.sort(array)).containsExactly(sortedArray);
    }

    @Test
    public void shouldSortArrayWithSingleElement() throws Exception
    {
        int[] array = new int[] {6};
        int[] sortedArray = new int[] {6};

        assertThat(MergeSort.sort(array)).containsExactly(sortedArray);
    }
}
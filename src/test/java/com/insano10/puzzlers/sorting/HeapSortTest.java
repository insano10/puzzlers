package com.insano10.puzzlers.sorting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeapSortTest
{

    @Test
    public void shouldSortUnsortedArray() throws Exception {

        Character[] unsortedArray = {'b', 'e', 'a', 'h', 'c'};
        Character[] sortedArray = {'a', 'b', 'c', 'e', 'h'};

        HeapSort.sort(unsortedArray);

        assertThat(unsortedArray).isEqualTo(sortedArray);
    }

    @Test
    public void shouldSortLotsOfIntegers() throws Exception {

        Integer[] unsortedArray = {5,4,7,8,9,3,2,4,6,7,3,1,0,2,4,6,9};
        Integer[] sortedArray = {0,1,2,2,3,3,4,4,4,5,6,6,7,7,8,9,9};

        HeapSort.sort(unsortedArray);

        assertThat(unsortedArray).isEqualTo(sortedArray);
    }

    @Test
    public void shouldSortAlreadySortedArray() throws Exception {

        Character[] sortedArray = {'a', 'b', 'c', 'e', 'h'};
        Character[] expectedSortedArray = {'a', 'b', 'c', 'e', 'h'};

        HeapSort.sort(sortedArray);

        assertThat(sortedArray).isEqualTo(expectedSortedArray);
    }

    @Test
    public void shouldSortArrayWithAllSameValues() throws Exception {

        Character[] sortedArray = {'a', 'a', 'a', 'a', 'a', 'a'};
        Character[] expectedSortedArray = {'a', 'a', 'a', 'a', 'a', 'a'};

        HeapSort.sort(sortedArray);

        assertThat(sortedArray).isEqualTo(expectedSortedArray);
    }

    @Test
    public void shouldSortEmptyArray() throws Exception {

        Character[] emptyArray = new Character[0];

        HeapSort.sort(emptyArray);

        assertThat(emptyArray).isEqualTo(new char[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPassedNull() throws Exception {

        HeapSort.sort(null);
    }
}
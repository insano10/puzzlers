package com.insano10.puzzlers.sorting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickSortInPlaceTest {


    @Test
    public void shouldSortUnsortedArray() throws Exception {

        char[] unsortedArray = {'b', 'e', 'a', 'h', 'c'};
        char[] sortedArray = {'a', 'b', 'c', 'e', 'h'};

        QuickSort.sortInPlace(unsortedArray);

        assertThat(unsortedArray).isEqualTo(sortedArray);
    }

    @Test
    public void shouldSortAlreadySortedArray() throws Exception {

        char[] sortedArray = {'a', 'b', 'c', 'e', 'h'};
        char[] expectedSortedArray = {'a', 'b', 'c', 'e', 'h'};

        QuickSort.sortInPlace(sortedArray);

        assertThat(sortedArray).isEqualTo(expectedSortedArray);
    }

    @Test
    public void shouldSortArrayWithAllSameValues() throws Exception {

        char[] sortedArray = {'a', 'a', 'a', 'a', 'a', 'a'};
        char[] expectedSortedArray = {'a', 'a', 'a', 'a', 'a', 'a'};

        QuickSort.sortInPlace(sortedArray);

        assertThat(sortedArray).isEqualTo(expectedSortedArray);
    }

    @Test
    public void shouldSortEmptyArray() throws Exception {

        char[] emptyArray = new char[0];

        QuickSort.sortInPlace(emptyArray);

        assertThat(emptyArray).isEqualTo(new char[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPassedNull() throws Exception {

        QuickSort.sortInPlace(null);
    }
}

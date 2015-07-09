package com.insano10.puzzlers.sorting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickSortWithExtraDataStructuresTest {


    @Test
    public void shouldSortUnsortedArray() throws Exception {

        Character[] unsortedArray = {'b', 'e', 'a', 'h', 'c'};
        Character[] sortedArray = {'a', 'b', 'c', 'e', 'h'};

        QuickSort.sortWithExtraDataStructures(unsortedArray);

        assertThat(unsortedArray).isEqualTo(sortedArray);
    }

    @Test
    public void shouldSortAlreadySortedArray() throws Exception {

        Character[] sortedArray = {'a', 'b', 'c', 'e', 'h'};
        Character[] expectedSortedArray = {'a', 'b', 'c', 'e', 'h'};

        QuickSort.sortWithExtraDataStructures(sortedArray);

        assertThat(sortedArray).isEqualTo(expectedSortedArray);
    }

    @Test
    public void shouldSortArrayWithAllSameValues() throws Exception {

        Character[] sortedArray = {'a', 'a', 'a', 'a', 'a', 'a'};
        Character[] expectedSortedArray = {'a', 'a', 'a', 'a', 'a', 'a'};

        QuickSort.sortWithExtraDataStructures(sortedArray);

        assertThat(sortedArray).isEqualTo(expectedSortedArray);
    }

    @Test
    public void shouldSortEmptyArray() throws Exception {

        Character[] emptyArray = new Character[0];

        QuickSort.sortWithExtraDataStructures(emptyArray);

        assertThat(emptyArray).isEqualTo(new char[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPassedNull() throws Exception {

        QuickSort.sortWithExtraDataStructures(null);
    }
}

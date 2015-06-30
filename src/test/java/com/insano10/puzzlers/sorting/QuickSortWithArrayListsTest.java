package com.insano10.puzzlers.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickSortWithArrayListsTest {


    @Test
    public void shouldSortUnsortedArray() throws Exception {

        List<Character> inputList = Arrays.asList('b', 'e', 'a', 'h', 'c');
        List<Character> expectedList = Arrays.asList('a', 'b', 'c', 'e', 'h');

        List<Character> sortedList = QuickSort.sortWithArrayLists(inputList);

        assertThat(sortedList).isEqualTo(expectedList);
    }

    @Test
    public void shouldSortAlreadySortedArray() throws Exception {

        List<Character> inputList = Arrays.asList('a', 'b', 'c', 'e', 'h');

        List<Character> sortedList = QuickSort.sortWithArrayLists(inputList);

        assertThat(sortedList).isEqualTo(inputList);
    }

    @Test
    public void shouldSortArrayWithAllSameValues() throws Exception {

        List<Character> inputList = Arrays.asList('a', 'a', 'a', 'a', 'a', 'a');

        List<Character> sortedList = QuickSort.sortWithArrayLists(inputList);

        assertThat(sortedList).isEqualTo(inputList);
    }

    @Test
    public void shouldSortEmptyArray() throws Exception {

        List<Character> emptyList = Collections.emptyList();

        List<Character> sortedList = QuickSort.sortWithArrayLists(emptyList);

        assertThat(sortedList).isEqualTo(emptyList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPassedNull() throws Exception {

        QuickSort.sortWithArrayLists(null);
    }
}

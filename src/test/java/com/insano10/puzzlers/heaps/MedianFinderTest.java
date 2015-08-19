package com.insano10.puzzlers.heaps;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MedianFinderTest
{

    @Test
    public void shouldFindMedianElementOfOddNumberOfIntegers() throws Exception
    {
        MedianFinder finder = new MedianFinder();

        double median = finder.getMedian(Lists.newArrayList(1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10));

        Assertions.assertThat(median).isEqualTo(4);
    }

    @Test
    public void shouldFindMedianElementOfEvenNumberOfIntegers() throws Exception
    {
        MedianFinder finder = new MedianFinder();

        double median = finder.getMedian(Lists.newArrayList(1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10));

        Assertions.assertThat(median).isEqualTo(4.5);
    }

    @Test
    public void shouldFindMedianElementOfUnsortedList() throws Exception
    {
        MedianFinder finder = new MedianFinder();

        double median = finder.getMedian(Lists.newArrayList(12, 5, 74, 8, 24, 20, 72, 30, 41, 17, 16, 99, 81));

        Assertions.assertThat(median).isEqualTo(24);
    }

    @Test
    public void shouldFindMedianElementOfSingleInteger() throws Exception
    {
        MedianFinder finder = new MedianFinder();

        double median = finder.getMedian(Lists.newArrayList(34));

        Assertions.assertThat(median).isEqualTo(34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetIllegalArgumentExceptionIfListIsEmpty() throws Exception
    {
        MedianFinder finder = new MedianFinder();

        finder.getMedian(Lists.newArrayList());
    }
}
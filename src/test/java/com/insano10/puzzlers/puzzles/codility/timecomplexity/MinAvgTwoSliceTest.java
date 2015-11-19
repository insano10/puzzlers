package com.insano10.puzzlers.puzzles.codility.timecomplexity;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MinAvgTwoSliceTest
{
    @Test
    public void shouldReturnTheIndexOfTheStartingPointOfTheLowestSliceWithTheMinimalAverage() throws Exception
    {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();

        Assertions.assertThat(minAvgTwoSlice.solution(new int[]{4, 2, 2, 5, 1, 5, 8})).isEqualTo(1);
    }

    @Test
    public void shouldReturnTheFirstIndexWhenAllElementsAreEqual() throws Exception
    {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();

        Assertions.assertThat(minAvgTwoSlice.solution(new int[]{2, 2, 2, 2})).isEqualTo(0);
    }

    @Test
    public void shouldReturnSliceIndexWithMinimalNegativeAverage() throws Exception
    {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();

        Assertions.assertThat(minAvgTwoSlice.solution(new int[]{2, 4, 2, -8})).isEqualTo(2);
    }

    @Test
    public void shouldReturnTheFirstIndexWhenInputOnlyHas2Elements() throws Exception
    {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();

        Assertions.assertThat(minAvgTwoSlice.solution(new int[]{1, 2})).isEqualTo(0);
    }

    @Test
    public void shouldReturnTheLowestIndexWhenInputOnlyHas3Elements() throws Exception
    {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();

        Assertions.assertThat(minAvgTwoSlice.solution(new int[]{1, 2, 1})).isEqualTo(0);
    }
}
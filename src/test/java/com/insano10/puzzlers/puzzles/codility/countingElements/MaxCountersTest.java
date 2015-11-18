package com.insano10.puzzlers.puzzles.codility.countingElements;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MaxCountersTest
{
    @Test
    public void shouldReturnStateOfCountersAfterOperationFollowingTheRules() throws Exception
    {
        MaxCounters maxCounters = new MaxCounters();

        Assertions.assertThat(maxCounters.solution(5, new int[]{3, 4, 4, 6, 1, 4, 4})).isEqualTo(new int[]{3, 2, 2, 4, 2});
    }

    @Test
    public void shouldReturnStateOfCountersAfterOperationFollowingTheRules2() throws Exception
    {
        MaxCounters maxCounters = new MaxCounters();

        Assertions.assertThat(maxCounters.solution(2, new int[]{1, 2, 3})).isEqualTo(new int[]{1,1});
        Assertions.assertThat(maxCounters.solution(2, new int[]{1, 3, 2})).isEqualTo(new int[]{1,2});
    }
}
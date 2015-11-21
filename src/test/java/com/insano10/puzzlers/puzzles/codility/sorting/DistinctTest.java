package com.insano10.puzzlers.puzzles.codility.sorting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DistinctTest
{
    @Test
    public void shouldReturnTheCountOfDistinctNumbersInAnArray() throws Exception
    {
        Distinct distinct = new Distinct();

        assertThat(distinct.solution(new int[]{2, 1, 1, 2, 3, 1})).isEqualTo(3);
        assertThat(distinct.solution(new int[]{2, 4, 6, 8})).isEqualTo(4);
        assertThat(distinct.solution(new int[]{-1, 0, 1})).isEqualTo(3);

        assertThat(distinct.solution(new int[]{1})).isEqualTo(1);
        assertThat(distinct.solution(new int[]{1, 1, 1})).isEqualTo(1);

        assertThat(distinct.solution(new int[0])).isEqualTo(0);
    }
}
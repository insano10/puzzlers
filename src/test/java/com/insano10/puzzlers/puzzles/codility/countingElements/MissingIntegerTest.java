package com.insano10.puzzlers.puzzles.codility.countingElements;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissingIntegerTest
{
    @Test
    public void shouldReturnMinimalPositiveMissingIntegerFromInput() throws Exception
    {
        MissingInteger missingInteger = new MissingInteger();

        assertThat(missingInteger.solution(new int[]{1, 3, 6, 4, 1, 2})).isEqualTo(5);
        assertThat(missingInteger.solution(new int[]{1, 2, 3, 4, 5})).isEqualTo(6);
        assertThat(missingInteger.solution(new int[]{1, 1})).isEqualTo(2);
        assertThat(missingInteger.solution(new int[]{-1})).isEqualTo(1);
        assertThat(missingInteger.solution(new int[]{0})).isEqualTo(1);
        assertThat(missingInteger.solution(new int[]{Integer.MAX_VALUE})).isEqualTo(1);
    }
}
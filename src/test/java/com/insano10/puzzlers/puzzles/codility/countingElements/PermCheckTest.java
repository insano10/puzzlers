package com.insano10.puzzlers.puzzles.codility.countingElements;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PermCheckTest
{
    @Test
    public void shouldReturnOneIfTheInputIsAPermutation() throws Exception
    {
        PermCheck permCheck = new PermCheck();

        Assertions.assertThat(permCheck.solution(new int[]{1, 2, 3, 4})).isEqualTo(1);
        Assertions.assertThat(permCheck.solution(new int[]{4, 1, 3, 2})).isEqualTo(1);
        Assertions.assertThat(permCheck.solution(new int[]{1})).isEqualTo(1);
    }

    @Test
    public void shouldReturnZeroIfTheInputIsNotAPermutation() throws Exception
    {
        PermCheck permCheck = new PermCheck();

        Assertions.assertThat(permCheck.solution(new int[]{1, 2, 4})).isEqualTo(0);
        Assertions.assertThat(permCheck.solution(new int[]{3, 3})).isEqualTo(0);
        Assertions.assertThat(permCheck.solution(new int[]{2})).isEqualTo(0);
    }
}
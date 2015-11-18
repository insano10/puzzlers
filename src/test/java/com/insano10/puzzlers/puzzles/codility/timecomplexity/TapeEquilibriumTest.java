package com.insano10.puzzlers.puzzles.codility.timecomplexity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TapeEquilibriumTest
{
    @Test
    public void shouldFindMinimalDifferenceFromGivenExample() throws Exception
    {
        int[] input = new int[] {3, 1, 2, 4, 3};

        TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();

        assertThat(tapeEquilibrium.solution(input)).isEqualTo(1);
    }

    @Test
    public void shouldFindMinimalDifferenceWhenItIsZero() throws Exception
    {
        int[] input = new int[] {1, 1, 1, 1};

        TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();

        assertThat(tapeEquilibrium.solution(input)).isEqualTo(0);
    }

    @Test
    public void shouldFindMinimalDifferenceWhereTheInputContainsNegativeNumbers() throws Exception
    {
        int[] input = new int[] {-10, 5, -3, -1, 7};

        TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();

        assertThat(tapeEquilibrium.solution(input)).isEqualTo(8);
    }
}
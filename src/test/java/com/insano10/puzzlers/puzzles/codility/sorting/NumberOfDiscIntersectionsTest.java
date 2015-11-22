package com.insano10.puzzlers.puzzles.codility.sorting;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class NumberOfDiscIntersectionsTest
{
    @Test
    public void shouldReturnTheNumberOfDiscIntersections() throws Exception
    {
        NumberOfDiscIntersections intersections = new NumberOfDiscIntersections();

        Assertions.assertThat(intersections.solution(new int[]{1, 5, 2, 1, 4, 0})).isEqualTo(11);
        Assertions.assertThat(intersections.solution(new int[]{0, 0, 0})).isEqualTo(0);
        Assertions.assertThat(intersections.solution(new int[]{1, 1})).isEqualTo(1);

    }

    @Test
    public void shouldReturnMinusOneIfTheNumberOfDiscIntersectionsExceedTenMillion() throws Exception
    {
        int[] discs = new int[10_000];
        for (int i = 0; i < discs.length; i++)
        {
            discs[i] = Integer.MAX_VALUE;
        }

        NumberOfDiscIntersections intersections = new NumberOfDiscIntersections();

        Assertions.assertThat(intersections.solution(discs)).isEqualTo(-1);
    }

    @Test
    public void shouldReturnMTheNumberOfDiscIntersectionsIncludingADiscWithRadiusMaxInt() throws Exception
    {
        NumberOfDiscIntersections intersections = new NumberOfDiscIntersections();

        Assertions.assertThat(intersections.solution(new int[]{Integer.MAX_VALUE, 1})).isEqualTo(1);

    }
}